package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		PrintWriter out = response.getWriter();
		Login ctrlLogin = new Login();
		Usuario us = new Usuario();
		int idu = Integer.parseInt(request.getParameter("idusuario"));

		us.setIdUsuario(idu);
		try {
			ctrlLogin.deleteUsuario(us);
		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("errormsg", "Error inesperado");
			rd.forward(request, response);
		}

		request.getRequestDispatcher("WEB-INF/MenuEmpleado/AsignarRol.jsp").forward(request, response);
	}

}
