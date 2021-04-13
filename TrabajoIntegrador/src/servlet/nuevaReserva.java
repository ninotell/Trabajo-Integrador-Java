package servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Categoria;
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
		Login ctrlLogin = new Login ();
		String fechadesde = request.getParameter("fechadesde");
		String idCat = request.getParameter("categoria");
		System.out.println(idCat);		
//		String patente = request.getParameter("patente");
//		String marca = request.getParameter("marca");
//		String modelo = request.getParameter("modelo");
//		String transmision = request.getParameter("transmision");
//		String km = request.getParameter("km");
//		String año = request.getParameter("año");
//		String idCat = request.getParameter("categoria");
//		
//		v.setPatente(patente);
//		v.setMarca(marca);
//		v.setModelo(modelo);
//		v.setAnio(Integer.parseInt(año));
//		v.setTransmision(transmision);
//		v.setKm(Double.parseDouble(km));
//		c.setIdCategoria(Integer.parseInt(idCat));		
				
		request.getRequestDispatcher("WEB-INF/MenuEmpleado/MenuEmpleado.jsp").forward(request, response);
	}

}
