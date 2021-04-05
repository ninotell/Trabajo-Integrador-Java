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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		Login ctrlLogin = new Login();
		Usuario us = (Usuario)request.getSession().getAttribute("usuario");
		Rol r = new Rol();
		r.setIdRol(1);
		if (us.hasRol(r)) {
			request.getRequestDispatcher("WEB-INF/MenuEmpleado/ListaVehiculos.jsp").forward(request, response);
		}
		else {
			response.setContentType("text/html");
			out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js\"></script>");
			out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>");
            out.println("<script>");
            out.println("$(document).ready(function(){"); 
            out.println("swal ( 'Oops!' , 'No tienes acceso a esta página' , 'error' )");
            out.println("});");
            out.println("</script>");
            RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/MenuCliente/MenuCliente.jsp");
            rd.include(request,response);}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
