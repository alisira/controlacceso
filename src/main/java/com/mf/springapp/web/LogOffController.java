package com.mf.springapp.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
import com.mf.controlacceso.sistema.Configuracion;
import com.mf.controlacceso.sistema.PingService;
import com.mf.controlacceso.to.UsuarioTO;


@Controller
public class LogOffController {

	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping(value="/logOff.htm")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    	
    	
    	Map<String, Object> contenido = new HashMap<String, Object>();
    	String vista = null;    	
    	String mensaje = "";
    	
    	try{
    		
    		reglaValidacion(null);
    		vista = "login.jsp";
    		contenido.put("menu", null);
	        
    	}catch (SQLException e){
    		logger.error("SQLException: ", e);
    		mensaje = "Problema de Conexion con la Base de Datos favor notificar al administrador, gracias";
        	contenido.put("mensaje", mensaje);
        	vista = "resultado.jsp";
    	}catch (Exception e){
    		logger.error("Exception: ", e);
    		mensaje = "Problema de Aplicación favor notificar al administrador, gracias";
        	contenido.put("mensaje", mensaje);
        	vista = "resultado.jsp";
    	}finally{
    		Locale l = new Locale("es","VE");
    		DecimalFormat twoDigits = new DecimalFormat("00");
	    	Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Caracas"),l);
	    	String hora = twoDigits.format(cal.get(Calendar.HOUR_OF_DAY)) + ":" + twoDigits.format(cal.get(Calendar.MINUTE)) + ":" + twoDigits.format(cal.get(Calendar.SECOND));
	    	String fechaHora = twoDigits.format((cal.get(Calendar.MONTH)+1)) + " " + twoDigits.format(cal.get(Calendar.DATE)) + " " + cal.get(Calendar.YEAR) + " " + hora;

	    	String js[] = {"jquery-2.1.4.min", "inicio", "dateFormat", "jquery.dateFormat", "comun"};        
	        String css[] = {"bootstrap", "global_admin" ,"styleMenu"};
	        
	        contenido.put("tituloPagina", "Bienvenidos al SIGEFIRRHH version 5");
	        contenido.put("js", js);
	        contenido.put("css", css);
	        contenido.put("ambiente", Configuracion.getAmbiente());
	        contenido.put("vista", vista);
	        contenido.put("fechaHora", fechaHora);
	        contenido.put("mensaje", mensaje);
    	}
    	
        return new ModelAndView("plantilla", "contenido", contenido);
    }
    
    private boolean reglaValidacion(Map datos) throws SQLException{

    	PingService ps = new PingService();
    	ps.pingWebDB();    	
    	
    	return true;
    }
	

}
