package entities;

public class Rol {

	private int idRol;
	private String descripcion;
	
	
	public int getIdRol() {
		return idRol;
	}
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "Rol [Rol Numero=" + idRol + ", descripcion=" + descripcion + "]";
	}
	
	
}
