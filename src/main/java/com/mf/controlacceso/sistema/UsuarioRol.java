package com.mf.controlacceso.sistema;

import com.mf.controlacceso.dominio.UsuarioDTO;



public class UsuarioRol  implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3842926529607790545L;
	private long idUsuarioRol;	
	private UsuarioDTO usuario;	
	private Rol rol;
	
	public String toString(){
		return usuario.getNombres() + " " + usuario.getApellidos() + " - " + rol.getNombre();
	}

	/**
	 * @return
	 */
	public long getIdUsuarioRol() {
		return idUsuarioRol;
	}

	/**
	 * @return
	 */
	public Rol getRol() {
		return rol;
	}

	/**
	 * @return
	 */
	public UsuarioDTO getUsuario() {
		return usuario;
	}

	/**
	 * @param l
	 */
	public void setIdUsuarioRol(long l) {
		idUsuarioRol = l;
	}

	/**
	 * @param rol
	 */
	public void setRol(Rol rol) {
		this.rol = rol;
	}

	/**
	 * @param usuario
	 */
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

}
