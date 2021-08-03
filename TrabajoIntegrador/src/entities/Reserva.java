package entities;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

public class Reserva {

	private int idReserva;
	private Timestamp fechaReserva;
	private Date fechaRetiro;
	private Date fechaDevolucion;
	private Date fechaCancelacion;
	private String estado;
	private String motivoCancelacion;
	
	public String getMotivoCancelacion() {
		return motivoCancelacion;
	}
	public void setMotivoCancelacion(String motivoCancelacion) {
		this.motivoCancelacion = motivoCancelacion;
	}
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	public Date getFechaReserva() {
		return fechaReserva;
	}
	public void setFechaReserva(Timestamp fechaReserva) {
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
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	@Override
	public String toString() {
		return "Reserva [Reserva=" + idReserva + ", fechaReserva=" + fechaReserva + ", fechaRetiro=" + fechaRetiro
				+ ", fechaDevolucion=" + fechaDevolucion + ", fechaCancelacion=" + fechaCancelacion + ", estado=" + estado + "]";
	}
	
	

}
