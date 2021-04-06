package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Vehiculo;
import logic.Login;


/**
 * Servlet implementation class Signin
 */
@WebServlet({ "/editavehiculo", "/EditaVehiculo", "/EDITAVEHICULO"})
public class editavehiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

public editavehiculo() {
		super();
}

/**
 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
 */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub

	
	
}

/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	PrintWriter out = response.getWriter();
	Login ctrlLogin = new Login();
	Vehiculo ve = new Vehiculo();
//	ve.setIdVehiculo(Integer.parseInt(request.getParameter("idvehiculo")));
//	ve.setPatente(request.getParameter("patente"));
//	ve.setMarca(request.getParameter("marca"));
//	ve.setTransmision(request.getParameter("transmision"));
//	ve.setModelo(request.getParameter("modelo"));
//	ve.setKm(Double.parseDouble(request.getParameter("km")));
//	ve.setAnio(Integer.parseInt(request.getParameter("anio")));
	Double kms = Double.parseDouble(request.getParameter("kms")); 
	int idv = Integer.parseInt(request.getParameter("idvehiculo"));
	ve.setIdVehiculo(idv);
	ve.setKm(kms);
	
	ctrlLogin.updateKm(ve);
	
	request.getRequestDispatcher("WEB-INF/MenuEmpleado/MenuEmpleado.jsp").forward(request, response);
	
//	String marca = request.getParameter("marca");
//	String transmision = request.getParameter("transmision");
//	String modelo = request.getParameter("modelo");
//	String km = request.getParameter("km");
//	String anio = request.getParameter("anio");
	
	
			
	
	
	


}

}


