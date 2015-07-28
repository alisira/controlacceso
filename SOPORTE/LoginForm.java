package com.mf.controlacceso.facade.sistema;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import sigefirrhh.base.estructura.EstructuraFacade;
import sigefirrhh.base.estructura.Organismo;
import sigefirrhh.sistema.RegistrarAuditoria;
import sigefirrhh.sistema.SistemaFacade;
import sigefirrhh.sistema.Usuario;
import sigefirrhh.sistema.UsuarioUnidadFuncionalForm;
import eforserver.presentation.Form;
import eforserver.tools.PasswordTools;

/**
 * Provee servicios para el manejo de eventos y metodos relacionados con la capa de presentacion del
 * objeto Login
 *
 */
public class Login extends Form {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private String usuario;
	private String password;
	private Collection colOrganismo;

	private EstructuraFacade estructuraFacade = new EstructuraFacade();

	private String selectOrganismo;
	private Organismo organismo;
	
	private SistemaFacade sistemaFacade = new SistemaFacade();
	
	public String getSelectOrganismo() {
		return this.selectOrganismo;
	}
	
	public LoginForm() throws Exception {
		this.refresh();
	}
	
	public void setSelectOrganismo(String valOrganismo) {
		Iterator iterator = this.colOrganismo.iterator();
		Organismo organismo = null;
		while (iterator.hasNext()) {
			organismo = (Organismo)iterator.next();
			if ( String.valueOf(organismo.getIdOrganismo()).equals(
				valOrganismo)) {
				this.organismo = organismo;
			}
		}
		this.selectOrganismo = valOrganismo;
	}
	
	public Collection getColOrganismo() {
		Collection col = new ArrayList();
		if (this.colOrganismo!=null) {
			Iterator iterator = this.colOrganismo.iterator();
			Organismo organismo = null;
			while (iterator.hasNext()) {
				organismo = (Organismo)iterator.next();
				col.add(new SelectItem(
					String.valueOf(organismo.getIdOrganismo()),
					organismo.toString()));
			}
		}
		return col;
	}
	
