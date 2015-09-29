package com.mf.controlacceso.to;

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
public class PerfilProcesoTO implements Serializable  {

	private static final long serialVersionUID = -6186051262696498313L;

	@Id
    @Column(name="id_perfil_proceso")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idPerfilProceso;
    
	@ManyToOne
	@JoinColumn(name="id_perfil")
    private PerfilTO perfilTO;	
    
	@ManyToOne
	@JoinColumn(name="id_proceso")
    private ProcesoTO procesoTO;
	
	public PerfilProcesoTO(){
		super();
	}

	public PerfilProcesoTO(ProcesoTO procesoTO, Integer idPerfilProceso, PerfilTO perfilTO){
		
		this.procesoTO = procesoTO;
		this.idPerfilProceso =idPerfilProceso; 
		this.perfilTO = perfilTO;
	} 
	
	public Integer getIdPerfilProceso() {
		return idPerfilProceso;
	}

	public void setIdPerfilProceso(Integer idPerfilProceso) {
		this.idPerfilProceso = idPerfilProceso;
	}

	public ProcesoTO getProceso() {
		return procesoTO;
	}

	public void setProceso(ProcesoTO procesoTO) {
		this.procesoTO = procesoTO;
	}

	public PerfilTO getPerfil() {
		return perfilTO;
	}

	public void setPerfil(PerfilTO perfilTO) {
		this.perfilTO = perfilTO;
	}
	

}