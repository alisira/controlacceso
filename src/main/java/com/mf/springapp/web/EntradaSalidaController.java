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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mf.springapp.service.ProductManager;

@Controller
public class EntradaSalidaController {

    protected final Log logger = LogFactory.getLog(getClass());
    
	@Autowired
    private ProductManager productManager;    

    @RequestMapping(value="/principal.htm")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	Locale l = new Locale("es","VE");
    	Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Caracas"),l);
    	String hora = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
    	String fecha = (cal.get(Calendar.MONTH)+1) + " " + cal.get(Calendar.DATE) + " " + cal.get(Calendar.YEAR) + " " +hora;
    	
    	String js[] = {"jquery-2.1.4.min","control_acceso"};        
        String css[] = {"bootstrap", "global_admin" ,"styleIE"};        
    	
        Map<String, Object> contenido = new HashMap<String, Object>();
        contenido.put("products", this.productManager.getProducts());
        contenido.put("js", js);
        contenido.put("css", css);
        contenido.put("fecha", fecha);
        contenido.put("hora", hora);
        contenido.put("vista", "entrada_salida.jsp");
        
        return new ModelAndView("plantilla", "contenido", contenido);

    }
}