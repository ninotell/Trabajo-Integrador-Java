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

	
	
	
	
//	public LinkedList<Reserva> getReserva(Reserva rToSearch, Usuario u) {
//		Reserva r=null;
//		PreparedStatement stmt=null;
//		ResultSet rs=null;
//		LinkedList<Reserva> reservas= new LinkedList<>();
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd"); 
//		try {
//			stmt=DbConnector.getInstancia().getConn().prepareStatement(
//					"select idReserva,fechaReserva,fechaRetiro,fechaDevolucion,fechaCancelacion,estado\r\n" + 
//					"from reserva r\r\n" + 
//					"inner join reserva_usuario_vehiculo ruv on r.idReserva = ruv.id_reserva\r\n" + 
//					"where id_usuario = ? and r.estado = ?"
//					);
//			stmt.setInt(1, u.getIdUsuario());
//			stmt.setString(2, rToSearch.getEstado());
//			rs=stmt.executeQuery();
//			if(rs!=null && rs.next()) {
//				System.out.println(rs.getTimestamp("fechaReserva"));
//				r=new Reserva();
//				r.setIdReserva(rs.getInt("idReserva"));
//				r.setFechaReserva(rs.getTimestamp("fechaReserva"));
//				r.setFechaRetiro(rs.getDate("fechaRetiro"));
//				r.setFechaDevolucion(rs.getDate("fechaDevolucion"));
//				r.setFechaCancelacion(rs.getDate("fechaCancelacion"));
//				r.setEstado(rs.getString("estado"));
//
//				reservas.add(r);
//				
//
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}	finally {
//			try {
//				if(rs!=null) {rs.close();}
//				if(stmt!=null) {stmt.close();}
//				DbConnector.getInstancia().releaseConn();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return reservas;
//	}
	
	public LinkedList<Reserva> getReservaByUser(Usuario u) {
		Reserva r=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Reserva> reservas= new LinkedList<>();
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select idReserva,fechaReserva,fechaRetiro,fechaDevolucion,fechaCancelacion,estado from reserva r inner join reserva_usuario_vehiculo ruv on r.idReserva = ruv.id_reserva where id_usuario = ?"
					);
			stmt.setInt(1, u.getIdUsuario());
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
				r=new Reserva();
				r.setIdReserva(rs.getInt("idReserva"));
				r.setFechaReserva(rs.getTimestamp("fechaReserva"));
				r.setFechaRetiro(rs.getDate("fechaRetiro"));
				r.setFechaDevolucion(rs.getDate("fechaDevolucion"));
				r.setFechaCancelacion(rs.getDate("fechaCancelacion"));
				r.setEstado(rs.getString("estado"));
				reservas.add(r);
				
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return reservas;
	}
	
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
							"insert into reserva (idReserva,fechaReserva,fechaRetiro,fechaDevolucion,estado)"
							+ " values(?,current_timestamp(),?,?,\"Iniciada\")",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, r.getIdReserva());
			stmt.setString(2, formatter.format(r.getFechaRetiro()));
			stmt.setString(3,  formatter.format(r.getFechaDevolucion()));
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
	
	public void agregaDatosReserva(Reserva r, Vehiculo v, Usuario u) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into reserva_usuario_vehiculo(id_reserva, id_vehiculo, id_usuario) values(?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, r.getIdReserva());
			stmt.setInt(2, v.getIdVehiculo());
			stmt.setInt(3, u.getIdUsuario());
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
	
	public void actualizaEstadoReserva(Reserva r) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update reserva r set r.estado=?,r.fechaCancelacion=current_timestamp() where r.idReserva=?");
			stmt.setString(1, r.getEstado());
			stmt.setInt(2, r.getIdReserva());
			stmt.executeUpdate();
			
			
			
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
}
