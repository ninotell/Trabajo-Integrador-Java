package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter out = response.getWriter();
    	Login ctrlLogin = new Login();
    	Reserva re = new Reserva();
    	int idr = Integer.parseInt(request.getParameter("idreserva"));
    			
    	re.setIdReserva(idr);
    	re.setEstado("Finalizada");
    	ctrlLogin.retirarVehiculo(re);
    	
    	request.getRequestDispatcher("WEB-INF/MenuEmpleado/MenuEmpleado.jsp").forward(request, response);
	}

}