	public void refresh() {
		try {
			this.colOrganismo = 
				this.estructuraFacade.findAllOrganismo();
		} catch (Exception e) {
			log.error("Excepcion controlada:",e);
		}
	}
	public String send() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		Collection colUsuario = null;
		PasswordTools passwordTools=PasswordTools.getInstance();
		log.error("@@ Antes de verificar Usuario");
		try {
			colUsuario = this.sistemaFacade.findUsuarioByPassword(this.usuario, this.password);
		} catch (Exception e) {
			log.error("Excepcion controlada:",e);
			context.addMessage("error_data", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al accesar la base de datos", ""));
			return null;
		}
		
		//RJB par�metro para determinar cuantos intentos de login se deben contar antes de desactivar el usuario
		int maxIntentos = 4;
		try{
			maxIntentos = this.sistemaFacade.getParametroMaxIntentos();
		}catch(Exception e){
			maxIntentos = 4;
		}

//		Usuario usuario = (Usuario)colUsuario.iterator().next();
		
		//RJB Est� iterando, no deber�a ya que se supone que la colecci�n tiene un solo registro
		//if ((colUsuario!=null) && (!colUsuario.isEmpty()) && passwordTools.chequear(this.password,((Usuario)colUsuario.iterator().next()).getPassword())) { // trajo informacion valida
		if ((colUsuario!=null) && (!colUsuario.isEmpty()) ) { // trajo informacion valida
			Usuario usuario = (Usuario)colUsuario.iterator().next();
			
			//RJB antes que nada cantidad de intentos fallidos
			if (usuario.getIntentos() >= maxIntentos){
				context.addMessage("error_data", new FacesMessage(FacesMessage.SEVERITY_ERROR, "El usuario se bloquear� por cantidad de intentos fallidos", ""));					
				//RJB bloquear el usuario				
				usuario.setActivo("N");								
				try {
					this.sistemaFacade.updateUsuario(usuario);
					//RJB Anotar cuando se bloque� el usuario
					RegistrarAuditoria.registrar(context, usuario, RegistrarAuditoria.MODIFICAR,"Se inactiv� el usuario luego de "+String.valueOf(usuario.getIntentos())+" fallidos");
					
				} catch (Exception e) {
					return null;
				}
				return null;
			}

			//RJB al usuario actual es al que se debe validar la contrase�a
			if (!passwordTools.chequear(this.password,usuario.getPassword())){
				context.addMessage("error_data", new FacesMessage(FacesMessage.SEVERITY_ERROR, "El usuario y/o la contrase�a no son validos", ""));					
				//RJB aumentar la cantidad de intentos fallidos				
				usuario.setIntentos(usuario.getIntentos()+1);				
				
				try {
					this.sistemaFacade.updateUsuario(usuario);
				} catch (Exception e) {
					return null;
				}
				return null;
			}
			
			LoginSession session = 
				(LoginSession)context.getApplication().getVariableResolver().resolveVariable(
				context,
				"loginSession");
		
			try{
				
				//RJB la primera validaci�n tiene que ser si la cuenta est� activa				
				if (!usuario.getActivo().equals("S")){
					context.addMessage("error_data", new FacesMessage(FacesMessage.SEVERITY_ERROR, "El usuario "+usuario.getUsuario()+" no est� activo", "Consulte con el administrador del sistema si su cuenta de usuario est� activa"));					
					//RJB Anotar cuando el usuario intent� ingresar al sistema
					RegistrarAuditoria.registrar(context, usuario, RegistrarAuditoria.SALIR,"Usuario inactivo intent� ingresar al sistema");
					return null;
				}
				
				//RJB la segunda validaci�n tiene que ser si la cuenta de usuario est� vencida				
				Calendar c = Calendar.getInstance();
				Date hoy = c.getTime();								
				if (usuario.getFechaVence()!=null && hoy.after(usuario.getFechaVence())){
					context.addMessage("error_data", new FacesMessage(FacesMessage.SEVERITY_ERROR, "El usuario "+usuario.getUsuario()+" est� vencido", "Consulte con el administrador del sistema si la fecha de vencimiento de usuario est� vigente"));					
					//RJB bloquear el usuario				
					usuario.setActivo("N");								
					try {
						this.sistemaFacade.updateUsuario(usuario);
						//RJB Anotar cuando se bloque� el usuario
						RegistrarAuditoria.registrar(context, usuario, RegistrarAuditoria.MODIFICAR,"Inactivado el usuario, fecha de vencimiento: "+usuario.getFechaVence().toString());
						
					} catch (Exception e) {
						return null;
					}
					return null;
				}
												
					if (usuario.getAdministrador().equals("N")){
						Collection colUsuarioOrganismo = sistemaFacade.findUsuarioOrganismoByUsuario(usuario.getIdUsuario(), this.organismo.getIdOrganismo());				
						if (colUsuarioOrganismo.size()==0){ /// no tiene permiso en el organismo 
							context.addMessage("error_data", new FacesMessage(FacesMessage.SEVERITY_ERROR, "EL usuario no posee permiso sobre este organismo", ""));					
							return null;
						}
					}

					//RJB Restablecer el contador de intentos fallidos				
					usuario.setIntentos(0);								
					try {
						this.sistemaFacade.updateUsuario(usuario);
					} catch (Exception e) {
						return null;
					}
					
					//RJB buscar el par�metro que indica la cantidad de d�as que dura la clave
					int diasDuracionClave=this.sistemaFacade.getParametroDiasDuracionClave();
					
					//RJB la siguiente validaci�n esta fija para cambiar de contrase�a cada 3 meses
					//session.setPasswordVencido(usuario.cambiaPassword()); // valido si tiene que cambiar el password
					//RJB la siguiente validaci�n se hace tomando en cuenta un par�metro que indica cada cuantos d�as se debe cambiar la clave
					session.setPasswordVencido(usuario.cambiaPasswordDias(diasDuracionClave));
					if(session.isPasswordVencido()){ 
						context.addMessage("error_data", new FacesMessage(FacesMessage.SEVERITY_ERROR, "EL usuario tiene que cambiar su clave", ""));
						ExternalContext externalContext = context.getExternalContext();
						externalContext.redirect("sigefirrhh/login/ChangePassword.jsf");
					}											
						
			} catch (Exception e) {
				log.error("Excepcion controlada:",e);
				return null;
			}
				
			
			session.setServicioPersonal(false);
			session.setOrganismo(organismo);
			session.setUsuario(this.usuario);
		
			Collection colUsuarioRol = new ArrayList();			
			usuario = new Usuario();
			usuario = (Usuario)colUsuario.iterator().next();
			usuario.setIdOrganismo(this.organismo.getIdOrganismo());
			session.setIdUsuario(usuario.getIdUsuario());
			session.setAdministrador(usuario.getAdministrador());
			session.setUsuarioObject(usuario);

			try {
				
					colUsuarioRol = sistemaFacade.findUsuarioRolByUsuario(usuario.getIdUsuario());
					session.setColUsuarioRol(colUsuarioRol);
					
					//RJB Anotar cuando ingres� al sistema
					RegistrarAuditoria.registrar(context, usuario, RegistrarAuditoria.INGRESAR,"Ingres� al sistema");
					
					ExternalContext externalContext = context.getExternalContext();
					HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
					externalContext.redirect("home.jsf");
					//log.error("LOGIN 3");
			} catch (Exception e) {
				log.error("Excepcion controlada:",e);
			}

			context.responseComplete();
			//log.error("LOGIN 4");
			return "success";

		} else {
			context.addMessage("error_data", new FacesMessage(FacesMessage.SEVERITY_ERROR, "El usuario y/o la contrase�a no son validos", ""));
		}
		return null;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setPassword(String string) {
		password = string;
	}

	public void setUsuario(String string) {
		usuario = string;
	}

	public String getPassword() {
		return password;
	}

}
