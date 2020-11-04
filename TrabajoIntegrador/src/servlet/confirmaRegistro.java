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
@WebServlet({ "/confirmaRegistro", "/CONFIRMAREGISTRO", "/ConfirmaRegistro"})
public class confirmaRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public confirmaRegistro() {
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
		PrintWriter out = response.getWriter();
		DataRol dr = new DataRol();
		Usuario u = new Usuario();
		Documento d = new Documento();
		Login ctrlLogin = new Login ();
		u.setDocumento(d);
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String tipodoc = request.getParameter("tipodoc");
		String nrodoc = request.getParameter("nrodoc");
		String telefono = request.getParameter("direccion");
		String direccion = request.getParameter("direccion");
		String email = request.getParameter("correoelectronico");
		String password = request.getParameter("password");
		String confpassword = request.getParameter("confpassword");
		
		u.setNombre(nombre);
		u.setApellido(apellido);
		d.setTipo(tipodoc);
		d.setNro(nrodoc);
		u.setTel(telefono);
		u.setDireccion(direccion);
		u.setEmail(email);
		u.setPassword(password);
		System.out.println(u.toString());
		
		
		Rol r = new Rol();
		r.setIdRol(2); //Se asigna por defecto rol de usuario
		r = dr.getById(r);
		u.addRol(r);

		
		
	try {
		
		if(password.equals(confpassword)) {
			u.addRol(r);
			ctrlLogin.newUsuario(u);
			dr.addRol(r, u);
			response.setContentType("text/html");
			out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js\"></script>");
			out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>");
            out.println("<script>");
            out.println("$(document).ready(function(){"); 
            out.println("swal ( 'Excelente!' , 'Bienvenido a nuestro sistema, ingrese' , 'success' )");
            out.println("});");
            out.println("</script>");
            RequestDispatcher rd=request.getRequestDispatcher("index.html");
            rd.include(request,response);
			
		}
		else {
			response.setContentType("text/html");
			out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js\"></script>");
			out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>");
            out.println("<script>");
            out.println("$(document).ready(function(){"); 
            out.println("swal ( 'Error!' , 'Las contraseñas no coinciden' , 'error' )");
            out.println("});");
            out.println("</script>");
            RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/Register.jsp");
            rd.include(request,response);
		}
	} catch( SQLException s) {
			if (s instanceof SQLIntegrityConstraintViolationException)
			{
////			JOptionPane.showMessageDialog(null,"Inténtelo otra vez", "Usuario ya registrado",JOptionPane.WARNING_MESSAGE);
				
				
				response.setContentType("text/html");
				out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js\"></script>");
				out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>");
                out.println("<script>");
                out.println("$(document).ready(function(){"); 
                out.println("swal ( 'Error!' , 'Usuario ya registrado' , 'error' )");
                out.println("});");
                out.println("</script>");
                RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/Register.jsp");
                rd.include(request,response);
			
			}
			
		}
		
		

			
			
	}
}

//}
