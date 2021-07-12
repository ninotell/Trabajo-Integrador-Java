package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Categoria;
import entities.Reserva;
import entities.Vehiculo;
import logic.Login;

/**
 * Servlet implementation class detalleReserva
 */
@WebServlet("/detalleReserva")
public class detalleReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public detalleReserva() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Login ctrlLogin = new Login();
		Vehiculo v = new Vehiculo();
		Reserva r = new Reserva();
		Categoria c = new Categoria();
		try {
			int idr = Integer.parseInt(request.getParameter("idreserva"));
			r.setIdReserva(idr);
			r = ctrlLogin.getReservaById(r);
			v = ctrlLogin.getVehiculoByReserva(r);
			c = ctrlLogin.getCategoriaVehiculo(v);
			request.getSession().setAttribute("categoria", c);
			request.getSession().setAttribute("vehiculo", v);
			request.getSession().setAttribute("reserva", r);
			request.getRequestDispatcher("WEB-INF/MenuCliente/detalleReserva.jsp").forward(request, response);
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/home");
		} 
	}

}
