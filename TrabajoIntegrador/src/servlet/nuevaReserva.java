package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Categoria;
import entities.Reserva;
import entities.Rol;
import entities.Usuario;
import entities.Vehiculo;
import logic.Login;

/**
 * Servlet implementation class nuevaReserva
 */
@WebServlet("/nuevaReserva")
public class nuevaReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public nuevaReserva() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/MenuCliente/nuevaReserva.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Vehiculo v = new Vehiculo();
		Categoria c = new Categoria();
		Reserva r = new Reserva();
		Login ctrlLogin = new Login ();
		int idCat = Integer.parseInt(request.getParameter("categoria"));
		request.getSession().setAttribute("fechadesde", request.getParameter("fechadesde"));
		request.getSession().setAttribute("fechahasta", request.getParameter("fechahasta"));
		Date fechadesde = new Date();
		Date fechahasta = new Date();
		try {
			fechadesde = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("fechadesde"));
			fechahasta = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("fechahasta"));
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		String transmision = request.getParameter("transmision");
		r.setFechaRetiro(fechadesde);
		r.setFechaDevolucion(fechahasta);
		c.setIdCategoria(idCat);
		v.setTransmision(transmision);
		LinkedList<Vehiculo> vDisponibles = new LinkedList<>();
		vDisponibles = ctrlLogin.VehiculosDisponibles(c, r, v);
		request.getSession().setAttribute("vDisponibles", vDisponibles);
		
		request.getRequestDispatcher("WEB-INF/MenuCliente/VehiculosDisponibles.jsp").forward(request, response);
	}

}
