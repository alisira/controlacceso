package com.mf.controlacceso.sistema;

import java.io.FileReader;
import java.util.Properties;

public class Configuracion {

	private static String config[] = inicializar();
	
	private static String[] inicializar(){

		config = new String[1];

		Properties propiedades = new Properties();	   

        try {

        	FileReader entrada = new FileReader(Configuracion.class.getClassLoader().getResource("").getPath() + "config.properties");        	
            propiedades.load(entrada);

            config[0] = propiedades.getProperty("ambiente");

        }catch(Exception e){
        	e.printStackTrace();
        }
		return config;

	}

	public static String getAmbiente() {
		return config[0];
	}
	
}
