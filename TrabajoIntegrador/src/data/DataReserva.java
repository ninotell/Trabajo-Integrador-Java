package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import entities.Categoria;
import entities.Reserva;
import entities.Usuario;
import entities.Vehiculo;

public class DataReserva {

	
	
	
	
//	public Reserva getReserva(Reserva rToSearch) {
//		Reserva r=null;
//		PreparedStatement stmt=null;
//		ResultSet rs=null;
//		try {
//			stmt=DbConnector.getInstancia().getConn().prepareStatement(
//					"select idReserva,fechaReserva,fechaRetiro,fechaDevolucion,fechaCancelacion,estado from reserva where idVehiculo=?"
//					);
//			stmt.setInt(1, rToSearch.get());
//			rs=stmt.executeQuery();
//			if(rs!=null && rs.next()) {
//				r=new Reserva();
//				r.setIdReserva(rs.getInt("idReserva"));
//				r.setFechaReserva(rs.getString("fechaReserva"));
//				r.setFechaRetiro((rs.getString("fechaRetiro"));
//				v.setModelo(rs.getString("fechaDevolucion"));
//				v.setAnio(rs.getInt("fechaCancelacion"));
//				v.setTransmision(rs.getString("estado"));
//
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				if(rs!=null) {rs.close();}
//				if(stmt!=null) {stmt.close();}
//				DbConnector.getInstancia().releaseConn();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return r;
//	}
	
	
	public LinkedList<Vehiculo> VehiculosDisponibles(Categoria c, Reserva r) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Vehiculo> vehiculos= new LinkedList<>();
		Vehiculo v=null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd"); 
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
					
			"SELECT vehiculo.idVehiculo,vehiculo.marca,vehiculo.modelo,vehiculo.año,vehiculo.transmision,vehiculo.patente,vehiculo.km "
			+ "FROM reserva_usuario_vehiculo"
			+ "	inner join reserva on reserva_usuario_vehiculo.id_reserva=reserva.idReserva"
			+ " right join vehiculo on reserva_usuario_vehiculo.id_vehiculo=vehiculo.idVehiculo "
			+ "where reserva.idReserva is null OR ((reserva.fechaDevolucion<? or reserva.fechaRetiro>?) and reserva.estado=?) "
			+ "group by vehiculo.idVehiculo"
					
					);
			stmt.setString(1, formatter.format(r.getFechaRetiro()));
			stmt.setString(2, formatter.format(r.getFechaDevolucion()));
//			stmt.setString(1, "2020/09/28");
//			stmt.setString(2, "2020/10/02");
			stmt.setString(3, "NF");
			rs=stmt.executeQuery();			
			if(rs!=null) {
				while(rs.next()) {
					v=new Vehiculo();
					v.setIdVehiculo(rs.getInt("vehiculo.idVehiculo"));
					v.setPatente(rs.getString("vehiculo.patente"));
					v.setMarca(rs.getString("vehiculo.marca"));
					v.setModelo(rs.getString("vehiculo.modelo"));
					v.setAnio(rs.getInt("vehiculo.año"));
					v.setTransmision(rs.getString("vehiculo.transmision"));
					v.setKm(rs.getDouble("vehiculo.km"));
					
					vehiculos.add(v);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		 	finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return vehiculos;
	}
	
	
	public void newReserva(Reserva r) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet=null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd"); 
		
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into reserva (fechaReserva,fechaRetiro,fechaDevolucion,estado)"
							+ " values(CURDATE(),?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
//			stmt.setString(1, "CURDATE()");
			stmt.setString(1, formatter.format(r.getFechaRetiro()));
			stmt.setString(2,  formatter.format(r.getFechaDevolucion()));
			stmt.setString(3, "NF");
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                r.setIdReserva(keyResultSet.getInt(1));
            }
			
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		
	}
	
	public void agregaDatosReserva(Vehiculo v, Usuario u) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into reserva_usuario_vehiculo(id_vehiculo, id_usuario) values(?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, v.getIdVehiculo());
			stmt.setInt(2, u.getIdUsuario());
			stmt.executeUpdate();
			} 
		
		catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
		
	}
	
}
