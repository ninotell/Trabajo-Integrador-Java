package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Categoria;
import entities.Vehiculo;
import logic.Login;


/**
 * Servlet implementation class Signin
 */
@WebServlet({ "/agregavehiculo", "/AgregaVehiculo", "/AGREGAVEHICULO"})
public class Agregavehiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Agregavehiculo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/Editar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Vehiculo v = new Vehiculo();
		Categoria c = new Categoria();
		Login ctrlLogin = new Login ();
		String patente = request.getParameter("patente");
		String marca = request.getParameter("marca");
		String modelo = request.getParameter("modelo");
		String transmision = request.getParameter("transmision");
		String km = request.getParameter("km");
		String a�o = request.getParameter("a�o");
		String idCat = request.getParameter("categoria");
		
		v.setPatente(patente);
		v.setMarca(marca);
		v.setModelo(modelo);
		v.setAnio(Integer.parseInt(a�o));
		v.setTransmision(transmision);
		v.setKm(Double.parseDouble(km));
		c.setIdCategoria(Integer.parseInt(idCat));
		System.out.println(v.toString());
		
		
		ctrlLogin.newVehiculo(v, c);
		
		request.getRequestDispatcher("WEB-INF/MenuEmpleado.jsp").forward(request, response);
		
	

	}
  
}