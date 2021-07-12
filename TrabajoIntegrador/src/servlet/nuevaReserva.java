package servlet;

import java.io.IOException;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
					request.setAttribute("errormsg", "true");
					rd.forward(request, response);
				}
			}
		} catch (java.lang.NullPointerException e) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("datosincorrectos", "true");
			rd.forward(request, response);
		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("errormsg", "true");
			rd.forward(request, response);
		}
		request.getRequestDispatcher("WEB-INF/MenuCliente/nuevaReserva.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Vehiculo v = new Vehiculo();
		Categoria c = new Categoria();
		Reserva r = new Reserva();
		Login ctrlLogin = new Login ();
		int idCat = Integer.parseInt(request.getParameter("categoria"));
		request.getSession().setAttribute("fechadesde", request.getParameter("fechadesde"));
		request.getSession().setAttribute("fechahasta", request.getParameter("fechahasta"));
		Date fechadesde = new Date();
		Date fechahasta = new Date();
		try {
			fechadesde = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("fechadesde"));
			fechahasta = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("fechahasta"));
			String transmision = request.getParameter("transmision");
			r.setFechaRetiro(fechadesde);
			r.setFechaDevolucion(fechahasta);
			c.setIdCategoria(idCat);
			v.setTransmision(transmision);
			LinkedList<Vehiculo> vDisponibles = new LinkedList<>();
			vDisponibles = ctrlLogin.VehiculosDisponibles(c, r, v);
			request.getSession().setAttribute("vDisponibles", vDisponibles);
			
			request.getRequestDispatcher("WEB-INF/MenuCliente/VehiculosDisponibles.jsp").forward(request, response);
		} catch (ParseException e) {
			request.setAttribute("fechaincorrecta", "true");
			request.getRequestDispatcher("WEB-INF/MenuCliente/nuevaReserva.jsp").forward(request, response);
		} 

	}

}
