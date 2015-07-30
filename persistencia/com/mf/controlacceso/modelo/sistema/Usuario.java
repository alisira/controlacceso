package com.mf.controlacceso.modelo.sistema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable  {
    
	private static final long serialVersionUID = 5837444858026193720L;

	@Id
    @Column(name="id_usuario")
	private Integer idUsuario;
    
    @Column(name="login")
    private String login;
    
    @Column(name="cedula")
	private Integer cedula;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="apellido")
    private String apellido;
    
    @Column(name="password")
    private String password;
 
    @Column(name="unidad_funcional")
    private String unidad_funcional;
    
    @Column(name="unidad_fisica")
    private String unidad_fisica;
    
    @Column(name="telefono1")
    private String telefono1;

    @Column(name="telefono2")
    private String telefono2;
    
    @Column(name="email")
    private String email;
    
    @Column(name="fecha_inact")
    private Date fecha_inact;    

    @Column(name="estatus")
    private String estatus;   
        
    @Column(name="intentos")
    private Integer intentos;   
        
    @Column(name="cambio_password")
	private Date cambio_password;
    
    @Column(name="administrador")
    private boolean administrador;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy="usuario",cascade= CascadeType.ALL)
    private List<PerfilUsuario> perfilUsuario = new ArrayList<PerfilUsuario>();
    

    public Usuario(){
		
	}
    
public Usuario(Integer idUsuario, String login,Integer cedula, String nombre,String apellido

,String password
,String unidad_funcional
,String unidad_fisica
,String telefono1
,String telefono2
,String email
,Date fecha_inact
,String estatus
,Integer intentos
,Date cambio_password,boolean administrador){
		this.idUsuario =idUsuario;
	    this.login =login;
		this.cedula =cedula ;
	    this.nombre =nombre;
	    this.apellido =apellido;
	    this.password =password;
	    this.unidad_funcional =unidad_funcional;
	    this.unidad_fisica =unidad_fisica;
	    this.telefono1 =telefono1;
	    this.telefono2 =telefono2;
	    this.email= email;
	    this.fecha_inact=fecha_inact;
	    this.estatus=estatus;
	    this.intentos=	intentos;        
	    this.cambio_password=cambio_password;
	    this.administrador=administrador;
		
	}

	
	public List<PerfilUsuario> getPerfilUsuario() {
		return perfilUsuario;
	}
	
	public void setPerfilUsuario(List<PerfilUsuario> perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Integer getCedula() {
		return cedula;
	}

	public void setCedula(Integer cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUnidad_funcional() {
		return unidad_funcional;
	}

	public void setUnidad_funcional(String unidad_funcional) {
		this.unidad_funcional = unidad_funcional;
	}

	public String getUnidad_fisica() {
		return unidad_fisica;
	}

	public void setUnidad_fisica(String unidad_fisica) {
		this.unidad_fisica = unidad_fisica;
	}

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFecha_inact() {
		return fecha_inact;
	}

	public void setFecha_inact(Date fecha_inact) {
		this.fecha_inact = fecha_inact;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Integer getIntentos() {
		return intentos;
	}

	public void setIntentos(Integer intentos) {
		this.intentos = intentos;
	}

	public Date getCambio_password() {
		return cambio_password;
	}

	public void setCambio_password(Date cambio_password) {
		this.cambio_password = cambio_password;
	}

	public boolean getAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

}