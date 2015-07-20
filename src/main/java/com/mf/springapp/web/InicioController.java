package com.mf.springapp.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mf.controlacceso.dao.UsuarioDAO;
import com.mf.controlacceso.imple.UsuarioDAOImple;
import com.mf.controlacceso.modelo.Usuario;
import com.mf.controlacceso.servicio.Configuracion;
import com.mf.controlacceso.servicio.PingService;


@Controller
public class InicioController {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@RequestMapping(value="/inicio.htm")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    	
    	
    	Map<String, Object> contenido = new HashMap<String, Object>();
    	String vista = null;
    	String ambiente = null;
    	
    	try{
    		ambiente  =  Configuracion.getAmbiente();  
    		reglaValidacion(null);
    		vista = "login.jsp";
    		
    		Usuario user = new Usuario();
    		user.setEstatus("A");
    		
    		UsuarioDAO usuarioDAO = new UsuarioDAOImple();
    		
			List<Usuario> listadoUsuario= (List) usuarioDAO.listar(user);
			
			System.out.println(listadoUsuario.size());
    		
	        
    	}catch (SQLException e){
    		logger.error("SQLException: ", e);
    		String mensaje = "Problema de Conexion con la Base de Datos favor notificar al administrador, gracias";
        	contenido.put("mensaje", mensaje);
        	vista = "resultado.jsp";
    	}catch (Exception e){
    		logger.error("Exception: ", e);
    		String mensaje = "Problema de Aplicación favor notificar al administrador, gracias";
        	contenido.put("mensaje", mensaje);
        	vista = "resultado.jsp";
    	}finally{
    		Locale l = new Locale("es","VE");
	    	Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Caracas"),l);
	    	String hora = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
	    	String fechaHora = (cal.get(Calendar.MONTH)+1) + " " + cal.get(Calendar.DATE) + " " + cal.get(Calendar.YEAR) + " " + hora;

	    	String js[] = {"jquery-2.1.4.min", "comun", "inicio"};        
	        String css[] = {"bootstrap", "global_admin" ,"styleIE"};
	        
	        contenido.put("tituloPagina", "Bienvenidos al SIGEFIRRHH version 5");
	        contenido.put("js", js);
	        contenido.put("css", css);
	        contenido.put("ambiente", ambiente);
	        contenido.put("vista", vista);
	        contenido.put("fechaHora", fechaHora);	        
    	}
    	
        return new ModelAndView("plantilla", "contenido", contenido);
    }
    
    
    private boolean reglaValidacion(Map datos) throws SQLException{

    	PingService ps = new PingService();
    	
    	ps.pingWebDB();    	
    	
    	return true;
    }
	

}
