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
@WebServlet({ "/eliminavehiculo", "/EliminaVehiculo", "/ELIMINAVEHICULO"})
public class eliminavehiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

public eliminavehiculo() {
		super();
}

/**
 * @param idv 
 * @param idvv 
 * @param id 
 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
 */

/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Login ctrlLogin = new Login();
	Vehiculo ve = new Vehiculo();
	int idv = Integer.parseInt(request.getParameter("idvehiculo"));
			
	ve.setIdVehiculo(idv);
	ctrlLogin.deleteVehiculo(ve);
	
	request.getRequestDispatcher("WEB-INF/MenuEmpleado/ListaVehiculos.jsp").forward(request, response);
	
	


}

}

