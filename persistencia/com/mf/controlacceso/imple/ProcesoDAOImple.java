package com.mf.controlacceso.imple;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mf.controlacceso.dao.ProcesoDAO;
import com.mf.controlacceso.modelo.sistema.Proceso;

public class ProcesoDAOImple extends GenericDAOImplHibernate implements ProcesoDAO {
	Session session = null;
	
	public ProcesoDAOImple(){
		super();				
	}

	public List<Proceso> procesoPerfil(Proceso proceso) {
		Session session =  asignaSession(proceso);
		session.beginTransaction();		
		
		List resul = session.createCriteria(Proceso.class)
				.addOrder(Order.asc("jerarquia_menu"))
				.add(Restrictions.eq( "estatus", "A" ))
				.createCriteria("perfilProceso")
					.createCriteria("perfil")
						.add( Restrictions.eq("idPerfil", proceso.getPerfilProceso().get(0).getPerfil().getIdPerfil()))
			    .list();		
		
		/*Manera Alternativa al bloque anterior invocando al objeto crit en cada linea
		Criteria crit = session.createCriteria(proceso.getClass());
		crit.addOrder(Order.asc("jerarquia_menu"));
		crit.add(Restrictions.eq("estatus", "A"));
		crit.createCriteria("perfilProceso").createCriteria("perfil").add(Restrictions.eq("idPerfil", proceso.getPerfilProceso().get(0).getPerfil().getIdPerfil()));
		List resul2 = crit.list();
		*/
		
		session.getTransaction().commit();
		return resul;
	}

}
