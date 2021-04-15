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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		Login ctrlLogin = new Login();
		PrintWriter out = response.getWriter();
		LinkedList<Categoria> categorias= new LinkedList<>();
		categorias = ctrlLogin.listaCategorias();
		try {
			DataRol dr = new DataRol();
			Rol r = new Rol();
			r.setIdRol(2);
			r = dr.getById(r);
			Usuario u = (Usuario)request.getSession().getAttribute("usuario");
						
			if (u==null) {
				response.setContentType("text/html");
				out.println("<script>");
				out.println("alert(\"Inicie sesi�n, por favor\")");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
