package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Reserva;
import entities.Rol;
import entities.Usuario;
import entities.Vehiculo;
import logic.Login;

/**
 * Servlet implementation class retirarVehiculo
 */
@WebServlet("/retirarVehiculo")
public class retirarVehiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public retirarVehiculo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		Usuario us = (Usuario)request.getSession().getAttribute("usuario");
		Rol r = new Rol();
		Rol rr = new Rol();
		r.setIdRol(1);
		rr.setIdRol(2);
		if (us.hasRol(r)) {
			request.getRequestDispatcher("WEB-INF/MenuEmpleado/EntregarVehiculo.jsp").forward(request, response);
		}
		else { if (us.hasRol(rr)) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/MenuCliente/MenuCliente.jsp");
			request.setAttribute("errormsg", "true");
			rd.forward(request, response);}}	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	PrintWriter out = response.getWriter();
    	Login ctrlLogin = new Login();
    	int idr = Integer.parseInt(request.getParameter("idReserva"));
    	Reserva re = new Reserva();
    	Reserva r = new Reserva();
    	re.setIdReserva(idr);		
    	r =  ctrlLogin.getReservaById(re);
    	System.out.println(r.toString());
    	request.getSession().setAttribute("reserva", r);
    	request.getRequestDispatcher("WEB-INF/MenuEmpleado/ReservaParaEntregar.jsp").forward(request, response);
		
    }

}
