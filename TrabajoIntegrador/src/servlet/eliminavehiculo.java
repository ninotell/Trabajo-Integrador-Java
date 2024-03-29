package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Vehiculo;
import logic.Login;

@WebServlet({ "/eliminavehiculo", "/EliminaVehiculo", "/ELIMINAVEHICULO" })
public class eliminavehiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public eliminavehiculo() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Login ctrlLogin = new Login();
		Vehiculo ve = new Vehiculo();
		int idv = Integer.parseInt(request.getParameter("idvehiculo"));

		ve.setIdVehiculo(idv);
		try {
			ctrlLogin.deleteVehiculo(ve);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/MenuEmpleado/MenuEmpleado.jsp");
			request.setAttribute("okmsg", "Vehiculo id: " + idv + " eliminado con �xito");
			rd.forward(request, response);
		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("errormsg", "Error inesperado");
			rd.forward(request, response);
		}


	}

}
