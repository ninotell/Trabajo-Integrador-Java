package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DataRol;
import entities.Documento;
import entities.Rol;
import entities.Usuario;
import logic.Login;

/**
 * Servlet implementation class Signin
 */
@WebServlet({ "/confirmaRegistro", "/CONFIRMAREGISTRO", "/ConfirmaRegistro" })
public class confirmaRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public confirmaRegistro() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataRol dr = new DataRol();
		Usuario u = new Usuario();
		Documento d = new Documento();
		Login ctrlLogin = new Login();
		u.setDocumento(d);
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String tipodoc = request.getParameter("tipodoc");
		String nrodoc = request.getParameter("nrodoc");
		String telefono = request.getParameter("telefono");
		String direccion = request.getParameter("direccion");
		String email = request.getParameter("correoelectronico");
		String password = request.getParameter("password");

		u.setNombre(nombre);
		u.setApellido(apellido);
		d.setTipo(tipodoc);
		d.setNro(nrodoc);
		u.setTel(telefono);
		u.setDireccion(direccion);
		u.setEmail(email);
		u.setPassword(password);

		Rol r = new Rol();
		r.setIdRol(2); // Se asigna por defecto rol de usuario
		r = dr.getById(r);
		u.addRol(r);

		try {

			u.addRol(r);
			ctrlLogin.newUsuario(u);
			dr.addRol(r, u);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("newuser", "true");
			rd.forward(request, response);

		} catch (Exception e) {
			if (e instanceof java.sql.SQLIntegrityConstraintViolationException) {

			RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
			request.setAttribute("errorusuario", "true");
			rd.include(request, response);
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				request.setAttribute("errormsg", "true");
				rd.include(request, response);
			}
		}

	}
}
