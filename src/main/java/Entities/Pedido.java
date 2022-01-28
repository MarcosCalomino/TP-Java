package Entities;

import java.time.LocalTime;

public class Pedido {

	private int nroPedido;
	private String fechaHoraPedido;
	private LocalTime horaEntrega;
	private Double precioTotal;
	private String estadoPedido;
	private int nroCliente;
	private int DireccionID;
	
	public Pedido() {
		
	}
	public Pedido(int idDireccion, int idCliente, String estado, String fechaHoraPedido, Double precioTotal) {
		super();
		this.DireccionID = idDireccion;
		this.nroCliente = idCliente;
		this.estadoPedido = estado;
		this.fechaHoraPedido = fechaHoraPedido;
		this.precioTotal = precioTotal;
	}
	public int getNroPedido() {
		return nroPedido;
	}
	public void setNroPedido(int nroPedido) {
		this.nroPedido = nroPedido;
	}
	public String getFechaHoraPedido() {
		return fechaHoraPedido;
	}
	public void setFechaHoraPedido(String fechaHoraPedido) {
		this.fechaHoraPedido = fechaHoraPedido;
	}
	public LocalTime getHoraEntrega() {
		return horaEntrega;
	}
	public void setHoraEntrega(LocalTime horaEntrega) {
		this.horaEntrega = horaEntrega;
	}
	public Double getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}
	public String getEstadoPedido() {
		return estadoPedido;
	}
	public void setEstadoPedido(String estadoPedido) {
		this.estadoPedido = estadoPedido;
	}
	public int getNroCliente() {
		return nroCliente;
	}
	public void setNroCliente(int nroCliente) {
		this.nroCliente = nroCliente;
	}
	public int getDireccionID() {
		return DireccionID;
	}
	public void setDireccionID(int direccionID) {
		DireccionID = direccionID;
	}
	
	
}
