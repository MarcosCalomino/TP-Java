package Entities;

public class LineaPedido {

	private int idProducto;
	private int nroLineaPedido;
	private double cantidad;
	private double precioLineaPedido;
	private int nroPedido;
	
	public LineaPedido() {

	}
	public LineaPedido(int idProducto, double cantidad, double precio) {
		super();
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.precioLineaPedido = cantidad*precio;
	}
	
	public int getNroPedido() {
		return nroPedido;
	}
	public void setNroPedido(int nroPedido) {
		this.nroPedido = nroPedido;
	}
	public double getPrecioLineaPedido() {
		return precioLineaPedido;
	}
	public void setPrecioLineaPedido(double precioLineaPedido) {
		this.precioLineaPedido = precioLineaPedido;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public int getNroLineaPedido() {
		return nroLineaPedido;
	}
	public void setNroLineaPedido(int nroLineaPedido) {
		this.nroLineaPedido = nroLineaPedido;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
