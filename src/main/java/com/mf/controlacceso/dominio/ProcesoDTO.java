package com.mf.controlacceso.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


public class ProcesoDTO implements Serializable  {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4395252476726190226L;


	private Integer idProceso;
	

    private String denominacion;
    
    private String estatus;   
    

	private String descripcion;
    

    private String url;

    private String jerarquia_menu;
    

    private String ruta_menu;

	private AplicacionDTO aplicacion;
	

    private List<PerfilProcesoDTO> perfilProceso = new ArrayList<PerfilProcesoDTO>();
	
	public ProcesoDTO(Integer idProceso, String denominacion , String estatus, String descripcion, String url, String jerarquia_menu, String ruta_menu, AplicacionDTO aplicacionDTO){
		this.idProceso=idProceso; 
		this.denominacion =denominacion; 
		this.estatus=estatus; 
		this.descripcion=descripcion; 
		this.url=url; 
		this.jerarquia_menu=jerarquia_menu; 
		this.ruta_menu=ruta_menu; 
		this.aplicacion = aplicacionDTO;			
	}	

	public ProcesoDTO(){
		
	}

	public List<PerfilProcesoDTO> getPerfilProceso() {
		return perfilProceso;
	}

	public void setPerfilProceso(List<PerfilProcesoDTO> perfilProceso) {
		this.perfilProceso = perfilProceso;
	}

	public Integer getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(Integer idProceso) {
		this.idProceso = idProceso;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getJerarquia_menu() {
		return jerarquia_menu;
	}

	public void setJerarquia_menu(String jerarquia_menu) {
		this.jerarquia_menu = jerarquia_menu;
	}

	public String getRuta_menu() {
		return ruta_menu;
	}

	public void setRuta_menu(String ruta_menu) {
		this.ruta_menu = ruta_menu;
	}

	public AplicacionDTO getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(AplicacionDTO aplicacionDTO) {
		this.aplicacion = aplicacionDTO;
	}


}