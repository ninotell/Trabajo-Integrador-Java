package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DataRol;
import entities.Categoria;
import entities.Rol;
import entities.Usuario;
import logic.Login;

/**
 * Servlet implementation class home
 */
@WebServlet("/home")
public class home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public home() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Login ctrlLogin = new Login();
		LinkedList<Categoria> categorias = new LinkedList<>();
		try {
			categorias = ctrlLogin.listaCategorias();
			DataRol dr = new DataRol();
			Rol r = new Rol();
			r.setIdRol(2);
			r = dr.getById(r);
			Usuario u = (Usuario) request.getSession().getAttribute("usuario");

			if (u.hasRol(r)) {
				request.getSession().setAttribute("usuario", u);
				request.getSession().setAttribute("listaCategorias", categorias);
				request.getRequestDispatcher("WEB-INF/MenuCliente/MenuCliente.jsp").forward(request, response);

			} else {
				request.getSession().setAttribute("usuario", u);
				request.getRequestDispatcher("WEB-INF/MenuEmpleado/MenuEmpleado.jsp").forward(request, response);
			}

		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("errormsg", "Error inesperado");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
