package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Categoria;
import entities.Vehiculo;
import logic.Login;

/**
 * Servlet implementation class reservaVistaPrevia
 */
@WebServlet("/reservaVistaPrevia")
public class reservaVistaPrevia extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public reservaVistaPrevia() {
		super();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Vehiculo v = new Vehiculo();
			Categoria c = new Categoria();
			Login ctrlLogin = new Login();
			int idv = Integer.parseInt(request.getParameter("idvehiculo"));
			v.setIdVehiculo(idv);
			v = ctrlLogin.getVehiculoById(v);
			c = ctrlLogin.getCategoriaVehiculo(v);
			request.getSession().setAttribute("categoria", c);
			request.getSession().setAttribute("vehiculo", v);
			request.getRequestDispatcher("WEB-INF/MenuCliente/confirmaReserva.jsp").forward(request, response);
		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("errormsg", "Error inesperado");
			rd.forward(request, response);
		}
	}

}
