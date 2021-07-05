package data;

import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import entities.Categoria;
import entities.Reserva;
import entities.Usuario;
import entities.Vehiculo;

public class DataReserva {

	public Reserva getReservaById(Reserva vToSearch) {
		Reserva r = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select idReserva,fechaReserva,fechaRetiro,fechaDevolucion,fechaCancelacion,estado from reserva where idReserva=?");
			stmt.setInt(1, vToSearch.getIdReserva());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				r = new Reserva();
				r.setIdReserva(rs.getInt("idReserva"));
				r.setFechaReserva(rs.getTimestamp("fechaReserva"));
				r.setFechaRetiro(rs.getDate("fechaRetiro"));
				r.setFechaDevolucion(rs.getDate("fechaDevolucion"));
				r.setFechaCancelacion(rs.getDate("fechaCancelacion"));
				r.setEstado(rs.getString("estado"));

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

		return r;
	}

	public LinkedList<Reserva> getReservaByUser(Usuario u) {
		Reserva r = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<Reserva> reservas = new LinkedList<>();
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"select idReserva,fechaReserva,fechaRetiro,fechaDevolucion,fechaCancelacion,estado"
							+ " from reserva r"
							+ " inner join reserva_usuario_vehiculo ruv on r.idReserva = ruv.id_reserva"
							+ " where id_usuario = ?");
			stmt.setInt(1, u.getIdUsuario());
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					r = new Reserva();
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

		return reservas;
	}

	public LinkedList<Vehiculo> VehiculosDisponibles(Categoria c, Reserva r, Vehiculo v) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<Vehiculo> vehiculos = new LinkedList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(

					"select v.idVehiculo, v.marca, v.modelo, v.año from vehiculo v\r\n"
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

	public void newReserva(Reserva r) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"insert into reserva (idReserva,fechaReserva,fechaRetiro,fechaDevolucion,estado)"
							+ " values(?,current_timestamp(),?,?,\"Iniciada\")",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, r.getIdReserva());
			stmt.setString(2, formatter.format(r.getFechaRetiro()));
			stmt.setString(3, formatter.format(r.getFechaDevolucion()));
			stmt.executeUpdate();

			keyResultSet = stmt.getGeneratedKeys();
			if (keyResultSet != null && keyResultSet.next()) {
				r.setIdReserva(keyResultSet.getInt(1));
			}

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

	public void agregaDatosReserva(Reserva r, Vehiculo v, Usuario u) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"insert into reserva_usuario_vehiculo(id_reserva, id_vehiculo, id_usuario) values(?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, r.getIdReserva());
			stmt.setInt(2, v.getIdVehiculo());
			stmt.setInt(3, u.getIdUsuario());
			stmt.executeUpdate();
		}

		catch (SQLException e) {
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

	public void actualizaEstadoReserva(Reserva r) {

		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"update reserva r set r.estado=?,r.fechaCancelacion=current_timestamp() where r.idReserva=?");
			stmt.setString(1, r.getEstado());
			stmt.setInt(2, r.getIdReserva());
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

	public void retirarVehiculo(Reserva r) {

		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"update reserva r set r.estado=?,r.fechaCancelacion=current_timestamp() where r.idReserva=?");
			stmt.setString(1, r.getEstado());
			stmt.setInt(2, r.getIdReserva());
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

	public void emailCancela(Reserva r, Usuario u) {

		try {

			Properties props = new Properties();
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.auth", "true");

			Session session = Session.getDefaultInstance(props);

			String correoRemitente = "rentsmart.alquileautos@gmail.com";
			String passwordRemitente = "BlancoTell00";
			String correoReceptor = u.getEmail();
			String asunto = "CANCELACION RESERVA";
			String mensaje = ("<h1>Reserva cancelada con éxito</h1> " + "<h2> ID de reserva: " + r.getIdReserva()
					+ ".</h2>");
			String nombreRemitente = "Rent Smart";

			MimeMessage message = new MimeMessage(session);
			try {
				message.setFrom(new InternetAddress(correoRemitente, nombreRemitente));
			} catch (UnsupportedEncodingException e) {

				e.printStackTrace();
			}
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
			message.setSubject(asunto);
			message.setContent(mensaje, "text/html");

			Transport t = session.getTransport("smtp");
			t.connect(correoRemitente, passwordRemitente);
			t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			t.close();

		} catch (AddressException e) {

			Logger.getLogger(DataReserva.class.getName()).log(Level.SEVERE, null, e);

		} catch (MessagingException e) {

			Logger.getLogger(DataReserva.class.getName()).log(Level.SEVERE, null, e);

		}

	}
	
	public void emailConfirma(Reserva r, Usuario u) {

		try {

			Properties props = new Properties();
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.auth", "true");

			Session session = Session.getDefaultInstance(props);

			String correoRemitente = "rentsmart.alquileautos@gmail.com";
			String passwordRemitente = "BlancoTell00";
			String correoReceptor = u.getEmail();
			String asunto = "CANCELACION RESERVA";
			String mensaje = ("<h1>Reserva cancelada con éxito</h1> " + "<h2> ID de reserva: " + r.getIdReserva()
					+ ".</h2>");
			String nombreRemitente = "Rent Smart";

			MimeMessage message = new MimeMessage(session);
			try {
				message.setFrom(new InternetAddress(correoRemitente, nombreRemitente));
			} catch (UnsupportedEncodingException e) {

				e.printStackTrace();
			}
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
			message.setSubject(asunto);
			message.setContent(mensaje, "text/html");

			Transport t = session.getTransport("smtp");
			t.connect(correoRemitente, passwordRemitente);
			t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			t.close();

		} catch (AddressException e) {

			Logger.getLogger(DataReserva.class.getName()).log(Level.SEVERE, null, e);

		} catch (MessagingException e) {

			Logger.getLogger(DataReserva.class.getName()).log(Level.SEVERE, null, e);

		}

	}
}
