package logic;

import entities.*;

import java.sql.SQLException;
import java.util.LinkedList;

import data.*;


public class Login {
	private DataUsuario du;
	private DataVehiculo dv;
	private DataCategoria dc;
	private DataRol dr;
	private DataReserva dre;
	
	public Login() {
		dv = new DataVehiculo();
		du = new DataUsuario();
		dc = new DataCategoria();
		dr = new DataRol();
		dre = new DataReserva();
	}
	
	public LinkedList<Usuario> listaUsuarios(){
		return du.listaUsuarios();
	}
	
	public Usuario validate(Usuario u) {
		
		return du.getByUser(u);
		
		}
	
	public LinkedList<Vehiculo> getByAnio(Vehiculo v){
		return dv.getByAnio(v);
	}
	
	public Vehiculo getVehiculoById(Vehiculo v) {
		return dv.getById(v);		
		
	}
	
	public void newUsuario(Usuario u) throws SQLException {
	
		du.newUsuario(u);
		
	}
	
	public void updateKm(Vehiculo v) {
		
		dv.updateKm(v);
		
	}
	public void newVehiculo(Vehiculo v, Categoria c) {
		dv.newVehiculo(v);
		dc.addCategoria(c, v);
	}
	
	public void deleteVehiculo(Vehiculo v) {
		dv.deleteVehiculo(v); 
		dc.deleteCategoriaVehiculo(v);
		
	}
	public void deleteUsuario(Usuario u) {
		dr.deleteRol(u);
		du.deleteUsuario(u);
	}
	public void updatePsw(Usuario u) {
		du.updatePsw(u);	
	}
	
	public LinkedList<Vehiculo> VehiculosDisponibles(Categoria c, Reserva r){
		return dre.VehiculosDisponibles(c,r);
	}
	
	public void newReserva(Reserva r, Vehiculo vr, Usuario ur) {
		dre.newReserva(r);
		dre.agregaDatosReserva(vr, ur);
	}
	
	
	
	
}
