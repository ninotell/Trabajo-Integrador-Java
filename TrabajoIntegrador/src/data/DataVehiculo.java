package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import entities.Categoria;
import entities.Reserva;
import entities.Vehiculo;

public class DataVehiculo {

	public LinkedList<Vehiculo> getByAnio(Vehiculo ve) {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<Vehiculo> vehiculos = new LinkedList<>();
		Vehiculo v = null;

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select idVehiculo,patente,marca,modelo,año,transmision,km,foto from vehiculo where año>?");
			stmt.setInt(1, ve.getAnio());
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					v = new Vehiculo();
					v.setIdVehiculo(rs.getInt("idVehiculo"));
					v.setPatente(rs.getString("patente"));
					v.setMarca(rs.getString("marca"));
					v.setModelo(rs.getString("modelo"));
					v.setAnio(rs.getInt("año"));
					v.setTransmision(rs.getString("transmision"));
					v.setKm(rs.getDouble("km"));
					v.setFoto(rs.getString("foto"));

					vehiculos.add(v);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return vehiculos;
	}

	public Vehiculo getById(Vehiculo vToSearch) {
		Vehiculo v = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select idVehiculo,patente,marca,modelo,año,transmision,km,foto from vehiculo where idVehiculo=?");
			stmt.setInt(1, vToSearch.getIdVehiculo());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				v = new Vehiculo();
				v.setIdVehiculo(rs.getInt("idVehiculo"));
				v.setPatente(rs.getString("patente"));
				v.setMarca(rs.getString("marca"));
				v.setModelo(rs.getString("modelo"));
				v.setAnio(rs.getInt("año"));
				v.setTransmision(rs.getString("transmision"));
				v.setKm(rs.getDouble("km"));
				v.setFoto(rs.getString("foto"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return v;
	}

	public void updateKm(Vehiculo v) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("update vehiculo set km=? where idVehiculo=?");
			stmt.setDouble(1, v.getKm());
			stmt.setInt(2, v.getIdVehiculo());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (keyResultSet != null)
					keyResultSet.close();
				if (stmt != null)
					stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void newVehiculo(Vehiculo v) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"insert into vehiculo (patente,marca,modelo,año,transmision,km,foto)" + " values(?,?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, v.getPatente());
			stmt.setString(2, v.getMarca());
			stmt.setString(3, v.getModelo());
			stmt.setInt(4, v.getAnio());
			stmt.setString(5, v.getTransmision());
			stmt.setDouble(6, v.getKm());
			stmt.setString(7, v.getFoto());
			stmt.executeUpdate();

			keyResultSet = stmt.getGeneratedKeys();
			if (keyResultSet != null && keyResultSet.next()) {
				v.setIdVehiculo(keyResultSet.getInt(1));
			}

		} catch (java.sql.SQLException e) {
			throw e;// FALTA AGREGAR EXCEPCION DE PATENTE DUPLICADA
		} finally {
			try {
				if (keyResultSet != null)
					keyResultSet.close();
				if (stmt != null)
					stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void deleteVehiculo(Vehiculo v) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("delete from vehiculo where idVehiculo=?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, v.getIdVehiculo());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (keyResultSet != null)
					keyResultSet.close();
				if (stmt != null)
					stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void edit(Vehiculo v) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("update vehiculo set km=? where idVehiculo=?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setDouble(1, v.getKm());
			stmt.setInt(2, v.getIdVehiculo());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (keyResultSet != null)
					keyResultSet.close();
				if (stmt != null)
					stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public Vehiculo getVehiculoByReserva(Reserva rToSearch) {
		Vehiculo v = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT v.idVehiculo, v.marca, v.patente, v.modelo, v.año, v.transmision,v.foto\r\n"
							+ "FROM reserva r\r\n"
							+ "inner join reserva_usuario_vehiculo ruv on r.idReserva = ruv.id_reserva\r\n"
							+ "inner join vehiculo v on ruv.id_vehiculo = v.idVehiculo\r\n" + "where r.idReserva = ?");
			stmt.setInt(1, rToSearch.getIdReserva());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				v = new Vehiculo();
				v.setIdVehiculo(rs.getInt("idVehiculo"));
				v.setPatente(rs.getString("patente"));
				v.setMarca(rs.getString("marca"));
				v.setModelo(rs.getString("modelo"));
				v.setAnio(rs.getInt("año"));
				v.setTransmision(rs.getString("transmision"));
				v.setFoto(rs.getString("foto"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return v;
	}

	public LinkedList<Vehiculo> getByCategoria(Categoria c) {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<Vehiculo> vehiculos = new LinkedList<>();
		Vehiculo v = null;

		try {
			stmt = DbConnector.getInstancia().getConn()
					.prepareStatement("SELECT idVehiculo,patente,marca,modelo,año,transmision,km,foto \r\n"
							+ "FROM vehiculo v\r\n"
							+ "inner join vehiculos_categorias vc on v.idVehiculo = vc.id_vehiculo\r\n"
							+ "where vc.id_categoria = ?");
			stmt.setInt(1, c.getIdCategoria());
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					v = new Vehiculo();
					v.setIdVehiculo(rs.getInt("idVehiculo"));
					v.setPatente(rs.getString("patente"));
					v.setMarca(rs.getString("marca"));
					v.setModelo(rs.getString("modelo"));
					v.setAnio(rs.getInt("año"));
					v.setTransmision(rs.getString("transmision"));
					v.setKm(rs.getDouble("km"));
					v.setFoto(rs.getString("foto"));

					vehiculos.add(v);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return vehiculos;
	}
	
	public LinkedList<Vehiculo> VehiculosDisponibles(Categoria c, Reserva r, Vehiculo v) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<Vehiculo> vehiculos = new LinkedList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(

					"select v.idVehiculo, v.marca, v.modelo, v.año, v.foto from vehiculo v\r\n"
							+ "inner join vehiculos_categorias vc on v.idVehiculo = vc.id_vehiculo\r\n"
							+ "where vc.id_categoria = ? and v.transmision = ? \r\n"
							+ "and v.idVehiculo not in (SELECT id_vehiculo\r\n"
							+ "from reserva_usuario_vehiculo ruv\r\n"
							+ "inner join reserva r on ruv.id_reserva = r.idReserva\r\n"
							+ "where r.fechaDevolucion>? and r.fechaRetiro<? and r.estado != ?)"

			);
			stmt.setInt(1, c.getIdCategoria());
			stmt.setString(2, v.getTransmision());
			stmt.setString(3, formatter.format(r.getFechaRetiro()));
			stmt.setString(4, formatter.format(r.getFechaDevolucion()));
			stmt.setString(5, "cancelada");
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					v = new Vehiculo();
					v.setIdVehiculo(rs.getInt("v.idVehiculo"));
					v.setMarca(rs.getString("v.marca"));
					v.setModelo(rs.getString("v.modelo"));
					v.setAnio(rs.getInt("v.año"));
					v.setFoto(rs.getString("v.foto"));
					vehiculos.add(v);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return vehiculos;
	}

}
