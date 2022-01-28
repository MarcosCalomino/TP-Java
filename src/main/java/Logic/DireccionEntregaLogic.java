package Logic;

import Data.DbDireccionEntrega;
import Entities.DireccionEntrega;
import Errores.TipoError;

public class DireccionEntregaLogic {

	private DbDireccionEntrega dbde;
	
	public DireccionEntregaLogic() {
		dbde = new DbDireccionEntrega();
	}
	
	public void Alta(DireccionEntrega direccionEntrega) {
		dbde.Alta(direccionEntrega);
	}


}
