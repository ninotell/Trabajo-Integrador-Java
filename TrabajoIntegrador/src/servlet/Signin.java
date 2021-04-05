package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DataRol;
import entities.Rol;
import entities.Usuario;
import entities.Vehiculo;
import logic.Login;

/**
 * Servlet implementation class Signin
 */
@WebServlet({ "/signin", "/SIGNIN", "/Signin", "/SignIn", "/signIn" })
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("get at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario u = new Usuario();
		Login ctrlLogin = new Login();
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		//validar email y password
		
		
	
		try {
			DataRol dr = new DataRol();
			Rol r = new Rol();
			u.setEmail(email);
			u.setPassword(password);
			r.setIdRol(2);
			r = dr.getById(r);
			u=ctrlLogin.validate(u);
						
			if (u==null) {
				response.setContentType("text/html");
				out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js\"></script>");
				out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>");
	            out.println("<script>");
	            out.println("$(document).ready(function(){"); 
	            out.println("swal ( 'Oops!' , 'Usuario y/o contraseña incorrectos' , 'error' )");
	            out.println("});");
	            out.println("</script>");
	            RequestDispatcher rd=request.getRequestDispatcher("index.html");
	            rd.include(request,response);
			}
			else {
				
			if (u.hasRol(r)) {
				request.getSession().setAttribute("usuario", u);
//				LinkedList<Vehiculo> vehiculos = ctrlLogin.getByAnio(v);
//				request.setAttribute("listaVehiculos", vehiculos);
				request.getRequestDispatcher("WEB-INF/MenuCliente/MenuCliente.jsp").forward(request, response);
				
				
				
			} else { 
				request.getSession().setAttribute("usuario", u);	
				request.getRequestDispatcher("WEB-INF/MenuEmpleado/MenuEmpleado.jsp").forward(request, response);
				}
			}
			}
			catch (NullPointerException n2) {
			n2.printStackTrace();	
			}
		
	}
}

//}
