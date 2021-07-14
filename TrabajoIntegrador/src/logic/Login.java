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
	public Categoria getCategoriaVehiculo(Vehiculo v) {
		return dc.getCategoria(v);		
		
	}
	
	public Usuario getUserById(Usuario u) {
		return du.getById(u);		
		
	}
	
	public void newUsuario(Usuario u) throws SQLException {
	
		du.newUsuario(u);
		
	}
	
	public void updateKm(Vehiculo v) {
		
		dv.updateKm(v);
		
	}
	public void newVehiculo(Vehiculo v, Categoria c) throws SQLException {
		dv.newVehiculo(v);
		dc.addCategoria(c, v);
	}
	
	public void deleteVehiculo(Vehiculo v) {
		dc.deleteCategoriaVehiculo(v);
		dv.deleteVehiculo(v); 
				
	}
	public void deleteUsuario(Usuario u) {
		du.deleteUsuario(u);
		dr.deleteRol(u);
		
	}
	
	public void updateRol(Usuario u, Rol r) throws SQLException {
		dr.addRol(r, u);

	}
	public void updatePsw(Usuario u) {
		du.updatePsw(u);	
	}
	
	public LinkedList<Vehiculo> VehiculosDisponibles(Categoria c, Reserva r, Vehiculo v){
		return dre.VehiculosDisponibles(c,r,v);
	}
	
	public void newReserva(Reserva r, Vehiculo vr, Usuario ur) {
		dre.newReserva(r);
		dre.agregaDatosReserva(r, vr, ur);
	}
	
	public LinkedList<Categoria> listaCategorias(){
		return dc.listaCategoria();
	}
	
	public LinkedList<Reserva> listaReservasUsuario(Usuario u){
		return dre.getReservaByUser(u);
	}
	
	public void cancelarReserva(Reserva r) {
		dre.actualizaEstadoReserva(r);	
	}
	public Reserva getReservaById(Reserva r) {
		return dre.getReservaById(r);		
		
	}
	public void retirarVehiculo(Reserva r) {
		dre.retirarVehiculo(r);	
	}
	public void emailCancelacion(Reserva r, Usuario u) {
		dre.emailCancela(r, u);	
	}
	
	public void emailConfirmacion(Reserva r, Usuario u) {
		dre.emailConfirma(r, u);	
	}
	
	public Vehiculo getVehiculoByReserva(Reserva r) {
		return dv.getVehiculoByReserva(r);
	}
}
