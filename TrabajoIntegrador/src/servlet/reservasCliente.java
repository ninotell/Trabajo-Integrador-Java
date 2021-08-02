package servlet;

import java.io.IOException;
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
import logic.Login;

/**
 * Servlet implementation class reservasCliente
 */
@WebServlet("/reservasCliente")
public class reservasCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public reservasCliente() {
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
		Login ctrlLogin = new Login();
		Rol r = new Rol();
		r.setIdRol(2);
		try {
			if (us.hasRol(r)) {
				LinkedList<Reserva> reservasUsuario = ctrlLogin.listaReservasUsuario(us);
				request.getSession().setAttribute("reservasUsuario", reservasUsuario);
				request.getRequestDispatcher("WEB-INF/MenuCliente/reservasCliente.jsp").forward(request, response);
			} else {
				r.setIdRol(1);
				if (us.hasRol(r)) {
					RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/MenuEmpleado/MenuEmpleado.jsp");
					request.setAttribute("errormsg", "No tienes acceso a esta página");
					rd.forward(request, response);
				}
			}

		}  catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("errormsg", "Error inesperado");
			rd.forward(request, response);
		}

	}
}
