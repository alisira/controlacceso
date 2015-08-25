package com.mf.controlacceso.modelo.sistema;

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



@Entity
@Table(name="perfil")
public class Perfil implements Serializable  {

	private static final long serialVersionUID = 9193451386166929186L;

	@Id
    @Column(name="id_perfil")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idPerfil;
    
    @Column(name="denominacion")
    private String denominacion;
    
    @Column(name="id_aplicacion")
	private Integer idAplicacion;
    
    @Column(name="estatus")
    private String estatus;    
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy="perfil", cascade= CascadeType.ALL)
    private List<PerfilUsuario> perfilUsuario = new ArrayList<PerfilUsuario>();
    
    Perfil(Integer idPerfil, String denominacion, Integer idAplicacion, String estatus){
    	this.idPerfil = idPerfil;
    	this.denominacion=denominacion;
    	this.idAplicacion=idAplicacion;
    	this.estatus=estatus;
    }
    
    public Perfil(){
    	super();
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

}