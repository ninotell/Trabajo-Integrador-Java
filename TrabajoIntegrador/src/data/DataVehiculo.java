package data;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.http.HttpServletResponse;



import entities.Reserva;
import entities.Vehiculo;

public class DataVehiculo {

	public LinkedList<Vehiculo> getByAnio(Vehiculo ve){
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		LinkedList<Vehiculo> vehiculos= new LinkedList<>();
		Vehiculo v=null;
		
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement(
			"select idVehiculo,patente,marca,modelo,a�o,transmision,km from vehiculo where a�o>?"
					);
			stmt.setInt(1, ve.getAnio());
			rs=stmt.executeQuery();			
			if(rs!=null) {
				while(rs.next()) {
					v=new Vehiculo();
					v.setIdVehiculo(rs.getInt("idVehiculo"));
					v.setPatente(rs.getString("patente"));
					v.setMarca(rs.getString("marca"));
					v.setModelo(rs.getString("modelo"));
					v.setAnio(rs.getInt("a�o"));
					v.setTransmision(rs.getString("transmision"));
					v.setKm(rs.getDouble("km"));
					
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
	
	public Vehiculo getById(Vehiculo vToSearch) {
		Vehiculo v=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select idVehiculo,patente,marca,modelo,a�o,transmision,km,foto from vehiculo where idVehiculo=?"
					);
			stmt.setInt(1, vToSearch.getIdVehiculo());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				v=new Vehiculo();
				v.setIdVehiculo(rs.getInt("idVehiculo"));
				v.setPatente(rs.getString("patente"));
				v.setMarca(rs.getString("marca"));
				v.setModelo(rs.getString("modelo"));
				v.setAnio(rs.getInt("a�o"));
				v.setTransmision(rs.getString("transmision"));
				v.setKm(rs.getDouble("km"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return v;
	}	
	
	public void listaImagen(int id, HttpServletResponse response) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		byte[] imgData = null; 
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from Vehiculo where idVehiculo=?"
					);
	        stmt.setInt(1, id);
	        rs = stmt.executeQuery();
	        if (rs.next()) {
	           try {
	        	   Blob blob = rs.getBlob("foto");
	        	   imgData = blob.getBytes(1, (int) blob.length());
	        	   response.setContentType("image/gif");
	        	   OutputStream os = response.getOutputStream();
	        	   os.write(imgData);
	        	   os.flush();
	        	   os.close();
	           }catch (NullPointerException e) {
	        	   System.out.println(rs.getInt("idVehiculo") + " sin imagen");
	           }
	        } 
	    } catch (Exception e) {
	        e.printStackTrace();
	        
	    }
		
	}
	
	
	public void updateKm(Vehiculo v) {
			PreparedStatement stmt = null;
			ResultSet keyResultSet=null;
			try {
				stmt=DbConnector.getInstancia().getConn().
						prepareStatement(
								"update vehiculo set km=? where idVehiculo=?");
				stmt.setDouble(1, v.getKm());
				stmt.setInt(2, v.getIdVehiculo());
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
	
	public void newVehiculo(Vehiculo v)  {
		PreparedStatement stmt = null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into vehiculo (idVehiculo,patente,marca,modelo,a�o,transmision,km,foto)"
							+ " values(?,?,?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, v.getIdVehiculo());
			stmt.setString(2, v.getPatente());
			stmt.setString(3, v.getMarca());
			stmt.setString(4, v.getModelo());
			stmt.setInt(5, v.getAnio());
			stmt.setString(6, v.getTransmision());
			stmt.setDouble(7, v.getKm());
			stmt.setBlob(8, v.getFoto());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                v.setIdVehiculo(keyResultSet.getInt(1));
            }
			
		} catch (java.sql.SQLException e) {
            e.printStackTrace(); //FALTA AGREGAR EXCEPCION DE PATENTE DUPLICADA
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
	
	public void deleteVehiculo(Vehiculo v) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from vehiculo where idVehiculo=?",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, v.getIdVehiculo());
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
	
	public void edit(Vehiculo v) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update vehiculo set km=? where idVehiculo=?",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setDouble(1, v.getKm());
			stmt.setInt(2, v.getIdVehiculo());
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
	
	public Vehiculo getVehiculoByReserva(Reserva rToSearch) {
		Vehiculo v=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT v.idVehiculo, v.marca, v.patente, v.modelo, v.a�o, v.transmision\r\n"
					+ "FROM reserva r\r\n"
					+ "inner join reserva_usuario_vehiculo ruv on r.idReserva = ruv.id_reserva\r\n"
					+ "inner join vehiculo v on ruv.id_vehiculo = v.idVehiculo\r\n"
					+ "where r.idReserva = ?"
					);
			stmt.setInt(1, rToSearch.getIdReserva());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				v=new Vehiculo();
				v.setIdVehiculo(rs.getInt("idVehiculo"));
				v.setPatente(rs.getString("patente"));
				v.setMarca(rs.getString("marca"));
				v.setModelo(rs.getString("modelo"));
				v.setAnio(rs.getInt("a�o"));
				v.setTransmision(rs.getString("transmision"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return v;
	}
	
	
			
	
	
}
