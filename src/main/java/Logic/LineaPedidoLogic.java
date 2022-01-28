package Logic;

import java.util.LinkedList;

import Data.DbLineaPedido;
import Entities.LineaPedido;

public class LineaPedidoLogic {

	private DbLineaPedido dblp;
	
	public LineaPedidoLogic() {
		dblp = new DbLineaPedido();
	}
	
	public void AltaLineaPedido(LineaPedido lineaPedido) {
	    dblp.AltaLineaPedido(lineaPedido);
	}

	public void Eliminar(LineaPedido lp) {
		dblp.Eliminar(lp);	
	}
	
	public LinkedList<LineaPedido> GetAll(int idPedido){
		return dblp.GetAll(idPedido);
	}

}
