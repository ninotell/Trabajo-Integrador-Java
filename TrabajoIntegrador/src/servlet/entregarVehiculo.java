package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Reserva;
import logic.Login;

/**
 * Servlet implementation class entregarVehiculo
 */
@WebServlet("/entregarVehiculo")
public class entregarVehiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public entregarVehiculo() {
        super();
        // TODO Auto-generated constructor stub
    }

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	Login ctrlLogin = new Login();
    	Reserva re = new Reserva();
    	int idr = Integer.parseInt(request.getParameter("idreserva"));
    			
    	re.setIdReserva(idr);
    	re.setEstado("Retirada");
    	ctrlLogin.retirarVehiculo(re);
    	
    	request.getRequestDispatcher("WEB-INF/MenuEmpleado/MenuEmpleado.jsp").forward(request, response);
    	
    	


    }
}
