package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Categoria;
import entities.Vehiculo;
import logic.Login;

/**
 * Servlet implementation class confirmaReserva
 */
@WebServlet("/confirmaReserva")
public class confirmaReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public confirmaReserva() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Vehiculo v = new Vehiculo();
		Categoria c = new Categoria();
		Login ctrlLogin = new Login();
		int idv = Integer.parseInt(request.getParameter("idvehiculo"));
		v.setIdVehiculo(idv);
		v = ctrlLogin.getVehiculoById(v);
		c = ctrlLogin.getCategoriaVehiculo(v);
		System.out.println(v.toString());
		request.getSession().setAttribute("categoria", c);
		request.getSession().setAttribute("vehiculo", v);
		request.getRequestDispatcher("WEB-INF/MenuCliente/confirmaReserva.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
