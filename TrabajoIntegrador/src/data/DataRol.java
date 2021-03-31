package data;

import java.sql.*;
import entities.*;

public class DataRol {

	public void setRoles(Usuario u) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select r.idRol, r.descripcion from rol r inner join usuario_rol ur on r.idRol = ur.id_rol where ur.fecha_cambio = (select max(fecha_cambio) from usuario_rol where id_usuario=? group by id_rol and id_usuario) and id_usuario=?"
					);
			stmt.setInt(1, u.getIdUsuario());
			stmt.setInt(2, u.getIdUsuario());
			rs= stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
					Rol r=new Rol();
					r.setIdRol(rs.getInt("idRol"));
					r.setDescripcion(rs.getString("descripcion"));
					u.addRol(r);;
				}
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
	}
	
	
	public Rol getById(Rol rolToSearch) {
		Rol r=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from rol where idRol=?"
					);
			stmt.setInt(1, rolToSearch.getIdRol());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				r=new Rol();
				r.setIdRol(rs.getInt("idRol"));
				r.setDescripcion(rs.getString("descripcion"));
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
		
		return r;
	}
	
	public void addRol(Rol rol, Usuario u) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into usuario_rol(id_usuario, id_rol) values(?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, u.getIdUsuario());
			stmt.setInt(2, rol.getIdRol());
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
	
	public void deleteRol(Usuario u) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from usuario_rol where id_usuario=?",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, u.getIdUsuario());
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
	
	public void getRol (Usuario u) {
		
	}
	
}
