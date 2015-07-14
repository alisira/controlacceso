package com.mf.controlacceso.helpers;

import javax.servlet.http.HttpServletRequest;

public class Html {

	public static String escribirJS(String[] js, HttpServletRequest request){
		
		if (js ==null){
			throw (new RuntimeException("La lista de js esta vacia, favor corregir"));
		}
		
		if (request ==null){
			throw (new RuntimeException("El request esta vacio, favor corregir"));
		}
		
		String text = "";		
        String ruta_js = request.getContextPath() + "/js/";
                
        for (String jsTemp : js){
          text += "<script src=\"" + ruta_js + jsTemp + ".js\" type=\"text/javascript\"></script>";
        }
        return text;
	}
	
	public static String escribirCSS(String[] css, HttpServletRequest request){
		
		if (css ==null){
			throw (new RuntimeException("La lista de css esta vacia, favor corregir"));
		}
		
		if (request ==null){
			throw (new RuntimeException("El request esta vacio, favor corregir"));
		}
		
		String text = "";		
        String ruta_css = request.getContextPath() + "/css/";
                
        for (String cssTemp : css){
          text += "<link href=\""+ ruta_css + cssTemp + ".css\" rel=\"stylesheet\" type=\"text/css\" />";             
        }
        return text;
	}

}
