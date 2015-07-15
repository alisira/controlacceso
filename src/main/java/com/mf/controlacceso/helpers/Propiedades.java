package com.mf.controlacceso.helpers;

import java.io.FileReader;
import java.util.Properties;

public class Propiedades {

	private String ambiente;
	
	public Propiedades(){
		 Properties propiedades = new Properties();	   
	    
        try {        	
        	
        	FileReader entrada = new FileReader(this.getClass().getClassLoader().getResource("").getPath() + "config.properties");        	
            propiedades.load(entrada);
          
            ambiente = propiedades.getProperty("ambiente");     
          
        }catch(Exception e){
        	e.printStackTrace();
        }

	}

	public String getAmbiente() {
		return ambiente;
	}
	
}
