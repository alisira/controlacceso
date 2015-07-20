package com.mf.controlacceso.imple;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.mf.controlacceso.dao.GenericDAO;
import com.mf.hibernate.util.HibernateUtil;


public class GenericDAOImplHibernate implements GenericDAO {

    SessionFactory sessionFactory;

    private final static Logger LOGGER = Logger.getLogger(GenericDAOImplHibernate.class.getName());

    public GenericDAOImplHibernate() {
        sessionFactory=HibernateUtil.getSessionFactory();
    }    

    public void saveOrUpdate(Object entity)  {
    	Session session = sessionFactory.getCurrentSession();
    	session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
    }
    
    public List listar(Object entity)  {//Recuerda ponerlo en la interface
    	Session session = sessionFactory.getCurrentSession();
    	session.beginTransaction();
    	
		List lista =null;
        Criteria crit = session.createCriteria(entity.getClass());
        Method metodos[] = entity.getClass().getMethods();        
        for (int i = 0; i < metodos.length; i++) {
        	Method field = metodos[i];        	
        	
        	if (field.getName().indexOf("get") > -1){
        		//System.out.println(field.getName() +" - "+field.getReturnType()+" - "+field.getModifiers() +" - "+field.getDefaultValue() +" - "+field.getGenericReturnType());
        		
        		if (!field.getName().equals("getClass")){
	    			//if (field.getName().equals("getCompromisoInicial")){
		        			
        	        Class clazz=entity.getClass();
        			Method m;
        			
        			Integer rInt = 0;	        			
        			String rStr = "";
        			Date rDate = null;
        			
        			try {
						m = clazz.getDeclaredMethod(field.getName());	
						
						if (field.getReturnType().toString().equals("class java.lang.Integer")){
							rInt = (Integer)m.invoke(entity);
							if (rInt!=null){
								//System.out.println(field.getName() +" - " + rInt);
								//System.out.println(field.getName().substring(3).toLowerCase() +" - " + rInt);
								crit.add(Restrictions.eq(field.getName().substring(3, 4).toLowerCase() + field.getName().substring(4), rInt));
							}
						}else if (field.getReturnType().toString().equals("class java.lang.String")){
							rStr= (String)m.invoke(entity);
							if (rStr!=null){
								crit.add(Restrictions.eq(field.getName().substring(3, 4).toLowerCase() + field.getName().substring(4), rStr));
							}
						}else if (field.getReturnType().toString().equals("class java.util.Date")){
							rDate= (Date)m.invoke(entity);
							if (rDate!=null){
								crit.add(Restrictions.eq(field.getName().substring(3, 4).toLowerCase() + field.getName().substring(4), rDate));
							}
						}
						
					} catch (NoSuchMethodException e) {						
						e.printStackTrace();
					} catch (SecurityException e) {						
						e.printStackTrace();
					} catch (IllegalAccessException e) {						
						e.printStackTrace();
					} catch (IllegalArgumentException e) {						
						e.printStackTrace();
					} catch (InvocationTargetException e) {						
						e.printStackTrace();
					} 
        		}        		
        	}
            
        }
        
        //crit.add(Restrictions.eq("CompromisoInicial", comproIni.getCompromisoInicial()));
        //crit.add(Restrictions.eq("ano", comproIni.getAno()));
        //Restrictions.eq("CompromisoInicial", comproIni.getCompromisoInicial());
        lista = crit.list();       
        session.getTransaction().commit();
		return lista;
		
    }

	public void create() {
		// TODO Auto-generated method stub		
	}

	public Object get(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(Object id) {
		// TODO Auto-generated method stub		
	}

	public List<Object> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
