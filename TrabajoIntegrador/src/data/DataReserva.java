package data;

import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
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
import entities.Documento;
import entities.Reserva;
import entities.Usuario;
import entities.Vehiculo;
import logic.Login;

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
					"update reserva r set r.estado=?,r.fechaCancelacion=current_timestamp(), r.motivoCancelacion=? where r.idReserva=?");
			stmt.setString(1, r.getEstado());
			stmt.setString(2, r.getMotivoCancelacion());
			stmt.setInt(3, r.getIdReserva());
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
					"update reserva r set r.estado=? where r.idReserva=?");
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
			props.setProperty("mail.smtp.host", "smtp-relay.sendinblue.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.auth", "true");

			Session session = Session.getDefaultInstance(props);

			String correoRemitente = "rentsmart.alquileautos@gmail.com";
			String passwordRemitente = "2q7YVvH3XwcQAT9B";
			String correoReceptor = u.getEmail();
			String asunto = "CANCELACION RESERVA";
			String mensaje = ("<div style=\"max-width: 400px;\">"
			+ "<div style=\"border-radius:10px 10px 10px 10px;border:0px solid #000000;margin-top:5p;margin-bottom:5px;padding:10px 10px 5px 10px;font-weight:normal;font-size:12px;color:#ffffff;background-color:#ff0000\">"
			+ "<div style=\"font-size:16px;font-weight:bold;margin-bottom:10px\"> Reserva cancelada con éxito. ID de reserva: "  + r.getIdReserva() + "</div>"
			+ "</div>"
			+ "</div>");
			String nombreRemitente = "Rent Smart";

			MimeMessage message = new MimeMessage(session);
			try {
				message.setFrom(new InternetAddress(correoRemitente, nombreRemitente));
			} catch (UnsupportedEncodingException e) {

				e.printStackTrace();
			}
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
			message.setSubject(asunto);
			message.setContent(mensaje, "text/html; charset=UTF-8");

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
		Login ctrlLogin = new Login();
		Vehiculo v = new Vehiculo();
		Categoria c = new Categoria();
		v = ctrlLogin.getVehiculoByReserva(r);
		c = ctrlLogin.getCategoriaVehiculo(v);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
		String fechadesde = dateFormat.format(r.getFechaRetiro()); 
		String fechahasta = dateFormat.format(r.getFechaDevolucion()); 
		long diff = r.getFechaDevolucion().getTime() - r.getFechaRetiro().getTime();
		int dias = Math.round((diff / (1000 * 60 * 60 * 24)));
		Double precio = c.getPrecioxDia() * dias;
		if (dias > 20) {
			precio = precio * 0.85;
		} 
		try {
			
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", "smtp-relay.sendinblue.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.auth", "true");

			Session session = Session.getDefaultInstance(props);
			

			String correoRemitente = "rentsmart.alquileautos@gmail.com";
			String passwordRemitente = "2q7YVvH3XwcQAT9B";
			String correoReceptor = u.getEmail();
			String asunto = "CONFIRMACION RESERVA";
			String mensaje = ("<h2>¡Felicitaciones ya tenes tu reserva hecha!</h2>"
					+ "<div>"
					+ "<div style=\"border-radius:10px 10px 10px 10px;border:0px solid #000000;margin-top:5p;margin-bottom:5px;padding:10px 10px 5px 10px;font-weight:normal;font-size:12px;color:#ffffff;background-color:#414141\">"
					+ "<div style=\"font-size:16px;margin-bottom:10px\"> Reserva ID: " + r.getIdReserva() + " por $" + precio + "</div>"
					+ "<p style=\"color:white;font-weight:bold\"> Responsable de reseva: " + u.getNombre() + " " + u.getApellido() + ".</p>"
					+ "<p style=\"color:white;font-weight:normal\"> DNI o CUIT: " + u.getDocumento().getNro() + ".</p>"
					+ "<div style=\"border-radius:10px 10px 10px 10px;border:0px solid #000000;margin-top:5p;margin-bottom:5px;padding:10px 10px 5px 10px;font-weight:normal;font-size:12px;color:#ffffff;background-color:#36783c\">"
					+ "<div style=\"font-size:16px;margin-bottom:10px\"> DETALLES DE RESERVA </div>"
					+ "<p style=\"color:white;font-weight:bold\">" + v.getMarca() + " " + v.getModelo() + ", modelo: "  + v.getAnio() + ", patente: " + v.getPatente()+ "</p>"
					+ "<p style=\"color:white;font-weight:normal\">Fecha de retiro: " + fechadesde + "   -    Fecha de devolución: " + fechahasta + ".</p>"
					+ "<div style=\"border-radius:10px 10px 10px 10px;border:0px solid #000000;margin-top:5p;margin-bottom:5px;padding:10px 10px 5px 10px;font-weight:normal;font-size:12px;color:#ffffff;background-color:#ff0000\">"
					+ "<div style=\"font-size:16px;margin-bottom:10px\"> ¡IMPORTANTE! </div>"
					+ "<p style=\"color:white;font-weight:bold\">La reserva se abona al retirar el vehiculo, presentándose con el celular</p>"
					+ "<p style=\"color:white;font-weight:normal\">Gracias por confiar en nosotros, esperamos que tenga una experiencia única.</p>"
					+ "</div>"
					+ "</div>");
			String nombreRemitente = "Rent Smart";

			MimeMessage message = new MimeMessage(session);
			try {
				message.setFrom(new InternetAddress(correoRemitente, nombreRemitente));
			} catch (UnsupportedEncodingException e) {

				e.printStackTrace();
			}
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
			message.setSubject(asunto);
			message.setContent(mensaje, "text/html; charset=UTF-8");

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
	
	public LinkedList<Reserva> getAll() {
		Reserva r = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<Reserva> reservas = new LinkedList<>();
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT * FROM reserva_usuario_vehiculo ruv\r\n"
					+ "inner join reserva r on r.idReserva = ruv.id_reserva\r\n"
					+ "inner join vehiculo v on v.idVehiculo = ruv.id_vehiculo\r\n"
					+ "inner join usuario u on u.idUsuario = ruv.id_usuario");
			rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					r = new Reserva();
					r.setUsuario(new Usuario());
					r.setVehiculo(new Vehiculo());
					r.getUsuario().setDocumento(new Documento());
					r.setIdReserva(rs.getInt("idReserva"));
					r.setFechaReserva(rs.getTimestamp("fechaReserva"));
					r.setFechaRetiro(rs.getDate("fechaRetiro"));
					r.setFechaDevolucion(rs.getDate("fechaDevolucion"));
					r.setFechaCancelacion(rs.getDate("fechaCancelacion"));
					r.setEstado(rs.getString("estado"));
					r.setMotivoCancelacion(rs.getString("motivoCancelacion"));
					r.getUsuario().setApellido(rs.getString("apellido"));
					r.getUsuario().setNombre(rs.getString("nombre"));
					r.getUsuario().setDireccion(rs.getString("direccion"));
					r.getUsuario().getDocumento().setTipo(rs.getString("tipoDocumento"));
					r.getUsuario().getDocumento().setNro(rs.getString("nroDocumento"));
					r.getUsuario().setEmail(rs.getString("mail"));
					r.getUsuario().setIdUsuario(rs.getInt("idUsuario"));
					r.getUsuario().setPassword(rs.getString("contraseña"));
					r.getUsuario().setTel(rs.getString("telefono"));
					r.getVehiculo().setAnio(rs.getInt("año"));
					r.getVehiculo().setFoto(rs.getString("foto"));
					r.getVehiculo().setIdVehiculo(rs.getInt("idVehiculo"));
					r.getVehiculo().setKm(rs.getDouble("km"));
					r.getVehiculo().setMarca(rs.getString("marca"));
					r.getVehiculo().setModelo(rs.getString("modelo"));
					r.getVehiculo().setPatente(rs.getString("patente"));
					r.getVehiculo().setTransmision(rs.getString("transmision"));
					
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
}
