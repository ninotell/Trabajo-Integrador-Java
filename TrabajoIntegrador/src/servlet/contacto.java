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
 * Servlet implementation class contacto
 */
@WebServlet("/contacto")
public class contacto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public contacto() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		Rol r = new Rol();
		r.setIdRol(2);
		try {
			if (us.hasRol(r)) {
				request.getRequestDispatcher("WEB-INF/MenuCliente/contacto.jsp").forward(request, response);
			} else {
				r.setIdRol(1);
				if (us.hasRol(r)) {
					RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/MenuEmpleado/MenuEmpleado.jsp");
					request.setAttribute("errormsg", "true");
					rd.forward(request, response);
				}
			}
		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("errormsg", "Error inesperado");
			rd.forward(request, response);
		}
	}

}
