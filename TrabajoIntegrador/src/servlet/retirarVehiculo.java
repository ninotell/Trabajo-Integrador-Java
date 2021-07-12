package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Reserva;
import entities.Rol;
import entities.Usuario;
import entities.Vehiculo;
import logic.Login;

/**
 * Servlet implementation class retirarVehiculo
 */
@WebServlet("/retirarVehiculo")
public class retirarVehiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public retirarVehiculo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		Rol r = new Rol();
		Rol rr = new Rol();
		r.setIdRol(1);
		rr.setIdRol(2);
		try {
			if (us.hasRol(r)) {
				request.getRequestDispatcher("WEB-INF/MenuEmpleado/EntregarVehiculo.jsp").forward(request, response);
			} else {
				if (us.hasRol(rr)) {
					RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/MenuCliente/MenuCliente.jsp");
					request.setAttribute("errormsg", "true");
					rd.forward(request, response);
				}
			}
		} catch (java.lang.NullPointerException e) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("errormsg", "true");
			rd.forward(request, response);
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Login ctrlLogin = new Login();
		int idr = Integer.parseInt(request.getParameter("idReserva"));
		Reserva r = new Reserva();
		r.setIdReserva(idr);
		r = ctrlLogin.getReservaById(r);
		if (r == null) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/MenuEmpleado/EntregarVehiculo.jsp");
			request.setAttribute("errorReserva", "true");
			rd.forward(request, response);
		} else {
			request.getSession().setAttribute("reserva", r);
			request.getRequestDispatcher("WEB-INF/MenuEmpleado/ReservaParaEntregar.jsp").forward(request, response);
		}

	}

}
