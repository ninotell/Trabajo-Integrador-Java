package data;

import java.sql.*;
import entities.*;

public class DataCategoria {
	
	public Categoria getById(Categoria catToSearch) {
		Categoria c=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from categoria where idCategoria=?"
					);
			stmt.setInt(1, catToSearch.getIdCategoria());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				c=new Categoria();
				c.setIdCategoria(rs.getInt("idCategoria"));
				c.setDescripcion(rs.getString("descripcion"));
				c.setPrecioxDia(rs.getDouble("precioXdia"));
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
		
		return c;
	}
	
	public void addCategoria(Categoria c, Vehiculo v) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into vehiculos_categorias(id_vehiculo, id_categoria) values(?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, v.getIdVehiculo());
			stmt.setInt(2, c.getIdCategoria());
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
	
	public void deleteCategoriaVehiculo(Vehiculo v) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from vehiculos_categorias where id_vehiculo=?",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setInt(1, v.getIdVehiculo());
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
