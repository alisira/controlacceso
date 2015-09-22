package com.mf.controlacceso.facade.sistema;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModeloUtil {

	public static Object llenarBean(Object fuente, Object destino)  {
		
		//Guarda todos los metodos del bean fuente
		Method metodos[] = fuente.getClass().getMethods();
		//itera sobre todos los metodos
		for (int i = 0; i < metodos.length; i++) {
			//Guarda el metodo actual
			Method field = metodos[i];        	

			//verifica si es un metodo get
			if (field.getName().indexOf("get") > -1){
				//System.out.println(field.getName() +" - "+field.getReturnType()+" - "+field.getModifiers() +" - "+field.getDefaultValue() +" - "+field.getGenericReturnType());

				//verifica que no sea alguno de estos metodos por estar exceptuados
				if (!field.getName().toString().equals("getClass") && !field.getName().toString().equals("getMultipartRequestHandler")
						&& !field.getName().toString().equals("getServletWrapper")){

					Class clazz=destino.getClass();
					Method m = null;
					Integer rInt = 0;
					int rint;
					String rStr = "";
					Date rDate = null;
					Boolean rBoolean = null;
					Long rLong = 0L;
					Long rlong;
					List rList=null; 
					
					try {
						//primero verifica el tipo de dato y luego que tenga un valor
						if (field.getReturnType().toString().equals("class java.lang.Integer")  ){
							rInt = (Integer)field.invoke(fuente);
							if (rInt!=null ){
								//System.out.println(field.getName() +" - " + rInt);
								Class[] cArg = new Class[1];
								cArg[0] = Integer.class;
								m = clazz.getDeclaredMethod("set"+field.getName().toString().substring(3), cArg);
								m.invoke(destino, rInt);								
							}
						}else if (field.getReturnType().toString().equals("class java.lang.String")){
							/*
							 * Esta seccion realiza la conversion de la fuente que viene tipo string en caso de struts cuando los bean de fechas se cargan
							 * en el form tipo string para que no de error en la recepcion cuando se hace el request 
							 */
							rStr= (String)field.invoke(fuente);
							if (rStr!=null && !rStr.toString().equals("")){
								//System.out.println(field.getName() +" - " + rStr);

								m = clazz.getDeclaredMethod(field.getName().toString());

								Class[] cArg = new Class[1];
								if(m.getReturnType().toString().equals("class java.lang.String")){
									cArg[0] = String.class;
									m = clazz.getDeclaredMethod("set"+field.getName().toString().substring(3), cArg);
									m.invoke(destino, rStr);
								} else if(m.getReturnType().toString().equals("class java.util.Date")){
									cArg[0] = Date.class;									
									m = clazz.getDeclaredMethod("set"+field.getName().toString().substring(3), cArg);
									try {
										m.invoke(destino, new SimpleDateFormat("dd/M/yyyy").parse(rStr));
									} catch (ParseException e) {										
										e.printStackTrace();
									}
								} else if(m.getReturnType().toString().equals("class java.lang.Integer")){
									cArg[0] = Integer.class;									
									m = clazz.getDeclaredMethod("set"+field.getName().toString().substring(3), cArg);							        
									m.invoke(destino, Integer.valueOf(rStr));
								
								} else if(m.getReturnType().toString().equals("boolean")){
									cArg[0] = boolean.class;									
									m = clazz.getDeclaredMethod("set"+field.getName().toString().substring(3), cArg);							        
									m.invoke(destino, Boolean.getBoolean(rStr));
																		
								} else {
									System.out.println("Revisar error conversion tipos 111:" + field.getName().toString());	
									System.out.println(field.getReturnType().toString() + " - " + m.getReturnType().toString());
								}								
							}

						}else if (field.getReturnType().toString().equals("class java.util.Date")){
							rDate= (Date)field.invoke(fuente);
							if (rDate!=null){
								Class[] cArg = new Class[1];
								cArg[0] = Date.class;
								m = clazz.getDeclaredMethod("set"+field.getName().toString().substring(3), cArg);
								m.invoke(destino, rDate);								
							}
						}else if (field.getReturnType().toString().equals("class java.util.Long")){
							rLong= (Long)field.invoke(fuente);
							if (rLong!=null){
								Class[] cArg = new Class[1];
								cArg[0] = Long.class;
								m = clazz.getDeclaredMethod("set"+field.getName().toString().substring(3), cArg);
								m.invoke(destino, rLong);								
							}
						}else if (field.getReturnType().toString().equals("int")){
							rint= (Integer) field.invoke(fuente);
							if ((Integer) field.invoke(fuente)!=null){
								Class[] cArg = new Class[1];
								cArg[0] = int.class;
								m = clazz.getDeclaredMethod("set"+field.getName().toString().substring(3), cArg);
								m.invoke(destino, rint);
							}
						}else if (field.getReturnType().toString().equals("long")){
							rlong= (Long) field.invoke(fuente);
							if ((Long) field.invoke(fuente)!=null){
								Class[] cArg = new Class[1];
								cArg[0] = long.class;
								m = clazz.getDeclaredMethod("set"+field.getName().toString().substring(3), cArg);
								m.invoke(destino, rlong);								
							}
						}else if (field.getReturnType().toString().equals("boolean")){
							rBoolean= (Boolean)field.invoke(fuente);
							if (rBoolean!=null){
								Class[] cArg = new Class[1];
								cArg[0] = boolean.class;
								m = clazz.getDeclaredMethod("set"+field.getName().toString().substring(3), cArg);
								m.invoke(destino, rBoolean);								
							}
							
							
						}else if (field.getReturnType().toString().equals("interface java.util.List")){
								
							m = clazz.getDeclaredMethod(field.getName());
							List listaDestino = (List) m.invoke(destino);
							
							if (listaDestino != null){
								
								List listaFuente = (List)field.invoke(fuente);

								if (listaFuente!=null){
								
									List listaTempoFuente = new ArrayList();
									for (int z = 0; z < listaFuente.size(); z++) {
										
										//getGenericReturnType obtiene el tipo de objetos que soporta la lista
										String [] claseDestino =  m.getGenericReturnType().toString().split("<");
										claseDestino =  claseDestino[1].toString().split(">");
										Object objetoDestino=(Object) Class.forName(claseDestino[0]).newInstance();
										if (z==listaDestino.size()){
											
											
											Object objetoCasilla=(Object) Class.forName(claseDestino[0]).newInstance();
											
											ModeloUtil.llenarBean(listaFuente.get(0), objetoCasilla);
											ModeloUtil.llenarBean(listaFuente.get(z), objetoCasilla);
											listaTempoFuente.add(objetoCasilla);
											

											
										}else{
											objetoDestino =  (Object) ModeloUtil.llenarBean(listaFuente.get(z), listaDestino.get(z));
											listaTempoFuente.add(objetoDestino);
										}
										
										
									}
									
									Class[] cArg = new Class[1];
									cArg[0] = List.class;
									//Estas dos lineas sirven para finalmente setear la lista en el objeto destino
									Method metodoFinal = destino.getClass().getDeclaredMethod("set"+m.getName().toString().substring(3), cArg);
									metodoFinal.invoke(destino, listaTempoFuente);
								}
								
							}
							
						}else{
							
							System.out.println("Error en metodo: " + field.getName().toString() + " falta tipos primitivos para: "+ field.getReturnType().toString());							
							//la version del 19/09/15 tiene el codigo para intentar hacer la reciprocidad							
							
							m = destino.getClass().getDeclaredMethod(field.getName());
							Object objectDestino = (Object) m.invoke(destino);
							
							if (objectDestino != null){
								Object objectoFuente = (Object)field.invoke(fuente);

								if (objectoFuente!=null){
									System.out.println( field.getName().toString() + " No esta vacio");
									 ModeloUtil.llenarBean(objectoFuente, objectDestino);
								}
							
								//String [] claseDestino =  m.getReturnType().toString().split("\\s+");
								//Object objetoDestino=(Object) Class.forName(claseDestino[1]).newInstance();
							}
								
							
							//llenarBean(field.invoke(fuente), objetoDestino);							
							
							/*
							Class claseTempo = (listaSeparada[1]) Class.forName(listaSeparada[1]).newInstance();
							Class<?> tempo1;
							
							tempo1= (Class.forName(listaSeparada[1])) field.invoke(fuente);
							if (rBoolean!=null){
								Class[] cArg = new Class[1];
								cArg[0] = boolean.class;
								m = clazz.getDeclaredMethod("set"+field.getName().toString().substring(3), cArg);
								m.invoke(destino, rBoolean);								
							}
							*/
							
							//Class claseTempo = Class.forName("com.mf.controlacceso.dominio.PerfilUsuarioDTO");
							
							//llenarBean(rList.get(c), claseTempo.newInstance());
							
							//Error en metodo: getPerfil falta tipos primitivos para: class com.mf.controlacceso.modelo.sistema.Perfil
							//Error en metodo: getUsuario falta tipos primitivos para: class com.mf.controlacceso.modelo.sistema.Usuario
							
							
							
							
							
							
							
							
							
						}

					} catch (NoSuchMethodException e) {

						Class n2=fuente.getClass();
						System.out.println("No existe el metodo " + field.getName().toString() + " en el bean destino:");
						System.out.println("Bean destino:" + clazz.getName() + ", bean fuente:" + n2.getName().toString());
						//continue;
						//e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (ClassCastException e) {
						e.printStackTrace();				
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}        		
			}
		}
		return destino;
	}
}
