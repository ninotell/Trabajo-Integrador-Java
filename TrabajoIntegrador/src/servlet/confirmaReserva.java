package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Categoria;
import entities.Reserva;
import entities.Usuario;
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
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Vehiculo v = (Vehiculo)request.getSession().getAttribute("vehiculo");
		Usuario u = (Usuario)request.getSession().getAttribute("usuario");
		Reserva res = new Reserva();
		Integer sumres = 0; //Cantidad de reservas iniciadas (sumres) no puede ser mayor a 1
		Login ctrlLogin = new Login();
		LinkedList<Reserva> reservas = ctrlLogin.listaReservasUsuario(u);
		for (Reserva r : reservas) {
			if (r.getEstado().equals("Iniciada")) {
				sumres = sumres + 1;
			} else {}
		}
		if (sumres == 0) {
			try {
				Date fechadesde = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("fechadesde"));
				Date fechahasta = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("fechahasta"));
				res.setFechaRetiro(fechadesde);
				res.setFechaDevolucion(fechahasta);
				ctrlLogin.newReserva(res, v, u);
		    	ctrlLogin.emailConfirmacion(res, u);
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/MenuCliente/MenuCliente.jsp");
				request.setAttribute("okmsg", "Reserva realizada con éxito");
				rd.forward(request, response);
			}catch (ParseException p) {
				//No puede fallar porque la fecha ya viene validada del paso anterior
				p.printStackTrace();
			}
			
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/MenuCliente/MenuCliente.jsp");
			request.setAttribute("errormsg", "Ya tienes una reserva iniciada");
			// response.setStatus("http error code");
			rd.forward(request, response);	
		}
	}

}
