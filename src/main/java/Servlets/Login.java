package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.Timer;

import Entities.Cliente;
import Errores.BugManager;
import Errores.TipoError;
import Interfacez.VerificaNuevoPedido;
import Logic.ClienteLogic;

import Logic.ProductoLogic;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Login() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ProductoLogic productoLogic = new ProductoLogic();
		ClienteLogic clienteLogic = new ClienteLogic();
        Cliente cliente = new Cliente();
        String telefono = request.getParameter("telefono");
        String password = request.getParameter("password");
        cliente.setTelefono(telefono);
        cliente.setPassword(password);
        cliente.setRepetirPassword(password);
        cliente = clienteLogic.EncriptaContrasenia(cliente);
        BugManager bugManager = new BugManager();      
        bugManager.setError(clienteLogic.verificarDatos(cliente, cliente.getPassword()));  
        
        
        if(bugManager.getError().equals(TipoError.NOEXISTE))//TOMA ESTE CAMINO SI LA CUENTA CON LA QUE SE QUIERE INICIAR SESION ES INEXISTENTE
        {
        	response.getWriter().append("Cuenta inexistente").append(request.getContextPath());
  	        response.setContentType("text/html");
  		response.getWriter().println("<a href=\"./Registrarse.jsp\">Registrarse</a>");  
        }
        else if(bugManager.getError().equals(TipoError.CAMPOSENBLANCO))//TOMA ESTE CAMINO SI USUARIO/CONTRASEÑA ESTAN EN BLANCO 
        { 	
        	request.setAttribute("error", "CamposEnBlanco");
        	request.getRequestDispatcher("./Login.jsp").forward(request, response);  
        }
        else if(bugManager.getError().equals(TipoError.BADPHONE))//TOMA ESTE CAMINO SI EL NUMERO DE TELEFONO ESTA MAL ESCRITO
        {
        	request.setAttribute("error", "telefonoIncorrecto");
        	request.setAttribute("telefono", cliente.getTelefono());
        	request.getRequestDispatcher("./Login.jsp").forward(request, response); 
        }
        else if(bugManager.getError().equals(TipoError.PASSWORINCORRECTA))//TOMA ESTE CAMINO SI LA CONTRASEÑA ES INCORRECTA
        {
        	request.setAttribute("telefono", telefono);
        	request.setAttribute("error", "PasswordIncorrecta");   	
        	request.getRequestDispatcher("./Login.jsp").forward(request, response); 
        }
        else if(bugManager.getError().equals(TipoError.OK))//TOMA ESTE CAMINO SI EL INICIO DE SESSION ES CORRECTO 
        { 
        	cliente = clienteLogic.getOne(cliente);
        	if(cliente.getTipoPermisos().equals("admin"))//SE VERIFICA SI QUIEN INICIO SESSION TIENE PERMISOS DE DE CLIENTE O ADMIN
        	{
        		HttpSession sessionLogin= request.getSession();
        		sessionLogin.setAttribute("sessionLogin", cliente.getNroCliente());
        		request.setAttribute("listaTipoProductos", productoLogic.GetAllTiposProductos()); 
        		VerificaNuevoPedido oyente = new VerificaNuevoPedido();
        		Timer Temporizador = new Timer(500, oyente);
        		Temporizador.start();	 
            	request.getRequestDispatcher("./Admin.jsp").forward(request, response);
        	}
        	else
        	{
        		HttpSession sessionLogin= request.getSession();
        		sessionLogin.setAttribute("sessionLogin", cliente.getNroCliente());
    		    request.setAttribute("listaTipoProductos", productoLogic.GetAllTiposProductos());
    		    request.getRequestDispatcher("./Cliente.jsp").forward(request, response);
        	} 	
        }
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
