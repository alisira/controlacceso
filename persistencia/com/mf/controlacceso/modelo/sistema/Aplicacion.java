package com.mf.controlacceso.modelo.sistema;

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
public class Aplicacion implements Serializable  {

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
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy="aplicacion",cascade= CascadeType.ALL)
    private List<Proceso> proceso = new ArrayList<Proceso>();
    
    public Aplicacion(Integer idAplicacion, String nombre, String estatus, String descripcion){
    	this.idAplicacion =idAplicacion ;
    	this.nombre= nombre;
    	this.estatus = estatus;
    	this.descripcion =descripcion;
    }
    
    public Aplicacion(){    	
    }

	public List<Proceso> getProceso() {
		return proceso;
	}

	public void setProceso(List<Proceso> proceso) {
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