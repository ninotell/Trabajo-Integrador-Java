package entities;

import java.util.HashMap;

public class Vehiculo {
	private int idVehiculo;
	private String patente;
	private String marca;
	private String modelo;
	private int anio;
	private String transmision;
	private Double km;
	private HashMap<Integer, Categoria> categoria;
	
	
	public int getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}


	public String getPatente() {
		return patente;
	}


	public void setPatente(String patente) {
		this.patente = patente;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getTransmision() {
		return transmision;
	}

	public void setTransmision(String transmision) {
		this.transmision = transmision;
	}

	public Double getKm() {
		return km;
	}

	public void setKm(Double km) {
		this.km = km;
	}
	
	public Vehiculo() {
		this.categoria=new HashMap<>();
	}
	
	public void addCategoria(Categoria c) {
		this.categoria.put(c.getIdCategoria(), c);
	}



	@Override
	public String toString() {
		return "\nVehiculo [idVehiculo=" + idVehiculo + ", patente=" + patente + ", marca=" + marca + ", modelo=" + modelo
				+ ", año=" + anio + ", transmision=" + transmision + ", km=" + km + "]";
	}
	
	
	

}
