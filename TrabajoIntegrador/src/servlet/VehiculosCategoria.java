package servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Categoria;
import entities.Vehiculo;
import logic.Login;

/**
 * Servlet implementation class VehiculosCategoria
 */
@WebServlet("/VehiculosCategoria")
public class VehiculosCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VehiculosCategoria() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Categoria c = new Categoria();
		Login ctrlLogin = new Login();
		LinkedList<Vehiculo> vehiculos = new LinkedList<Vehiculo>();
		c.setIdCategoria(Integer.parseInt(request.getParameter("idcategoria")));
		try {
			vehiculos = ctrlLogin.getByCategoria(c);
			request.getSession().setAttribute("vehiculos", vehiculos);
			request.getRequestDispatcher("WEB-INF/MenuCliente/vehiculosCategoria.jsp").forward(request, response);
			
		}catch (java.lang.NullPointerException e) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/MenuCliente/MenuCliente.jsp");
			request.setAttribute("errorvehiculocat", "true");
			rd.forward(request, response);
		}catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("errormsg", "true");
			rd.forward(request, response);
		}
	}

}
