package entities;

public class Categoria {
	private int idCategoria;
	private String descripcion;
	private Double precioxDia;
	
	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecioxDia() {
		return precioxDia;
	}

	public void setPrecioxDia(Double precioxDia) {
		this.precioxDia = precioxDia;
	}

	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria + ", descripcion=" + descripcion + ", precioxDia=" + precioxDia
				+ "]";
	}
	
	
	

}
