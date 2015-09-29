package com.mf.controlacceso.to;

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

@Entity
@Table(name="aplicacion")
public class AplicacionTO implements Serializable  {

	private static final long serialVersionUID = -4682833874808260783L;

	@Id
    @Column(name="id_aplicacion")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idAplicacion;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="estatus")
    private String estatus; 
    
    @Column(name="descripcion")
    private String descripcion;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy="aplicacionTO",cascade= CascadeType.ALL)
    private List<ProcesoTO> procesoTO = new ArrayList<ProcesoTO>();
    
    public AplicacionTO(Integer idAplicacion, String nombre, String estatus, String descripcion){
    	this.idAplicacion =idAplicacion ;
    	this.nombre= nombre;
    	this.estatus = estatus;
    	this.descripcion =descripcion;
    }
    
    public AplicacionTO(){    	
    }

	public List<ProcesoTO> getProceso() {
		return procesoTO;
	}

	public void setProceso(List<ProcesoTO> procesoTO) {
		this.procesoTO = procesoTO;
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