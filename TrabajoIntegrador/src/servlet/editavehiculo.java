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
@WebServlet({ "/editavehiculo", "/EditaVehiculo", "/EDITAVEHICULO" })
public class editavehiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public editavehiculo() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Login ctrlLogin = new Login();
		Vehiculo ve = new Vehiculo();
		Double kms = Double.parseDouble(request.getParameter("kms"));
		int idv = Integer.parseInt(request.getParameter("idvehiculo"));
		ve.setIdVehiculo(idv);
		ve.setKm(kms);

		ctrlLogin.updateKm(ve);

		request.getRequestDispatcher("WEB-INF/MenuEmpleado/MenuEmpleado.jsp").forward(request, response);

	}
}
