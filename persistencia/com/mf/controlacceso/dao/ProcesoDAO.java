package com.mf.controlacceso.dao;

import java.util.List;

import com.mf.controlacceso.modelo.sistema.Proceso;

public interface ProcesoDAO extends GenericDAO {
	
	public List<Proceso> procesoPerfil(Proceso proceso);
}
