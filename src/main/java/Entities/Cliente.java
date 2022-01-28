package Entities;

public class Cliente {

	private String nombreCliente;
	private String apellidoClienta;
	private int nroCliente;
	private String telefono;
	private String tipoPermisos;
	private String password;
	private String repetirPassword;
	
	
	
	public String getRepetirPassword() {
		return repetirPassword;
	}
	public void setRepetirPassword(String repetirPassword) {
		this.repetirPassword = repetirPassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTipoPermisos() {
		return tipoPermisos;
	}
	public void setTipoPermisos(String tipoPermisos) {
		this.tipoPermisos = tipoPermisos;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getApellidoClienta() {
		return apellidoClienta;
	}
	public void setApellidoClienta(String apellidoClienta) {
		this.apellidoClienta = apellidoClienta;
	}
	public int getNroCliente() {
		return nroCliente;
	}
	public void setNroCliente(int nroCliente) {
		this.nroCliente = nroCliente;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	@Override
	public String toString() {
		return "Cliente [nombreCliente=" + nombreCliente + ", apellidoClienta=" + apellidoClienta + ", nroCliente="
				+ nroCliente + ", telefono=" + telefono + ", tipoPermisos=" + tipoPermisos + "]";
	}
	
}
