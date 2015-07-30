package com.mf.controlacceso.modelo.sistema;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="perfil_usuario")
public class PerfilUsuario implements Serializable{

	private static final long serialVersionUID = 5108781693702443115L;

	@Id
    @Column(name="id_perfil_usuario")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idPerfilUsuario;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
 
	@ManyToOne
	@JoinColumn(name="id_perfil")
    private Perfil perfil;
    
	public PerfilUsuario(){
		super();
	}
    
	public PerfilUsuario(Usuario usuario, Integer idPerfilUsuario, Perfil perfil){
		
		this.usuario = usuario;
		this.idPerfilUsuario =idPerfilUsuario; 
		this.perfil =  perfil;
	}    

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getIdPerfilUsuario() {
		return idPerfilUsuario;
	}

	public void setIdPerfilUsuario(Integer idPerfilUsuario) {
		this.idPerfilUsuario = idPerfilUsuario;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
}