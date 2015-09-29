package com.mf.controlacceso.dao;

import java.util.List;

import com.mf.controlacceso.to.ProcesoTO;

public interface ProcesoDAO extends GenericDAO {
	
	public List<ProcesoTO> procesoPerfil(ProcesoTO procesoTO);
}
