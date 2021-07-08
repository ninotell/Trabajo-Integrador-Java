package servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mysql.cj.jdbc.Blob;

import entities.Categoria;
import entities.Rol;
import entities.Usuario;
import entities.Vehiculo;
import logic.Login;


/**
 * Servlet implementation class Signin
 */
@WebServlet({ "/agregavehiculo", "/AgregaVehiculo", "/AGREGAVEHICULO"})
@MultipartConfig
public class Agregavehiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Agregavehiculo() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		Usuario us = (Usuario)request.getSession().getAttribute("usuario");
		Rol r = new Rol();
		Rol rr = new Rol();
		r.setIdRol(1);
		rr.setIdRol(2);
		try { if (us.hasRol(r)) {
			request.getRequestDispatcher("WEB-INF/MenuEmpleado/NewVehiculo.jsp").forward(request, response);
		}
		else { if (us.hasRol(rr)) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/MenuCliente/MenuCliente.jsp");
			request.setAttribute("errormsg", "true");
			rd.forward(request, response);}}} 
		
		catch(java.lang.NullPointerException e){
				RequestDispatcher rd = request.getRequestDispatcher("index.html");
				rd.forward(request, response);
			}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Vehiculo v = new Vehiculo();
		Categoria c = new Categoria();
		Login ctrlLogin = new Login ();
		String patente = request.getParameter("patente");
		String marca = request.getParameter("marca");
		String modelo = request.getParameter("modelo");
		String transmision = request.getParameter("transmision");
		String km = request.getParameter("km");
		String año = request.getParameter("año");
		String idCat = request.getParameter("categoria");
		Part part = request.getPart("fotovehiculo");
		InputStream foto = part.getInputStream();
		
		
		v.setPatente(patente);
		v.setMarca(marca);
		v.setModelo(modelo);
		v.setAnio(Integer.parseInt(año));
		v.setTransmision(transmision);
		v.setKm(Double.parseDouble(km));
		c.setIdCategoria(Integer.parseInt(idCat));
		v.setFoto(foto);
		
		ctrlLogin.newVehiculo(v, c);
		
		request.getRequestDispatcher("WEB-INF/MenuEmpleado/MenuEmpleado.jsp").forward(request, response);
		
	

	}

	private static String getSubmittedFileName(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	            return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
	        }
	    }
	    return null;
	}
  
}
