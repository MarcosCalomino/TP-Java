package Logic;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import javax.servlet.http.Part;

import Data.DbPrecio;
import Data.DbProducto;
import Entities.Precio;
import Entities.Producto;
import Entities.TipoProducto;
import Errores.TipoError;
import Servlets.Login;

public class ProductoLogic {

	private DbProducto dbp;
	private DbPrecio DbPrecio;
	public ProductoLogic() {
		dbp = new DbProducto();
	    DbPrecio = new DbPrecio();
	}
	
	
	public LinkedList<TipoProducto> GetAllTiposProductos() {
		LinkedList<TipoProducto> listaTipoProductos = dbp.GetAllTiposPrductos();
		return listaTipoProductos;
	}

	public LinkedList<Producto> GetProductsForType(String tipoProducto) {
		LinkedList<Producto> listaProductosPorTipo = dbp.GetProductsForType(tipoProducto); 
		return listaProductosPorTipo;
	}

	public Producto GetOne(int idProducto) {       
		return dbp.GetOne(idProducto);
	}

	//GUARDA LA IMAGEN EN UNA CARPETA EN EL SERVIDOR
	public File ManageImage(Part filePart) throws IOException {
		String path="C:\\apache-tomcat-8.5.71\\webapps\\ROOT\\ImagenesPizzeria";
		String fileName = GetNamePhoto(filePart);
	    File uploads = new File(path); //Carpeta donde se guardan los archivos
	    uploads.mkdirs(); //Crea los directorios necesarios
	    File file = File.createTempFile(fileName, fileName, uploads); //Evita que hayan dos archivos con el mismo nombre
	    
	    //Copia imagen de origen a destino..
	    try (InputStream input = filePart.getInputStream()){
	        Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
	    } 
	    return file;
	}

	//OBTIENE EL NOMBRE DE LA IMAGEN
	public String GetNamePhoto(Part part) {
		Part filePart = part; // Retrieves <input type="file" name="file">
		String photoName = this.getSubmittedFileName(filePart);
		return photoName;
	}
	//PREPARA EL NOMBRE DE LA IMAGEN PARA QUE NO EXISTAN 2 IGUALES
	public static String getSubmittedFileName(Part filePart) {
		for (String cd : filePart.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	            return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
	        }
	    }
	    return null;
	}
    
	public Producto setDatos(String nombreProducto, String descripcionProducto, String tipoProducto, Double precioProducto, int estadoProducto, File file) {
        Producto p = new Producto();
        p.setDescripcion(descripcionProducto);
        p.setNombreProducto(nombreProducto);
        p.setTipoProducto(tipoProducto);
        p.setImagen(file.getName());
        if(estadoProducto==1)
        {
        	p.setEstado(true);
        }
        else
        {
        	p.setEstado(false);
        }
        p.setNroPrecio(AltaPrecioProducto(precioProducto));
		return p;
	}

	private int AltaPrecioProducto(Double precio) {    
		DbPrecio dbp = new DbPrecio();
		Precio pp = new Precio();
		pp.setPrecio(precio);
		pp.setDisabledDate(this.GetFechaActual());
		dbp.AltaPrecioProducto(pp);
		return pp.getNroPrecio();
	}
	
	public String GetFechaActual() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");	
		return dtf.format(LocalDateTime.now());
	}
	
	public void AltaProducto(Producto p) {
		dbp.AltaProducto(p);	
	}

	public void ModificarProducto(Producto producto) {
             dbp.ModificarProducto(producto);		
	}


	public void EliminarProducto(Producto producto) {
		dbp.EliminarProducto(producto);
		DbPrecio.EliminarPrecio(producto.getNroPrecio());
	}


	public LinkedList<Producto> GetAll() {
		LinkedList<Producto> lista = dbp.GetAll();
		return lista;
	}


//	public boolean ValidaExistenciaProducto(Producto p) {
//	Producto pr = new Producto();
//	pr = dbp.GetOne(p.getNombreProducto());
//	if(pr==null) {
//		return false;
//	}else {
//		return true;
//	}
//	
//}
	

}
