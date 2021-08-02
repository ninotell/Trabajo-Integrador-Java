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

/**
 * Servlet implementation class changePassword
 */
@WebServlet("/changePassword")
public class changePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public changePassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		if (us != null) {
			request.getRequestDispatcher("WEB-INF/passwordChange.jsp").forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("errormsg", "Inicie sesi�n");
			rd.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Login ctrlLogin = new Login();
		Rol r = new Rol();
		r.setIdRol(1);
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		String password = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		us.setPassword(password);
		us = ctrlLogin.validate(us);
		if (us == null) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/passwordChange.jsp");
			request.setAttribute("errormsg", "true");
			rd.forward(request, response);
		} else {
			us.setPassword(newPassword);
			ctrlLogin.updatePsw(us);
			if (us.hasRol(r)) {
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/MenuEmpleado/MenuEmpleado.jsp");
				request.setAttribute("okmsg", "Contrase�a actualizada con �xito");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/MenuCliente/MenuCliente.jsp");
				request.setAttribute("okmsg", "Contrase�a actualizada con �xito");
				rd.forward(request, response);
			}
		}

	}

}
