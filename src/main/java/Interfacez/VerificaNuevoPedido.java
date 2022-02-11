package Interfacez;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.Toolkit;

import Data.DbPedido;
import Entities.Pedido;

public class VerificaNuevoPedido implements ActionListener{

	private DbPedido dbp;
	private LinkedList<Pedido> listaPedidosNuevos;
	public static int cantPedidos;
	
	public VerificaNuevoPedido() {
		dbp = new DbPedido();	
		cantPedidos = 0;
	}
	
	public void actionPerformed(ActionEvent e) {
		listaPedidosNuevos = dbp.GetAllPedidos("espera");
		if(listaPedidosNuevos.size()>cantPedidos) {
			Toolkit.getDefaultToolkit().beep();
			this.cantPedidos = listaPedidosNuevos.size();
		}else if(listaPedidosNuevos.size()==cantPedidos){
			this.cantPedidos = listaPedidosNuevos.size();
		}else {
			this.cantPedidos = this.cantPedidos - 1;
		}
//		this.cantPedidos = this.cantPedidos + 0;
	}
	
	
	
}
