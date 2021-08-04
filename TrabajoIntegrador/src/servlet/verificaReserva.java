package servlet;

import java.io.IOException;
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
import logic.Login;

@WebServlet("/verificaReserva")
public class verificaReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public verificaReserva() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario us = (Usuario) request.getSession().getAttribute("usuario");
		Login ctrlLogin = new Login();
		Rol r = new Rol();
		r.setIdRol(1);
		try {
			if (us.hasRol(r)) {
				LinkedList<Reserva> reservas = ctrlLogin.getReservas();
				request.getSession().setAttribute("reservas", reservas);
				request.getRequestDispatcher("WEB-INF/MenuEmpleado/VerificarReservas.jsp").forward(request, response);
			} else {
				r.setIdRol(2);
				if (us.hasRol(r)) {
					RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/MenuCliente/MenuCliente.jsp");
					request.setAttribute("errormsg", "No tienes acceso a esta página");
					rd.forward(request, response);
				}
			}

		}  catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("errormsg", "Error inesperado");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Usuario u = (Usuario) request.getSession().getAttribute("usuario");
			Login ctrlLogin = new Login();
			Reserva re = new Reserva();
			String motivo = request.getParameter("motivoCancelacion");
			int idr = Integer.parseInt(request.getParameter("idreserva"));
			System.out.println(motivo);
			re.setIdReserva(idr);
			re.setEstado("Cancelada");
			re.setMotivoCancelacion(motivo);
			ctrlLogin.cancelarReserva(re);
			ctrlLogin.emailCancelacion(re, u);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/MenuEmpleado/MenuEmpleado.jsp");
			request.setAttribute("okmsg", "Reserva cancelada con éxito");
			rd.forward(request, response);
		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("errormsg", "Error inesperado");
			rd.forward(request, response);
		}
	}

}
