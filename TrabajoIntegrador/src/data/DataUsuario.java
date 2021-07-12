package data;

import java.sql.*;
import java.util.LinkedList;

import entities.*;

public class DataUsuario {
		
	public Usuario getByUser(Usuario u) {
		DataRol dr=new DataRol();
		Usuario user = null;
		PreparedStatement stmt = null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select idUsuario,mail,contraseña,tipoDocumento,nroDocumento,nombre,apellido,telefono,direccion from usuario where mail=? and contraseña=?"
					);
			stmt.setString(1, u.getEmail());
			stmt.setString(2, u.getPassword());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				user=new Usuario();
				user.setDocumento(new Documento());
				user.setIdUsuario(rs.getInt("idUsuario"));
				user.setEmail(rs.getString("mail"));
				user.getDocumento().setTipo(rs.getString("tipoDocumento"));
				user.getDocumento().setNro(rs.getString("nroDocumento"));
				user.setNombre(rs.getString("nombre"));
				user.setApellido(rs.getString("apellido"));		
				user.setDireccion(rs.getString("direccion"));
				user.setTel(rs.getString("telefono"));
				//
				dr.setRoles(user);
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
		
		return user;
		
	}
	
	public void newUsuario(Usuario u) throws SQLException  {
		PreparedStatement stmt = null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into usuario(mail,contraseña,tipoDocumento,nroDocumento,nombre,apellido,telefono,direccion)"
							+ " values(?,?,?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, u.getEmail());
			stmt.setString(2, u.getPassword());
			stmt.setString(3, u.getDocumento().getTipo());
			stmt.setString(4, u.getDocumento().getNro());
			stmt.setString(5, u.getNombre());
			stmt.setString(6, u.getApellido());
			stmt.setString(7, u.getTel());
			stmt.setString(8, u.getDireccion());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                u.setIdUsuario(keyResultSet.getInt(1));
            }
			
		} 
		catch (SQLException e) {
 	} 	
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
	}

	
	
	public LinkedList<Usuario> listaUsuarios(){
		DataRol dr=new DataRol();
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Usuario> usuarios= new LinkedList<>();
		Usuario u=null;
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select idUsuario,mail,tipoDocumento,nroDocumento,nombre,apellido,telefono,direccion,contraseña from usuario"
					);
			if(rs!=null) {
				while(rs.next()) {
					u=new Usuario();
					u.setDocumento(new Documento());
					u.setIdUsuario(rs.getInt("idUsuario"));
					u.setEmail(rs.getString("mail"));
					u.getDocumento().setTipo(rs.getString("tipoDocumento"));
					u.getDocumento().setNro(rs.getString("nroDocumento"));
					u.setNombre(rs.getString("nombre"));
					u.setApellido(rs.getString("apellido"));		
					u.setDireccion(rs.getString("direccion"));
					u.setPassword(rs.getString("contraseña"));
					u.setTel(rs.getString("telefono"));		
					dr.setRoles(u);
					
					usuarios.add(u);
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
		return usuarios;
	}
	
	public Usuario getById (Usuario u) {
		DataRol dr = new DataRol();
		Usuario us=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select idUsuario,mail,tipoDocumento,nroDocumento,nombre,apellido,telefono,direccion from usuario where idUsuario=?"
					);
			stmt.setInt(1, u.getIdUsuario());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				us=new Usuario();
				us.setDocumento(new Documento());
				us.setIdUsuario(rs.getInt("idUsuario"));
				us.setEmail(rs.getString("mail"));
				us.getDocumento().setTipo(rs.getString("tipoDocumento"));
				us.getDocumento().setNro(rs.getString("nroDocumento"));
				us.setNombre(rs.getString("nombre"));
				us.setApellido(rs.getString("apellido"));		
				us.setDireccion(rs.getString("direccion"));
				us.setTel(rs.getString("telefono"));		
				dr.setRoles(us);

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
		
		return us;
	}
	public void deleteUsuario(Usuario u) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from usuario where idUsuario=?",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, u.getIdUsuario());
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
	public void updatePsw(Usuario u) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update usuario set contraseña=? where idUsuario=?");
			stmt.setString(1, u.getPassword());
			stmt.setInt(2, u.getIdUsuario());
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
	
	public void updateRol(Usuario u) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update usuario set contraseña=? where idUsuario=?");
			stmt.setString(1, u.getPassword());
			stmt.setInt(2, u.getIdUsuario());
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
	
	

