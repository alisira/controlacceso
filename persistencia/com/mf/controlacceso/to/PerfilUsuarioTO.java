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
@Table(name="perfil_usuario")
public class PerfilUsuarioTO implements Serializable{

	private static final long serialVersionUID = 5108781693702443115L;

	@Id
    @Column(name="id_perfil_usuario")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idPerfilUsuario;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private UsuarioTO usuarioTO;
 
	@ManyToOne
	@JoinColumn(name="id_perfil")
    private PerfilTO perfilTO;
    
	public PerfilUsuarioTO(){
		super();
	}
    
	public PerfilUsuarioTO(UsuarioTO usuarioTO, Integer idPerfilUsuario, PerfilTO perfilTO){		
		this.usuarioTO = usuarioTO;
		this.idPerfilUsuario =idPerfilUsuario; 
		this.perfilTO =  perfilTO;
	}    

	public UsuarioTO getUsuario() {
		return usuarioTO;
	}

	public void setUsuario(UsuarioTO usuarioTO) {
		this.usuarioTO = usuarioTO;
	}

	public Integer getIdPerfilUsuario() {
		return idPerfilUsuario;
	}

	public void setIdPerfilUsuario(Integer idPerfilUsuario) {
		this.idPerfilUsuario = idPerfilUsuario;
	}

	public PerfilTO getPerfil() {
		return perfilTO;
	}

	public void setPerfil(PerfilTO perfilTO) {
		this.perfilTO = perfilTO;
	}
	
}