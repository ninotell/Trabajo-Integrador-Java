package servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mysql.cj.jdbc.Blob;

import entities.Categoria;
import entities.Rol;
import entities.Usuario;
import entities.Vehiculo;
import logic.Login;

/**
 * Servlet implementation class Signin
 */
@WebServlet({ "/Agregavehiculo", "/agregavehiculo", "/AgregaVehiculo", "/AGREGAVEHICULO" })
@MultipartConfig
public class Agregavehiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Agregavehiculo() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		Rol r = new Rol();
		Rol rr = new Rol();
		r.setIdRol(1);
		try {
			if (us.hasRol(r)) {
				request.getRequestDispatcher("WEB-INF/MenuEmpleado/NewVehiculo.jsp").forward(request, response);
			} else {
				r.setIdRol(2);
				if (us.hasRol(rr)) {
					RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/MenuCliente/MenuCliente.jsp");
					request.setAttribute("errormsg", "true");
					rd.forward(request, response);
				}
			}
		} catch (java.lang.NullPointerException e) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("datosincorrectos", "true");
			rd.forward(request, response);
		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("errormsg", "true");
			rd.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Vehiculo v = new Vehiculo();
			Categoria c = new Categoria();
			Login ctrlLogin = new Login();
			String patente = request.getParameter("patente");
			String marca = request.getParameter("marca");
			String modelo = request.getParameter("modelo");
			String transmision = request.getParameter("transmision");
			String km = request.getParameter("km");
			String anio = request.getParameter("anio");
			String idCat = request.getParameter("categoria");
			String foto = request.getParameter("fotovehiculo");

			v.setPatente(patente);
			v.setMarca(marca);
			v.setModelo(modelo);
			v.setAnio(Integer.parseInt(anio));
			v.setTransmision(transmision);
			v.setKm(Double.parseDouble(km));
			c.setIdCategoria(Integer.parseInt(idCat));
			v.setFoto(foto);

			ctrlLogin.newVehiculo(v, c);
			request.getRequestDispatcher("WEB-INF/MenuEmpleado/MenuEmpleado.jsp").forward(request, response);
		} catch (SQLIntegrityConstraintViolationException e) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/MenuEmpleado/NewVehiculo.jsp");
			request.setAttribute("errorpatente", "true");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("errormsg", "true");
			rd.forward(request, response);
		}

	}

}
