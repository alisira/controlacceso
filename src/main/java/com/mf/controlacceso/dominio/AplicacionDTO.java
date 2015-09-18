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
import javax.persistence.OneToMany;
import javax.persistence.Table;

public class AplicacionDTO implements Serializable  {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7353372867773697188L;


	private Integer idAplicacion;
    private String nombre;
    private String estatus; 
    private String descripcion;
    private List<ProcesoDTO> proceso = new ArrayList<ProcesoDTO>();
    
    public AplicacionDTO(Integer idAplicacion, String nombre, String estatus, String descripcion){
    	this.idAplicacion =idAplicacion ;
    	this.nombre= nombre;
    	this.estatus = estatus;
    	this.descripcion =descripcion;
    }
    
    public AplicacionDTO(){    	
    }

	public List<ProcesoDTO> getProceso() {
		return proceso;
	}

	public void setProceso(List<ProcesoDTO> proceso) {
		this.proceso = proceso;
	}

	public Integer getIdAplicacion() {
		return idAplicacion;
	}

	public void setIdAplicacion(Integer idAplicacion) {
		this.idAplicacion = idAplicacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

}