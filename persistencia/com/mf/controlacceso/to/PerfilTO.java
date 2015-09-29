package com.mf.controlacceso.to;

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
public class PerfilTO implements Serializable  {

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
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy="perfilTO", cascade= CascadeType.ALL)
    private List<PerfilUsuarioTO> perfilUsuarioTO = new ArrayList<PerfilUsuarioTO>();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy="perfilTO", cascade= CascadeType.ALL)
    private List<PerfilProcesoTO> perfilProcesoTO = new ArrayList<PerfilProcesoTO>();
    
    PerfilTO(Integer idPerfil, String denominacion, Integer idAplicacion, String estatus){
    	this.idPerfil = idPerfil;
    	this.denominacion=denominacion;
    	this.idAplicacion=idAplicacion;
    	this.estatus=estatus;
    }
    
    public PerfilTO(){
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

	public List<PerfilUsuarioTO> getPerfilUsuario() {
		return perfilUsuarioTO;
	}

	public void setPerfilUsuario(List<PerfilUsuarioTO> perfilUsuarioTO) {
		this.perfilUsuarioTO = perfilUsuarioTO;
	}

	public List<PerfilProcesoTO> getPerfilProceso() {
		return perfilProcesoTO;
	}

	public void setPerfilProceso(List<PerfilProcesoTO> perfilProcesoTO) {
		this.perfilProcesoTO = perfilProcesoTO;
	}

	

}