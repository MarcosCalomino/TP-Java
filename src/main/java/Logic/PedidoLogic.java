package Logic;

import java.util.LinkedList;

import Data.DbPedido;
import Entities.Pedido;

public class PedidoLogic {

	private DbPedido dbp;
	
	public PedidoLogic(){
		dbp= new DbPedido();
	}

	public void Alta(Pedido pedido) {
		dbp.Alta(pedido);
	}

	public LinkedList<Pedido> GetAllPedidos() {
		return dbp.GetAllPedidos();
	}

	public LinkedList<Pedido> GetAllPedidos(String estado) {
		return dbp.GetAllPedidos(estado);
	}

	public Pedido GetOne(int idPedido) {
		return dbp.GetOne(idPedido);
	}

	public void Modify(Pedido pedido) {
		dbp.Modify(pedido);
		
	}

}
