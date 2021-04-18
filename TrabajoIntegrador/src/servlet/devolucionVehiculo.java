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
import logic.Login;

/**
 * Servlet implementation class devolucionVehiculo
 */
@WebServlet("/devolucionVehiculo")
public class devolucionVehiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public devolucionVehiculo() {
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
		r.setEstado("retirado");
		LinkedList<Reserva> reservasRetiradas = ctrlLogin.listaReservasUsuario(r, u);
		request.getSession().setAttribute("reservasRetiradas", reservasRetiradas);
		System.out.println(u.toString());
		System.out.println(reservasRetiradas.toString());
		
		request.getRequestDispatcher("WEB-INF/MenuCliente/devolucionVehiculo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
