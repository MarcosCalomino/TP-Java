package Entities;

public class Precio {

	private double precio;
	private int nroPrecio;
	private String disabledDate;
		
	public Precio() {
		
	}
	public Precio(int nroPrecio, double precio, String disabledDate) {
		super();
		this.precio = precio;
		this.nroPrecio = nroPrecio;
		this.disabledDate = disabledDate;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getNroPrecio() {
		return nroPrecio;
	}
	public void setNroPrecio(int nroPrecio) {
		this.nroPrecio = nroPrecio;
	}
	public String getDisabledDate() {
		return disabledDate;
	}
	public void setDisabledDate(String disabledDate) {
		this.disabledDate = disabledDate;
	}
	@Override
	public String toString() {
		return "Precio [precio=" + precio + ", nroPrecio=" + nroPrecio + ", disabledDate=" + disabledDate + "]";
	}
}
