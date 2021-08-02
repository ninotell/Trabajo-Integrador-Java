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

@WebServlet("/listavehiculos")
public class listavehiculos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public listavehiculos() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Usuario us = (Usuario)request.getSession().getAttribute("usuario");
		Rol r = new Rol();
		Rol rr = new Rol();
		r.setIdRol(1);
		rr.setIdRol(2);
	try {	
		if (us.hasRol(r)) {
			request.getRequestDispatcher("WEB-INF/MenuEmpleado/ListaVehiculos.jsp").forward(request, response);
		}
		else { if (us.hasRol(rr)) {
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
