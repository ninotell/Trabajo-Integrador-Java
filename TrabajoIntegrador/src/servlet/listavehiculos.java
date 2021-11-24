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

import entities.Rol;
import entities.Usuario;
import entities.Vehiculo;
import logic.Login;

@WebServlet("/listavehiculos")
public class listavehiculos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public listavehiculos() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		Vehiculo v = new Vehiculo();
		Login ctrlLogin = new Login();
		v.setPatente("");
		LinkedList<Vehiculo> lv = ctrlLogin.getByPatente(v);
		Rol r = new Rol();
		Rol rr = new Rol();
		r.setIdRol(1);
		rr.setIdRol(2);
		try {
			if (us.hasRol(r)) {
				lv = ctrlLogin.getByPatente(v);
				request.getSession().setAttribute("vehiculosEncontrados", lv);
				request.getRequestDispatcher("WEB-INF/MenuEmpleado/ListaVehiculos.jsp").forward(request, response);
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		Login ctrlLogin = new Login();
		Vehiculo v = new Vehiculo();
		LinkedList<Vehiculo> lv = new LinkedList<Vehiculo>();
		Rol r = new Rol();
		Rol rr = new Rol();
		r.setIdRol(1);
		rr.setIdRol(2);
		try {
			if (us.hasRol(r)) {
				v.setPatente(request.getParameter("patenteBusqueda"));
				lv = ctrlLogin.getByPatente(v);
				request.getSession().setAttribute("vehiculosEncontrados", lv);
				request.getRequestDispatcher("WEB-INF/MenuEmpleado/ListaVehiculos.jsp").forward(request, response);
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
