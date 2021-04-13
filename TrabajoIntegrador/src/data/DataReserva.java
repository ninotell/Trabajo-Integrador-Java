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
	
	
	public LinkedList<Vehiculo> VehiculosDisponibles(Categoria c, Reserva r,Vehiculo v) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Vehiculo> vehiculos= new LinkedList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd"); 
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
					
			"select v.idVehiculo, v.marca, v.modelo, v.año from vehiculo v\r\n" + 
			"inner join vehiculos_categorias vc on v.idVehiculo = vc.id_vehiculo\r\n" + 
			"where vc.id_categoria = ? and v.transmision = ? \r\n" + 
			"and v.idVehiculo not in (SELECT id_vehiculo\r\n" + 
			"from reserva_usuario_vehiculo ruv\r\n" + 
			"inner join reserva r on ruv.id_reserva = r.idReserva\r\n" + 
			"where r.fechaDevolucion>? and r.fechaRetiro<? and r.estado != ?)"
					
					);
			stmt.setInt(1, c.getIdCategoria());
			stmt.setString(2, v.getTransmision());
			stmt.setString(3, formatter.format(r.getFechaRetiro()));
			stmt.setString(4, formatter.format(r.getFechaDevolucion()));
			stmt.setString(5, "cancelada");
			rs=stmt.executeQuery();			
			if(rs!=null) {
				while(rs.next()) {
					v=new Vehiculo();
					v.setIdVehiculo(rs.getInt("v.idVehiculo"));
					v.setMarca(rs.getString("v.marca"));
					v.setModelo(rs.getString("v.modelo"));
					v.setAnio(rs.getInt("v.año"));
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
