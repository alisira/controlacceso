package com.mf.controlacceso.dominio;

import java.io.Serializable;

public class PerfilUsuarioDTO implements Serializable{

	private static final long serialVersionUID = 5734099761179063113L;
	private Integer idPerfilUsuario;
	private UsuarioDTO usuario;
    private PerfilDTO perfil;
    
	public Integer getIdPerfilUsuario() {
		return idPerfilUsuario;
	}
	public void setIdPerfilUsuario(Integer idPerfilUsuario) {
		this.idPerfilUsuario = idPerfilUsuario;
	}
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	public PerfilDTO getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilDTO perfil) {
		this.perfil = perfil;
	}
 

	
}