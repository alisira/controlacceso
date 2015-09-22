package com.mf.controlacceso.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


public class PerfilDTO implements Serializable  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2107990218890824473L;
	private Integer idPerfil;   
    private String denominacion;
	private Integer idAplicacion;
    private String estatus;    

    private List<PerfilUsuarioDTO> perfilUsuario;
    private List<PerfilProcesoDTO> perfilProceso;
    
    PerfilDTO(Integer idPerfil, String denominacion, Integer idAplicacion, String estatus){
    	this.idPerfil = idPerfil;
    	this.denominacion=denominacion;
    	this.idAplicacion=idAplicacion;
    	this.estatus=estatus;
    }
    
    public PerfilDTO(){
    	super();
    }

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public Integer getIdAplicacion() {
		return idAplicacion;
	}

	public void setIdAplicacion(Integer idAplicacion) {
		this.idAplicacion = idAplicacion;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public List<PerfilUsuarioDTO> getPerfilUsuario() {
		return perfilUsuario;
	}

	public void setPerfilUsuario(List<PerfilUsuarioDTO> perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}

	public List<PerfilProcesoDTO> getPerfilProceso() {
		return perfilProceso;
	}

	public void setPerfilProceso(List<PerfilProcesoDTO> perfilProceso) {
		this.perfilProceso = perfilProceso;
	}
	

}