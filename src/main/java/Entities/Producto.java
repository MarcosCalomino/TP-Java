package Entities;

public class Producto {

	private boolean estado;
	private String nombreProducto;
	private String descripcion;
	private String imagen;
	private int nroPrecio;
	private String tipoProducto;
	private int id_producto;
	
	
	public Producto() {
		
	}
    
	public Producto(boolean estado, String nombreProducto, String descripcion, String imagen, int nroPrecio,String tipoProducto) {
		super();
		this.estado = estado;
		this.nombreProducto = nombreProducto;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.tipoProducto = tipoProducto;
		this.nroPrecio = nroPrecio;
	}
	
	public int getId_producto() {
		return id_producto;
	}
	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}
	public String getTipoProducto() {
		return tipoProducto;
	}
	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
	public int getNroPrecio() {
		return nroPrecio;
	}
	public void setNroPrecio(int nroPrecio) {
		this.nroPrecio = nroPrecio;
	}
	public boolean getEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	@Override
	public String toString() {
		return "Producto [nombreProducto=" + nombreProducto + ", descripcion=" + descripcion +", imagen=" + imagen + ", tipoProducto=" + tipoProducto + "]";
	}
}
