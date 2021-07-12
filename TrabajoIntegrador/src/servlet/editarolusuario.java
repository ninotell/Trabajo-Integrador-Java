package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Rol;
import entities.Usuario;
import entities.Vehiculo;
import logic.Login;

/**
 * Servlet implementation class Signin
 */
@WebServlet({ "/editarolusuario", "/EditaRolUsuario", "/EDITAROLUSUARIO" })
public class editarolusuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public editarolusuario() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		Rol r = new Rol();
		Rol rr = new Rol();
		r.setIdRol(1);
		rr.setIdRol(2);
		try {
			if (us.hasRol(r)) {
				request.getRequestDispatcher("WEB-INF/MenuEmpleado/AsignarRol.jsp").forward(request, response);
			} else {
				if (us.hasRol(rr)) {
					RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/MenuCliente/MenuCliente.jsp");
					request.setAttribute("errormsg", "true");
					rd.forward(request, response);
				}
			}

		} catch (java.lang.NullPointerException e) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Login ctrlLogin = new Login();
		Usuario us = new Usuario();
		Rol r = new Rol();
		Rol rr = new Rol();
		r.setIdRol(1);
		rr.setIdRol(2);
		int idu = Integer.parseInt(request.getParameter("idusuario"));
		us.setIdUsuario(idu);
		us = ctrlLogin.getUserById(us);
		try {

			if (us.hasRol(r)) {
				ctrlLogin.updateRol(us, rr);
			} else {
				if (us.hasRol(rr)) {
					ctrlLogin.updateRol(us, r);
				}
			}
		} catch (SQLException e) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("errormsg", "true");
			rd.forward(request, response);
		}

		request.getRequestDispatcher("WEB-INF/MenuEmpleado/AsignarRol.jsp").forward(request, response);

	}

}
