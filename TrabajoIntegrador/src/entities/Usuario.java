package entities;

import java.util.HashMap;

public class Usuario {
	private int idUsuario;
	private Documento documento;
	private String nombre;
	private String apellido;
	private String email;
	private String password;
	private String tel;
	private String direccion;
	private HashMap<Integer, Rol> roles;	
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Usuario() {
		this.roles=new HashMap<>();
	}
	
	public void addRol(Rol r) {
		this.roles.put(r.getIdRol(), r);
	}
	
	public void removeRol(Rol r) {
		this.roles.remove(r.getIdRol());
	}
	
	public boolean hasRol(Rol r) {
		return this.roles.containsKey(r.getIdRol());
	}
	

	public HashMap<Integer, Rol> getRoles() {
		return roles;
	}


	@Override
	public String toString() {
		return "\n\nUsuario [idUsuario=" + idUsuario + ", documento=" + documento + ", nombre=" + nombre + ", apellido="
				+ apellido + ", email=" + email + ", password=" + password + ", tel=" + tel + ", direccion=" + direccion
				+ ", roles=" + roles + "]";
	}
	

	
	
}

