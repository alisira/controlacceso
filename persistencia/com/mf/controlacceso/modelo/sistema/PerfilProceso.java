package com.mf.controlacceso.modelo.sistema;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="perfil_proceso")
public class PerfilProceso implements Serializable  {

	private static final long serialVersionUID = -6186051262696498313L;

	@Id
    @Column(name="id_perfil_proceso")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idPerfilProceso;
    
	@ManyToOne
	@JoinColumn(name="id_perfil")
    private Perfil perfil;	
    
	@ManyToOne
	@JoinColumn(name="id_proceso")
    private Proceso proceso;
	
	public PerfilProceso(){
		super();
	}

	public PerfilProceso(Proceso proceso, Integer idPerfilProceso, Perfil perfil){
		
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

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	

}