package Logic;

import Data.DbPrecio;
import Entities.LineaPedido;
import Entities.Precio;

public class PrecioLogic {

	private DbPrecio dbp;
	
	public PrecioLogic() {
		dbp = new DbPrecio();
	}
	
	public Precio GetOne(int IdPrecio) {
		return dbp.GetOne(IdPrecio);
	}

	public void ModificarPrecio(Precio precio) {
		dbp.ModificarPrecio(precio);
	}

}
