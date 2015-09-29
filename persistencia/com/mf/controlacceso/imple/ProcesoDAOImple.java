package com.mf.controlacceso.imple;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mf.controlacceso.dao.ProcesoDAO;
import com.mf.controlacceso.to.PerfilUsuarioTO;
import com.mf.controlacceso.to.ProcesoTO;

public class ProcesoDAOImple extends GenericDAOImplHibernate implements ProcesoDAO {
	Session session = null;
	
	public ProcesoDAOImple(){
		super();				
	}

	public List<ProcesoTO> procesoPerfil(ProcesoTO procesoTO) {
		Session session =  asignaSession(procesoTO);
		session.beginTransaction();		
		
		List resul = session.createCriteria(ProcesoTO.class)
				.addOrder(Order.asc("jerarquia_menu"))
				.add(Restrictions.eq( "estatus", "A" ))
				.createCriteria("perfilProcesoTO")
						.add( Restrictions.eq("perfilTO.idPerfil", procesoTO.getPerfilProceso().get(0).getPerfil().getIdPerfil()))
			    .list();		
		
		//System.out.println("errof: " + resul.size());
		
		//Criterio in se le puede pasar un collection .add( Restrictions.in("idPerfil", valores))
		
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
	
	public List<ProcesoTO> procesoPerfil(List<PerfilUsuarioTO> perfilUsuarioTO) {
		
		List<Integer> listaPerfil = new LinkedList();
		for(PerfilUsuarioTO i: perfilUsuarioTO){
			listaPerfil.add(i.getPerfil().getIdPerfil());
		}
		
		Session session =  asignaSession(perfilUsuarioTO.get(0));
		session.beginTransaction();
		
		List resul = session.createCriteria(ProcesoTO.class)
				.addOrder(Order.asc("jerarquia_menu"))
				.add(Restrictions.eq( "estatus", "A" ))
				.createCriteria("perfilProcesoTO")
					.createCriteria("perfilTO")
						.add(Restrictions.in("idPerfil", listaPerfil))
			    .list();		

		session.getTransaction().commit();
		return resul;
	}

}
