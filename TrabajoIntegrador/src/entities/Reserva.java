package entities;

import java.util.Date;

public class Reserva {

	private int idReserva;
	private Date fechaReserva;
	private Date fechaRetiro;
	private Date fechaDevolucion;
	private Date fechaCancelacion;
	
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	public Date getFechaReserva() {
		return fechaReserva;
	}
	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}
	public Date getFechaRetiro() {
		return fechaRetiro;
	}
	public void setFechaRetiro(Date fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}
	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}
	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
	public Date getFechaCancelacion() {
		return fechaCancelacion;
	}
	public void setFechaCancelacion(Date fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}
	
	@Override
	public String toString() {
		return "Reserva [Reserva=" + idReserva + ", fechaReserva=" + fechaReserva + ", fechaRetiro=" + fechaRetiro
				+ ", fechaDevolucion=" + fechaDevolucion + ", fechaCancelacion=" + fechaCancelacion + "]";
	}
	
	

}
