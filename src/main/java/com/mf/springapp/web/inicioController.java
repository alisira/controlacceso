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
public class inicioController {

	protected final Log logger = LogFactory.getLog(getClass());

    @RequestMapping(value="/inicio.htm")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    	
    	
    	Map<String, Object> contenido = new HashMap<String, Object>();
    	String vista = "login.jsp";
    	
    	try{
	    	    	
	    	String mensaje = null;

	    	Map objetoValidacion = new HashMap();
	    	
	        if (!reglaValidacion(objetoValidacion)){
            	mensaje = "Problema de Aplicación favor notificar al administrador, gracias";
            	contenido.put("mensaje", mensaje);
            }
	        
    	}catch (Exception e){
    		logger.error(null, e);
    	}finally{
    		Locale l = new Locale("es","VE");
	    	Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Caracas"),l);
	    	String hora = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
	    	String fechaHora = (cal.get(Calendar.MONTH)+1) + " " + cal.get(Calendar.DATE) + " " + cal.get(Calendar.YEAR) + " " + hora;
	    	
	    	String js[] = {"jquery-2.1.4.min", "inicio"};        
	        String css[] = {"bootstrap", "global_admin" ,"styleIE"};
	        
	        contenido.put("tituloPagina", "Bienvenidos al SIGEFIRRHH version 5");
	        contenido.put("js", js);
	        contenido.put("css", css);
	        contenido.put("vista", vista);	        
	        contenido.put("fechaHora", fechaHora);	        
    	}
    	
        return new ModelAndView("plantilla", "contenido", contenido);
    }
    
    
    private boolean reglaValidacion(Map datos){
    	
    	//Esto sirve por si se quiere validar la conectividad cada vez q se instancia por si se pierde conexion con la db
        /*if (!$this->Utilidades_db_mod->ambiente[0]) {           
            $this->data['title'] = "Error de Conectividad";
            $this->data['contenido'] = 'nocnn_view';
            $this->data['js'] = array('');
            $this->data['css'] = array('');
            */
    	
    	
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
	
/*

class Inicio_con extends CI_Controller {

    public function __construct() {
        parent::__construct();
        $this->load->model('Utilidades_db_mod');
        $this->load->model('configuracion_mod');
        $this->load->library('funciones');
        $this->load->helper('form','url');

        //Parametros comunes en todos los metodos
        $this->data['ambiente'] = $this->Utilidades_db_mod->ambiente[1];
        $this->data['config'] = $this->configuracion_mod;
        $this->data['titulo_pagina'] = $this->configuracion_mod->titulo_sistema;
        
    }
    
    public function __destruct() {
        $this->Utilidades_db_mod=null;
        $this->configuracion_mod = null;
        $this->data = null;
    }

    public function index($page = 'home') {
        
        //Esto sirve por si se quiere validar la conectividad cada vez q se instancia por si se pierde conexion con la db
        if (!$this->Utilidades_db_mod->ambiente[0]) {
            $this->data['ambiente'] = '';
            $this->data['title'] = "Error de Conectividad";
            $this->data['contenido'] = 'nocnn_view';
            $this->data['js'] = array('');
            $this->data['css'] = array('');
            
        } else {
            $this->data['contenido'] = 'login_view';
            $this->data['js'] = array('jqmin.js', 'jquery.js', 'menu_view.js', 'login_view.js');
            $this->data['css'] = array('bootstrap.css','global_admin.css', 'styleIE.css');
        }   
        $this->load->view('templates/plantilla', $this->data);
    }
}
*/
}
