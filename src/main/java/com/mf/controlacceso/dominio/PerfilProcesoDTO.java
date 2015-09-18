package com.mf.controlacceso.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

public class PerfilProcesoDTO implements Serializable  {
	
	private Integer idPerfilProceso;
    private PerfilDTO perfil;
    private ProcesoDTO proceso;
	
	public PerfilProcesoDTO(){
		super();
	}

	public PerfilProcesoDTO(ProcesoDTO proceso, Integer idPerfilProceso, PerfilDTO perfil){
		
		this.proceso = proceso;
		this.idPerfilProceso =idPerfilProceso; 
		this.perfil = perfil;
	} 
	
	public Integer getIdPerfilProceso() {
		return idPerfilProceso;
	}

	public void setIdPerfilProceso(Integer idPerfilProceso) {
		this.idPerfilProceso = idPerfilProceso;
	}

	public ProcesoDTO getProceso() {
		return proceso;
	}

	public void setProceso(ProcesoDTO procesoDTO) {
		this.proceso = procesoDTO;
	}

	public PerfilDTO getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilDTO perfil) {
		this.perfil = perfil;
	}
	

}