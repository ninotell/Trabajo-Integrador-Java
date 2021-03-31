package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Rol;
import entities.Usuario;
import entities.Vehiculo;
import logic.Login;

/**
 * Servlet implementation class Signin
 */
@WebServlet({ "/editarolusuario", "/EditaRolUsuario", "/EDITAROLUSUARIO"})
public class editarolusuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

public editarolusuario() {
		super();
}

/**
 * @param idv 
 * @param idvv 
 * @param id 
 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
 */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	request.getRequestDispatcher("WEB-INF/AsignarRol.jsp").forward(request, response);
	
	
}

/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	PrintWriter out = response.getWriter();
	Login ctrlLogin = new Login();
	Usuario us = new Usuario();
	Rol nr = new Rol();
	int idu = Integer.parseInt(request.getParameter("idusuario"));
	us.setIdUsuario(idu);
//	ve.setKm(kms);
//	
//	ctrlLogin.updateKm(ve);
	
	request.getRequestDispatcher("WEB-INF/MenuCliente.jsp").forward(request, response);
	
//	String marca = request.getParameter("marca");
//	String transmision = request.getParameter("transmision");
//	String modelo = request.getParameter("modelo");
//	String km = request.getParameter("km");
//	String anio = request.getParameter("anio");
	
	
			
	
	
	


}

}

