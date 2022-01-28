package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Cliente;
import Errores.BugManager;
import Errores.TipoError;
import Logic.ClienteLogic;


@WebServlet("/AltaCliente")
public class AltaCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AltaCliente() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClienteLogic clienteLogic = new ClienteLogic();
	    Cliente cliente = new Cliente(); 
	    cliente.setNombreCliente(request.getParameter("nombre"));
	    cliente.setApellidoClienta(request.getParameter("apellido"));
	    cliente.setTelefono(request.getParameter("telefono"));
	    cliente.setPassword(request.getParameter("password"));
	    cliente.setTipoPermisos("user");
	    cliente.setRepetirPassword(request.getParameter("repetirPassword"));
	   
	    BugManager bugManager = new BugManager();      
        bugManager.setError(clienteLogic.verificarDatos(cliente));
	    
        if(bugManager.getError().equals(TipoError.YAEXISTE))
        {
        	response.getWriter().append("Ya existe una cuenta para ese numero").append(request.getContextPath());
  			response.setContentType("text/html");
  			response.getWriter().println("<a href=\"./Login.jsp\">Iniciar Session</a>");
        }
        else if(bugManager.getError().equals(TipoError.CAMPOSENBLANCO))
        {
        	request.setAttribute("cliente", cliente); 
        	request.setAttribute("error", "camposEnBlanco");
        	request.getRequestDispatcher("./Registrarse.jsp").forward(request, response);
        }
        else if(bugManager.getError().equals(TipoError.PASSWORINCORRECTA))
        {
        	request.setAttribute("cliente", cliente); 
        	request.setAttribute("error", "contraseniasNoCoinciden");
        	request.getRequestDispatcher("./Registrarse.jsp").forward(request, response);
        }
        else if(bugManager.getError().equals(TipoError.BADPHONE))
        {
        	request.setAttribute("cliente", cliente); 
        	request.setAttribute("error", "telefonoIncorrecto");
        	request.getRequestDispatcher("./Registrarse.jsp").forward(request, response);
        }
        else if(bugManager.getError().equals(TipoError.APELLIDOINCORRECTO))
        {
        	request.setAttribute("cliente", cliente); 
        	request.setAttribute("error", "apellidoIncorrecto");
        	request.getRequestDispatcher("./Registrarse.jsp").forward(request, response);
        }
        else if(bugManager.getError().equals(TipoError.NOMBREINCORRECTO))
        {
        	request.setAttribute("cliente", cliente); 
        	request.setAttribute("error", "nombreIncorrecto");
        	request.getRequestDispatcher("./Registrarse.jsp").forward(request, response);
        }
        else if(bugManager.getError().equals(TipoError.OK))
        {
        	clienteLogic.AltaCliente(cliente);
        	response.getWriter().append("Cuenta creada con Exito").append(request.getContextPath());
  			response.setContentType("text/html");
  			response.getWriter().println("<a href=\"./Login.jsp\">Iniciar Session</a>");
        }
        
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		doGet(request, response);
		doGet(request, response);
	}

}
