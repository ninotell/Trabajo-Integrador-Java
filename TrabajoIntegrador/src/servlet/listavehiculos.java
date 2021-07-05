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
 * Servlet implementation class listavehiculos
 */
@WebServlet("/listavehiculos")
public class listavehiculos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listavehiculos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario us = (Usuario)request.getSession().getAttribute("usuario");
		Rol r = new Rol();
		Rol rr = new Rol();
		r.setIdRol(1);
		rr.setIdRol(2);
		request.setAttribute("errormsg", "");
	try {	
		if (us.hasRol(r)) {
			request.getRequestDispatcher("WEB-INF/MenuEmpleado/ListaVehiculos.jsp").forward(request, response);
		}
		else { if (us.hasRol(rr)) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/MenuCliente/MenuCliente.jsp");
			request.setAttribute("errormsg", "true");
			rd.forward(request, response);		
			  }
	}
	
		} catch (java.lang.NullPointerException e) {
		RequestDispatcher rd = request.getRequestDispatcher("index.html");
		rd.forward(request, response);

	}		
	}
	



}
