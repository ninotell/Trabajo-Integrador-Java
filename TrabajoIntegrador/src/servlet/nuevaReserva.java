package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Categoria;
import entities.Reserva;
import entities.Rol;
import entities.Usuario;
import entities.Vehiculo;
import logic.Login;

@WebServlet("/nuevaReserva")
public class nuevaReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public nuevaReserva() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		Rol r = new Rol();
		r.setIdRol(2);
		try {
			if (us.hasRol(r)) {
				request.getRequestDispatcher("WEB-INF/MenuCliente/nuevaReserva.jsp").forward(request, response);
			} else {
				r.setIdRol(1);
				if (us.hasRol(r)) {
					RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/MenuEmpleado/MenuEmpleado.jsp");
					request.setAttribute("errormsg", "No tienes acceso a esta página");
					rd.forward(request, response);
				}
			}
		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("errormsg", "Error inesperado");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Vehiculo v = new Vehiculo();
		Categoria c = new Categoria();
		Reserva r = new Reserva();
		Login ctrlLogin = new Login();
		int idCat = Integer.parseInt(request.getParameter("categoria"));
		Date fechadesde = new Date();
		Date fechahasta = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String fd = request.getParameter("fechadesde");
		String fh = request.getParameter("fechahasta");
		request.getSession().setAttribute("fechadesde", fd); //Para la confirmación de la reserva
		request.getSession().setAttribute("fechahasta", fh); //Para la confirmación de la reserva
		try {
			fechadesde = format.parse(fd);
			fechahasta = format.parse(fh);
			String transmision = request.getParameter("transmision");
			r.setFechaRetiro(fechadesde);
			r.setFechaDevolucion(fechahasta);
			c.setIdCategoria(idCat);
			v.setTransmision(transmision);
			LinkedList<Vehiculo> vDisponibles = new LinkedList<>();
			vDisponibles = ctrlLogin.VehiculosDisponibles(c, r, v);
			request.getSession().setAttribute("vDisponibles", vDisponibles);

			request.getRequestDispatcher("WEB-INF/MenuCliente/VehiculosDisponibles.jsp").forward(request, response);
		} 
		catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("errormsg", "Error inesperado, intente en unos minutos.");
			rd.forward(request, response);
		}

	}

}
