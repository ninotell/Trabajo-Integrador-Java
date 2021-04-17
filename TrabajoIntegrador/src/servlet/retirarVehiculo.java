package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Reserva;
import entities.Usuario;
import entities.Vehiculo;
import logic.Login;

/**
 * Servlet implementation class retirarVehiculo
 */
@WebServlet("/retirarVehiculo")
public class retirarVehiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public retirarVehiculo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Login ctrlLogin = new Login();
		Usuario u = (Usuario)request.getSession().getAttribute("usuario");
		Reserva r = new Reserva();
		r.setEstado("iniciada");
		LinkedList<Reserva> reservasIniciadas = ctrlLogin.listaReservasUsuario(r, u);
		request.getSession().setAttribute("reservasIniciadas", reservasIniciadas);
		
		request.getRequestDispatcher("WEB-INF/MenuCliente/reservasIniciadas.jsp").forward(request, response);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
