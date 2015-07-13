package com.mf.springapp.web;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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


@Controller
public class loginCon {

    protected final Log logger = LogFactory.getLog(getClass());

    @RequestMapping(value="/login.htm")
    public ModelAndView handleRequest(String cedula, String foto, String observacion, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    	
    	
    	Map<String, Object> contenido = new HashMap<String, Object>();
    	String vista = "entradaSalida.jsp";
    	String visibleFoto = "none";
    	String visibleVideo = "block";
    	
    	try{
	    	
	    	String nombreApellido = "";    	
	    	String mensaje = null;

	    	Map objetoValidacion = new HashMap();
	    	objetoValidacion.put("cedula", cedula);
	    	objetoValidacion.put("foto", foto);
	    	
	        if (reglaValidacion(objetoValidacion)){
	
	            //$resultado = $this->control_acceso_mod->insertarCA($cedula, $observacion, $fotoTXT, $id_instalacion);
		    	boolean resultado = true;
		    	boolean entrada = false;
		    	
	            if (resultado) {
	    	    	visibleFoto = "block";
	    	    	visibleVideo = "none";
	                
	                //$empleado = $this->empleado_mod->empleado($cedula);
	    	    	nombreApellido = "Ali Sira";
	    	    	
	    	    	if ( (int)(Math.random()*(1-0+1)+0)  == 0){
	    	    		entrada = true;	
	    	    	}else{
	    	    		entrada = false;
	    	    	}
	    	    	
	    	    	contenido.put("foto", foto);
			        contenido.put("nombreApellido", nombreApellido);
			        contenido.put("entrada", entrada);
	                
	            } else {
	            	mensaje = "Empleado No Est&aacute; Registrado, Favor Revisar";
	            }
		    	
		    	contenido.put("mensaje", mensaje);
	        
	        }
        
    	}catch (Exception e){
    		e.printStackTrace();
    	}finally{
    		Locale l = new Locale("es","VE");
	    	Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Caracas"),l);
	    	String hora = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
	    	String fechaHora = (cal.get(Calendar.MONTH)+1) + " " + cal.get(Calendar.DATE) + " " + cal.get(Calendar.YEAR) + " " + hora;
	    	
	    	String js[] = {"jquery-2.1.4.min","entradaSalida"};        
	        String css[] = {"bootstrap", "global_admin" ,"styleIE", "controlAcceso"};
	        
	        contenido.put("tituloPagina", "Control de Entrada y Salida del Personal");
	        contenido.put("js", js);
	        contenido.put("css", css);
	        contenido.put("vista", vista);
	        contenido.put("visibleFoto", visibleFoto);
	        contenido.put("visibleVideo", visibleVideo);
	        contenido.put("fechaHora", fechaHora);	        
    	}
    	
        return new ModelAndView("plantilla", "contenido", contenido);
    }
    
    
    private boolean reglaValidacion(Map datos){
    	
    	if (((String)datos.get("cedula")).length() < 1 ) {
    		//throw new RuntimeException("cedula vacia");
    		return false;
    	}
    	if (((String)datos.get("foto")).length() < 1 ) {
    		//throw new RuntimeException("foto vacia");
    		return false;
    	}
    	return true;
    }
    
}