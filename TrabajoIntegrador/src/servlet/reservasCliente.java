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
 * Servlet implementation class reservasCliente
 */
@WebServlet("/reservasCliente")
public class reservasCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reservasCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario u = (Usuario)request.getSession().getAttribute("usuario");
		Login ctrlLogin = new Login();
		LinkedList<Reserva> reservasUsuario = ctrlLogin.listaReservasUsuario(u);
		request.getSession().setAttribute("reservasUsuario", reservasUsuario);
//		System.out.println(reservasUsuario.toString());
		request.getRequestDispatcher("WEB-INF/MenuCliente/reservasCliente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
