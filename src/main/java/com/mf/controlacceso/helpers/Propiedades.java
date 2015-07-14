package com.mf.controlacceso.helpers;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

public class Propiedades {
	
	private InputStream entrada;
	private String ambiente;
	
	public Propiedades(){
		entrada = null;  
	    Properties propiedades = new Properties();

	    
	    File miDir = new File (".");
	     try {
	       System.out.println ("Directorio actual: " + miDir.getCanonicalPath());
	       }
	     catch(Exception e) {
	       e.printStackTrace();
	       }
	    
	    
        try {
            
          entrada = this.getClass().getResourceAsStream("../classes/config.properties");          
        // cargamos el archivo de propiedades
          propiedades.load(entrada);
        // obtenemos las propiedades y las imprimimos 
          
          System.out.println(propiedades.getProperty("ambiente"));
          
          setAmbiente(propiedades.getProperty("ambiente"));
          
          String strManejador      = propiedades.getProperty("strManejador");
          String strBaseDatos      = propiedades.getProperty("strBaseDatos");
          String strServidor       = propiedades.getProperty("strServidor");
          String strUsuario        = propiedades.getProperty("strUsuario");
          String strPassword       = propiedades.getProperty("strPassword");
          String strPuerto         = propiedades.getProperty("strPuerto");
          
        }catch(Exception e){
        	e.printStackTrace();
        }

	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}
	
	
	
}
