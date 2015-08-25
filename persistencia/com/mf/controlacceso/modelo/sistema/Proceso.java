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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="proceso")
public class Proceso implements Serializable  {
	
	private static final long serialVersionUID = 351734763231876102L;

	@Id
    @Column(name="id_proceso")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idProceso;
	
    @Column(name="denominacion")
    private String denominacion;
    
    @Column(name="estatus")
    private String estatus;   
    
    @Column(name="descripcion")
	private String descripcion;
    
    @Column(name="url")
    private String url;
    
    @Column(name="jerarquia_menu")
    private String jerarquia_menu;
    
    @Column(name="ruta_menu")
    private String ruta_menu;
    
	@ManyToOne( cascade= CascadeType.ALL)
	@JoinColumn(name="id_aplicacion")
	private Aplicacion aplicacion;
	
    @OneToMany(fetch = FetchType.LAZY, mappedBy="proceso", cascade= CascadeType.ALL)
    private List<PerfilProceso> perfilProceso = new ArrayList<PerfilProceso>();
	
	public Proceso(Integer idProceso, String denominacion , String estatus, String descripcion, String url, String jerarquia_menu, String ruta_menu, Aplicacion aplicacion){
		this.idProceso=idProceso; 
		this.denominacion =denominacion; 
		this.estatus=estatus; 
		this.descripcion=descripcion; 
		this.url=url; 
		this.jerarquia_menu=jerarquia_menu; 
		this.ruta_menu=ruta_menu; 
		this.aplicacion = aplicacion;			
	}	

	public Proceso(){
		
	}

	public List<PerfilProceso> getPerfilProceso() {
		return perfilProceso;
	}

	public void setPerfilProceso(List<PerfilProceso> perfilProceso) {
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

	public Aplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}


}