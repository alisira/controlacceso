package com.mf.hibernate.util;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	
    private static Map<String, SessionFactory> sessionFactory = new HashMap<String, SessionFactory>();

    public static synchronized void buildSessionFactory(Map<String, String> mapaXml) {
        
    	Configuration configuration = null;
    	ServiceRegistry serviceRegistry = null;
    	
    	if (sessionFactory.size() < 1 ) {
        	
    		Iterator<String> it = mapaXml.keySet().iterator();
    		
    		while(it.hasNext()){
    			String bd = (String) it.next();
    			//System.out.println("BD: " + bd + " -> Path: " + mapaXml.get(bd));
    			
    			configuration = new Configuration();
    			    			
    			File xmlMapeo = new File(mapaXml.get(bd));

				if(!xmlMapeo.exists() || xmlMapeo.isDirectory()) { 
					throw (new RuntimeException("Path: " + mapaXml.get(bd) + " no encontrado"));
				} 
				configuration.configure(xmlMapeo);
            
    			configuration.setProperty("hibernate.current_session_context_class", "thread");
    			serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
    			SessionFactory sessionFactoryTemp = configuration.buildSessionFactory(serviceRegistry);
    			sessionFactory.put(bd, sessionFactoryTemp);
    			
    			sessionFactoryTemp = null;
    			serviceRegistry = null;
    			configuration =null;
    		}
        }
    }

    public static void openSessionAndBindToThread(String bd) {
        Session session = (Session) sessionFactory.get(bd).openSession();
        ThreadLocalSessionContext.bind(session);
    }
    
    public static Map<String, SessionFactory> getSessionFactory() {
    	if (sessionFactory.size() < 1 ) {
        	
        	Map<String, String> mapaXml = new HashMap<String, String>();
        	//Hay que poner estas lineas para que se lean de un archivo de configuracion
        	mapaXml.put("oncop", HibernateUtil.class.getClassLoader().getResource("").getPath() + "hibernate.cfg.xml");
        	mapaXml.put("wf", HibernateUtil.class.getClassLoader().getResource("").getPath() + "hibernate.cfg.wf.xml");
        	
            buildSessionFactory(mapaXml);
        }
        return sessionFactory;
    }

    public static void closeSessionAndUnbindFromThread(String bd) {
        Session session = ThreadLocalSessionContext.unbind(sessionFactory.get(bd));
        if (session!=null) {
            session.close();
        }
    }

    public static void closeSessionFactory(String bd) {
        if ((sessionFactory.get(bd)!=null) && (sessionFactory.get(bd).isClosed()==false)) {
        	sessionFactory.get(bd).close();
        }
    }
}
