package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Rol;
import entities.Usuario;
import logic.Login;

@WebServlet("/eliminausuario")
public class eliminausuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public eliminausuario() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		Login ctrlLogin = new Login();
		Usuario u = new Usuario();
		int idu = Integer.parseInt(request.getParameter("idusuario"));
		Rol r = new Rol();
		Rol rr = new Rol();
		r.setIdRol(1);
		rr.setIdRol(2);
		try {
			if (us.hasRol(r)) {
				u.setIdUsuario(idu);
				ctrlLogin.deleteUsuario(u);
				request.getRequestDispatcher("WEB-INF/MenuEmpleado/AsignarRol.jsp").forward(request, response);
			} else {
				if (us.hasRol(rr)) {
					RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/MenuCliente/MenuCliente.jsp");
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

}
