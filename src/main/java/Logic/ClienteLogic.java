package Logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Data.DbCliente;
import Entities.Cliente;
import Errores.BugManager;
import Errores.TipoError;
import Servlets.Login;

public class ClienteLogic extends LoginLogic{
	
	private DbCliente dbc;
		
	public ClienteLogic() {
		dbc=new DbCliente();
	}

	public TipoError verificarDatos(Cliente cliente, String password) {
		Cliente c = dbc.getOne(cliente);
		
		if(cliente.getTelefono().isEmpty() || cliente.getPassword().isEmpty())
		{
			return TipoError.CAMPOSENBLANCO;
		}
		else if(c==null)
	    {
				return TipoError.NOEXISTE;
	    }
		else if(!verificarIsNumeric(cliente.getTelefono()))
		{
			return TipoError.BADPHONE;
		}
		else if(!verificaPassword(c.getPassword(), password)) 
		{
			return TipoError.PASSWORINCORRECTA;
		}
		else
		{
			return TipoError.OK;	
		}
		
	}

	public Cliente getOne(Cliente cliente) {
		return dbc.getOne(cliente);
	}

	public Cliente getOne(int idCliente) {
		return dbc.getOne(idCliente);
	}

	public TipoError verificarDatos(Cliente cliente) {
		Cliente c = dbc.getOne(cliente);
		
		if(isWhite(cliente))
		{
			return TipoError.CAMPOSENBLANCO;
		}
		else if(c!=null)
		{
			return TipoError.YAEXISTE;
		}
		else if(!verificaPassword(cliente.getPassword(), cliente.getRepetirPassword()))
		{
			return TipoError.PASSWORINCORRECTA;
		}
		else if(!verificaSoloLetras(cliente.getApellidoClienta()))
		{
			return TipoError.APELLIDOINCORRECTO;
		}
		else if(!verificaSoloLetras(cliente.getNombreCliente()))
		{
			return TipoError.NOMBREINCORRECTO;
		}
		else if (!verificarIsNumeric(cliente.getTelefono())) {
			return TipoError.BADPHONE;
		}
		else
		{		     
			return TipoError.OK;
		}
		
	}

	public void AltaCliente(Cliente cliente) {
		dbc.AltaCliente(cliente);
		
	}

	

}
