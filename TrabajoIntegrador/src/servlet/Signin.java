package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;
import java.util.Iterator;
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario u = new Usuario();
		LinkedList<Categoria> categorias= new LinkedList<>();
		Login ctrlLogin = new Login();
		PrintWriter out = response.getWriter();		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		categorias = ctrlLogin.listaCategorias();
		
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
				out.println("<script>");
				out.println("alert(\" Usuario y/o contraseña incorrectos\")");
				out.println("</script>");
	            RequestDispatcher rd=request.getRequestDispatcher("index.html");
	            rd.include(request,response);
			}
			else {
				
			if (u.hasRol(r)) {
				request.getSession().setAttribute("usuario", u);
				request.getSession().setAttribute("listaCategorias", categorias);
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
