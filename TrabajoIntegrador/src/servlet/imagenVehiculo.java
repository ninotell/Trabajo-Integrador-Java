package servlet;

import java.io.IOException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DataVehiculo;
import data.DbConnector;

/**
 * Servlet implementation class imagenVehiculo
 */
@WebServlet("/imagenVehiculo")
public class imagenVehiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public imagenVehiculo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idv = Integer.parseInt(request.getParameter("idv"));
		PreparedStatement stmt=null;
		ResultSet rs=null;
		response.setContentType("image/jpeg");
		byte[] b = null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select foto from Vehiculo where idVehiculo=?"
					);
	        stmt.setInt(1, idv);
	        rs = stmt.executeQuery();
	        while (rs.next()) {
	              b = rs.getBytes("foto");
	        }
	        	   InputStream ist = new ByteArrayInputStream(b);
	        	   int tamano = ist.available();
	        	   byte[] imgData = new byte[tamano];
	        	   ist.read(imgData, 0, tamano);
	        	   response.getOutputStream().write(imgData);
	        	   ist.close();
	        	   stmt.close();
	        	   rs.close();

	         
	    } catch (Exception e) {
	        e.printStackTrace();
	        
	    }
		
	}

}
