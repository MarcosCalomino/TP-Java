package Logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Entities.Cliente;

public class LoginLogic {

	protected boolean verificarIsNumeric(String telefono) {
		 boolean isValid = false;
		 String expression = "^(([(]?(\\d{2,4})[)]?)|(\\d{2,4})|([+1-9]+\\d{1,2}))?[-\\s]?(\\d{2,3})?[-\\s]?((\\d{7,8})|(\\d{3,4}[-\\s]\\d{3,4}))$"; 
		    CharSequence inputStr = telefono;
		    Pattern pattern = Pattern.compile(expression); 
		    Matcher matcher = pattern.matcher(inputStr); 
		    if(matcher.matches()){ 
		     isValid = true; 
		    } 
		    return isValid; 
	}
	
	protected boolean isWhite(Cliente cliente) {
		if(cliente.getApellidoClienta().isEmpty() ||
		   cliente.getNombreCliente().isEmpty()   ||
		   cliente.getPassword().isEmpty()        ||
		   cliente.getRepetirPassword().isEmpty() || 
		   cliente.getTelefono().isEmpty()) { return true;}
		return false;
	}
	
	protected boolean verificaPassword(String pw, String password) {
		if(pw.isEmpty()) {
			return false;
		}
		else if(pw.equals(password)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean verificaSoloLetras(String cadena) {
		for (int i = 0; i < cadena.length(); i++)
		{
			char caracter = cadena.toUpperCase().charAt(i);
			int valorASCII = (int)caracter;
			if (valorASCII != 165 && (valorASCII < 65 || valorASCII > 90))
				return false; //Se ha encontrado un caracter que no es letra
		}
 
		//Terminado el bucle sin que se haya retornado false, es que todos los caracteres son letras
		return true;
	}
}
