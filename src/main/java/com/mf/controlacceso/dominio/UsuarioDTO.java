package com.mf.controlacceso.dominio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UsuarioDTO  implements Serializable {

	private static final long serialVersionUID = 8204141610579789865L;
	private Integer idUsuario;
    private String login;
	transient String password;
	private Integer cedula;
	private String apellido;
	private String nombre;
	private String telefono;
	private boolean administrador=false;
	private Date cambioPassword;
	private Integer intentos;
    private String telefono1;
    private String telefono2; 
    private String email;
    private Date fecha_inact;  
    private String estatus;
    private String unidad_funcional;
    private String unidad_fisica;
    private List<PerfilUsuarioDTO> perfilUsuario;

    
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<PerfilUsuarioDTO> getPerfilUsuario() {
		return perfilUsuario;
	}

	public void setPerfilUsuario(List<PerfilUsuarioDTO> perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	
	//RJB 
	/**
	 * Devuelve la cantidad de intentos de login fallidos desde el �ltimo login correcto
	 * @return cantidad de intentos fallidos
	 */


	//RJB 
	/**
	 * Establece la cantidad de intentos de login fallidos
	 * @param intentos
	 */

	
	public Integer getCedula() {
		return cedula;
	}

	public void setCedula(Integer cedula) {
		this.cedula = cedula;
	}

	public Integer getIntentos() {
		return intentos;
	}

	public void setIntentos(Integer intentos) {
		this.intentos = intentos;
	}


	//RJB 
	/**
	 * Fecha de Vencimiento de la cuenta 
	* @jdo.field
	* 	persistence-modifier="persistent"
	* 	key="length" value="max 1"
	* @efordoclet-application
	*/
	private Date fechaVence;
	
	//RJB 
	/**
	 * Establece la fecha de vencimiento de la cuenta de usuario
	 * @param fecha
	 */
	public void setFechaVence(Date fecha){
		this.fechaVence=fecha;
	}
	
	//RJB 
	/**
	 * Devuelve la fecha de vencimiento de la cuenta, puede ser null
	 * @return la fecha de vencimiento de la cuenta o null
	 */
	public Date getFechaVence(){
		return this.fechaVence;
	}	
	
	public String toString(){
					return this.login + "  -  " +
					this.apellido + "  -  " +
					this.nombre + " - " + cedula;
			}




	public String getPassword() {
		return password;
	}

	public Date getCambioPassword() {
		return this.cambioPassword;
	}


	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}


	public void setPassword(String string) {
		password = string;
	}


	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param string
	 */
	public void setTelefono(String string) {
		telefono = string;
	}

	public void setCambioPassword(Date fecha) {
		this.cambioPassword = fecha;
	}

	 public boolean cambiaPassword() {  

		 Calendar cal = Calendar.getInstance();  

		 cal.setTime(new Date());  
		 int minuendMonth =  cal.get(Calendar.MONTH);  
		 int minuendYear = cal.get(Calendar.YEAR); 
		 int minuedDay = cal.get(Calendar.DAY_OF_MONTH);
		 cal.setTime(this.cambioPassword);  
		 int subtrahendMonth =  cal.get(Calendar.MONTH);  
		 int subtrahendYear = cal.get(Calendar.YEAR);  
		 int subtraDay = cal.get(Calendar.DAY_OF_MONTH);
		 
		 int ajuste = 0; // ajuste porsi no se ha cumplido el mes todav�a
		 if(minuedDay < subtraDay){ 
			 ajuste = -1;
		 }
		 
		 int diferencia = ((minuendYear - subtrahendYear) * cal.getMaximum(Calendar.MONTH)) + (minuendMonth - subtrahendMonth) + ajuste;
		 

		 if(diferencia>=3) {
			 return true;
		 }else{
			 return false;
		 }
		 
	 }

	 //RJB
	 /**
	  * Devuelve verdadero en caso de que el usuario est� en la obligaci�n de cambiar la clave
	  * @param diasDuracionClave cada cuantos dias se debe cambiar la clave
	  * @return
	  */
	public boolean cambiaPasswordDias(int diasDuracionClave) {
			Calendar c = Calendar.getInstance();
			Date hoy = c.getTime();		
			//Establecer la fecha de cambio de clave
			c.setTime(this.cambioPassword);
			//Sumar los d�as de duracion de la clave
			c.add(Calendar.DATE, diasDuracionClave);
			//Tomar la fecha en que qued�
			Date fechaVenceClave = c.getTime();
			//Verificar si hay que cambiar la clave
			if (hoy.after(fechaVenceClave)){
				//La fecha venci� antes de hoy
				return true;
			}else{
				//La fecha vence despu�s de hoy
				return false;
			}
	}	
	
	
}
