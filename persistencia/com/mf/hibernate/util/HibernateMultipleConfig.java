package com.mf.hibernate.util;

import java.util.HashMap;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * Clase de utilidad para conseguir la sesión de Hibernate.
 * La sesión es única por thread.
 * Pueden existir varios ficheros de configuración.
 * Al menos siempre existirá la configuración por defecto sacada del fichero "hibernate.cfg.xml".
 * 
 * @author Autentia
 */
public class HibernateMultipleConfig {
    /** Clave para acceder a la sesión por defecto, esta sesión se obtiene a partir del fichero de configuración "hibernate.cfg.xml". */
    public static final String DEFAULT = "default";
    /** Mapa donde se guardan los SessionFactory para cada uno de los ficheros de configuración. */
    private static final Map<String, SessionFactory> sessionFactorys = new HashMap();
    /** Atributo único por threat donde se guardan las sesiones para cada uno de los ficheros de configuración. */
    private static final ThreadLocal<Map<String, Session>> sessions = new ThreadLocal();
    
    static {
        try {
            // Create the DEFAULT SessionFactory from hibernate.cfg.xml
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            sessionFactorys.put(DEFAULT, sessionFactory);
        } catch (Throwable e) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
        // Creamos el mapa donde se guardará la sesión para cada una de las configuraciones.
        // Este mapa lo guardamos en el atributo sessions, que nos asegura que el mapa es único por thread.
        sessions.set(new HashMap<String, Session>());
    }
    /**
     * Añade un nuevo fichero de configuración, creando el SessionFactory correspondiente.
     * Para encontrar el fichero de configuración, este debe encontrarse en el classpath,
     * junto al fichero de configuración por defecto de hibernate "hibernate.cfg.xml". 
     * 
     * @param fileName nombre del fichero de configuración que se quiere añadir.
     * @param key clave con la que se hará referencia a esta configuración en las siguientes llamadas.
     * @throws HibernateException si no se puede crear el SessionFactory basado en el fichero que se pasa como
     *             argumento.
     */
    public static void addConfigFile(String fileName, String key) throws HibernateException {
        SessionFactory sessionFactory = new Configuration().configure(fileName).buildSessionFactory();
        sessionFactorys.put(key, sessionFactory);
    }
    /**
     * Devuelve la sesión, para el thread que hace la llamada. Es decir cada thread tiene su propia sesión.
     * 
     * @param key clave que identifica la sesión a la que se quiere acceder.
     * @return sesión por defecto, para el thread que hace la llamada.
     * @throws HibernateException
     */
    public static Session currentSession(String key) throws HibernateException {
        Map<String, Session> ss = sessions.get();
        Session s = ss.get(key);
        // Abre una nueva sesión si este thread todavía no tiene ninguna
        if (s == null) {
            s = sessionFactorys.get(key).openSession();
            ss.put(key, s);
        }
        return s;
    }
    /**
     * Cierra la sesión, para el thread que hace la llamada.
     * 
     * @param key clave que identifica la sesión que se quiere cerrar.
     * @throws HibernateException
     */
    public static void closeSession(String key) throws HibernateException {
        Map<String, Session> ss = sessions.get();
        Session s = ss.get(key);
        if (s != null) {
            s.close();
            ss.put(key, null);
        }
    }
    /**
     * Devuelve la sesión por defecto, para el thread que hace la llamada.
     * Es decir cada thread tiene su propia sesión.
     * 
     * @return sesión por defecto, para el thread que hace la llamada.
     * @throws HibernateException
     */
    public static Session currentSession() throws HibernateException {
        return currentSession(DEFAULT);
    }
    /**
     * Cierra la sesión por defecto, para el thread que hace la llamada.
     * 
     * @throws HibernateException
     */
    public static void closeSession() throws HibernateException {
        closeSession(DEFAULT);
    }
}