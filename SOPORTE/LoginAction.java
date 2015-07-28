package ve.gob.mf.sigecof.workflow.interfaznegociadora.actions;


import java.util.HashMap;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import bsh.util.Util;

import ve.gob.mf.sigecof.workflow.interfaznegociadora.actionForms.BusquedasAF;
import ve.gob.mf.sigecof.workflow.interfaznegociadora.actionForms.LoginAF;
import ve.gob.mf.sigecof.workflow.interfaznegociadora.actionForms.MensajesAF;
import ve.gob.mf.sigecof.workflow.negocio.bdl.ServiciosInterfazNegociadoraBDL;
import ve.gob.mf.sigecof.workflow.negocio.bdl.ServiciosMotorWorkflowBDL;
import ve.gob.mf.sigecof.workflow.negocio.dao.ConstanteBDWorkFlow;
import ve.gob.mf.sigecof.workflow.negocio.to.ExpedienteTO;
import ve.gob.mf.sigecof.workflow.negocio.to.MensajeOnlineTO;
import ve.gob.mf.sigecof.workflow.negocio.to.PaginaTO;
import ve.gob.mf.sigecof.workflow.negocio.to.SesionTO;
import ve.gob.mf.sigecof.workflow.negocio.to.UsuarioWFTO;



/**
 * @version 	1.0
 * @author
 */
 public class LoginAction extends Action
{   
	private static Logger logger = Logger.getLogger(LoginAction.class);	
	private transient ServiciosInterfazNegociadoraBDL serviciosInterfazNegociadoraBDL  = null;	
    //private transient ServiciosMotorWorkflowBDL motor  = new ServiciosMotorWorkflowBDL(CONFIGURACION_CONTEXTO,CONFIGURACION_LOG);
	public static final String CONFIGURACION_CONTEXTO = "configuracionContexto-InterfazNegociadora.config";
	public static final String CONFIGURACION_LOG = "log4J-InterfazNegociadora.config";
	
    /**  
     *  El constructor inicializara el BDL de la aplicacion
     */
    public LoginAction()
    { }
    
    public ServiciosInterfazNegociadoraBDL getBDL()
    {
    	logger.info("ServiciosInterfazNegociadoraBDL ");
        if (serviciosInterfazNegociadoraBDL == null)
        {
            serviciosInterfazNegociadoraBDL = new ServiciosInterfazNegociadoraBDL(CONFIGURACION_CONTEXTO,CONFIGURACION_LOG);
        }
    
        return serviciosInterfazNegociadoraBDL;
    }

	
    
	public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {		
 
	    logger.info("Entrado al metodo ejecucion del  execute");
        ActionMessages errors = new ActionMessages();
        ActionForward forward = new ActionForward(); // return value
        
        try {
                        
               //tomando el actionForm de la session
              LoginAF loginAF= (LoginAF) form;
              
              String clave=loginAF.getUsuario().getClave();
              
              
              //vericando la session
              HttpSession session = request.getSession(false);
              //verificando que ya se habia alcanzado el maximo
              if(!loginAF.isMaximo())
              {   
              //verificando el login y password estan registrado correctamente
              UsuarioWFTO usuarioTO=this.getBDL().validarUsuario(loginAF.getUsuario());
              loginAF.setUsuario(usuarioTO);
              
              if(!usuarioTO.isRegistrado())
              {
                  logger.error("Login o password inv�lidos");
                  errors.add("error.workflow.LoginClaveInvalida", new ActionMessage("error.workflow.LoginClaveInvalida"));
                  loginAF.getUsuario().setClave("");
              }
              else
              {
                   loginAF.getUsuario().setClave(clave);
	               if(usuarioTO.getEstado().equals("I"))   
	                 {
	                   logger.error("Usuario registrado pero esta inactivo");
	                   errors.add("error.workflow.usuarioInativo", new ActionMessage("error.workflow.usuarioInativo"));	                   
	                 }
	               else
	                {  
		                //verificando si el maximo de conexiones activas permitidas para el usuario
		                  if(this.getBDL().validarMaximoSesionesActivas(usuarioTO))
		                  {   logger.error("maximo de conexiones alcanzadas");
		                      errors.add("error.workflow.maximoConexiones", new ActionMessage("error.workflow.maximoConexiones"));
		                      loginAF.setMaximo(true);		                    
		                  }
		                  else
		                  {   
		                      logger.error("Session valida 1");
		                      SesionTO sesionTO=new SesionTO();
		                      //tomando informacion de la maquina remota
		                      sesionTO.setIdUsuario(usuarioTO.getIdUsuario());
		                      sesionTO.setIpMaquina(request.getRemoteAddr());
		                      logger.info("IP"+sesionTO.getIpMaquina());
		                      sesionTO.setMaquina(request.getRemoteHost());		                      
		                      //creando una sesion mas para el usuario
		                      sesionTO=this.getBDL().crearSesion(sesionTO);		                      		                      
		                      loginAF.setSesion(sesionTO);
		                      // tomando la unidad administradora a la que pertenece el usuario
		                      loginAF.setUsuario(this.getBDL().getUnidadAdministradora(loginAF.getUsuario()));
		                      
		                      
		                      if(this.getBDL().debeCambiarClave(usuarioTO))
		                        { 
		                    	  logger.error("debeCambiarClave");
		                          loginAF.getUsuario().setClave("");
		                          loginAF.setEntrada(0);
		                          loginAF.setCambiarClave(true);
		                          forward = mapping.findForward("cambiar");		                          
		                        } 
		                     else
		                        { 
		                    	  loginAF.setEntrada(2);
		                          loginAF.getUsuario().setClave("");
		                          loginAF.setCambiarClave(false);
		                          //loginAF.setMensajes(Boolean.FALSE);
		                          forward = mapping.findForward("tareas");
		                          int cantidadMensajes=0;
		                          //int cantidadMensajes=this.getBDL().getCantidadMensajesPorEstado("",loginAF.getUsuario().getIdUsuario());
		                          if (logger.isDebugEnabled())logger.info("cantidad de mensajes "+cantidadMensajes);
		                          if  ( cantidadMensajes>0){
		                        	  
		                          	loginAF.setMensajes(true);
		                          	MensajesAF mensajesAF =new MensajesAF();
		                          	if( loginAF.getUsuario().getRol().equals(ConstanteBDWorkFlow.rolEnvio.rolUsuario)){
		                          		 mensajesAF.setRolEnvio(true);
		                            }
		                          	 
		                          	int boton=mensajesAF.getBoton();           
		                          	 
		                            PaginaTO pagina=mensajesAF.getPagina();
		                               
		                               //hacer el proceso             
		                            mensajesAF.setVacio(true);
		                            
		                	        //busqueda por filtro primera vez o el boton inicio 
		                	        if(boton==0 || boton==1)
		                            {
		                	        	 
		                	        	 pagina.setPagina(1);      
		                	        }
		                	             //anterior
		                	        if(boton==2)
		                	        {
		                	        	 
		                	        	 pagina.setPagina(pagina.getPagina()-1);   
		                	        }  
		                	        //siguiente
		                	        if(boton==3)
		                	        {
		                	        	 
		                	           pagina.setPagina(pagina.getPagina()+1);   
		                	        } 
		                	        //Final
		                	        if(boton==4)
		                	        {
		                	        	 
		                	        	 pagina.setPagina(pagina.getUltimaPagina());   
		                	        }
		                	        
		                	        logger.info("boton"+boton);
		                	        logger.error("boton");   
		                	        logger.error("msg request.getSession(false).setAttribute(mensajesAF,mensajesAF) ");
		                            request.getSession(false).setAttribute("mensajesAF",mensajesAF);
		                            
		                            	
		                            //pagina=this.getBDL().getMensajesPorUsuario(loginAF.getUsuario().getIdUsuario(),"1",pagina.getPagina(),"T");                 
		                 	     
		                        
		                            if(pagina.getElementosTotales()==0)
		                            {
		                            	pagina.setUltimaPagina(0);  
		                            }
		                     
		                            HashMap mapaHash=(HashMap)request.getSession(false).getAttribute("mapaHash");
		                            if (mapaHash==null){
		                            	mapaHash=new HashMap();
		                            }
		                            request.getSession(false).setAttribute("mapaHash",mapaHash);
		                            //request.getSession(false).setAttribute("mapaHash",mapaHash);
		                            mensajesAF.setPagina(pagina);     
		                            
		                            
		                    		//forward = mapping.findForward("mensajes");
		                    		if (logger.isDebugEnabled())logger.info("El usuario tiene mas de un mensaje");             			
		                        
		                        
		                        }  
		                          
		                        } 
		                  }
	                }  
              }
              }
              else
              {                     
                  logger.error("Session valida 2");
                  UsuarioWFTO usuarioTO=loginAF.getUsuario();
                  SesionTO sesionTO=new SesionTO();
                  //tomando informacion de la maquina remota
                  sesionTO.setIdUsuario(usuarioTO.getIdUsuario());
                  sesionTO.setIpMaquina(request.getRemoteAddr());
                  logger.info("IP"+sesionTO.getIpMaquina());
                  sesionTO.setMaquina(request.getRemoteHost());		                      
                  //creando una sesion mas para el usuario
                  sesionTO=this.getBDL().crearSesion(sesionTO);                  
                  loginAF.setSesion(sesionTO);
                  //tomando la unidad administradora a la que pertenece el usuario
                  loginAF.setUsuario(this.getBDL().getUnidadAdministradora(loginAF.getUsuario()));
                  
                  //Iniciando el nuevo actionForms
                 if(this.getBDL().debeCambiarClave(usuarioTO))
                    {	
                      loginAF.getUsuario().setClave("");
                      loginAF.setEntrada(0);
                      loginAF.setCambiarClave(true);
                      forward = mapping.findForward("cambiar");
                    } 
                 else
                    {
                	 
                      loginAF.getUsuario().setClave("");
                      loginAF.setEntrada(2);
                      loginAF.setCambiarClave(false);
                      //loginAF.setMensajes(false);
                      
                      forward = mapping.findForward("tareas");
                      int cantidadMensajes=0;
                      //int cantidadMensajes=this.getBDL().getCantidadMensajesPorEstado("",loginAF.getUsuario().getIdUsuario());
                      if (logger.isDebugEnabled())logger.info("cantidad de mensajes "+cantidadMensajes);
                      if  ( cantidadMensajes>0){
                    	  
                      	loginAF.setMensajes(true);
                      	MensajesAF mensajesAF =new MensajesAF();
                      	if( loginAF.getUsuario().getRol().equals(ConstanteBDWorkFlow.rolEnvio.rolUsuario)){
                        	mensajesAF.setRolEnvio(true);
                        }
                    
                      	int boton=mensajesAF.getBoton();           
                        
                        PaginaTO pagina=mensajesAF.getPagina();
                           
                           //hacer el proceso             
                        mensajesAF.setVacio(true);
            	        //busqueda por filtro primera vez o el boton inicio 
            	        if(boton==0 || boton==1)
                        {
            	        	pagina.setPagina(1);      
            	        }
            	             //anterior
            	        if(boton==2)
            	        {
            	        	pagina.setPagina(pagina.getPagina()-1);   
            	        }  
            	        //siguiente
            	        if(boton==3)
            	        {
            	           pagina.setPagina(pagina.getPagina()+1);   
            	        } 
            	        //Final
            	        if(boton==4)
            	        {
            	           pagina.setPagina(pagina.getUltimaPagina());   
            	        }
            	        
            	        logger.info("boton"+boton);
            	             
            	        
                        request.getSession(false).setAttribute("mensajesAF",mensajesAF);
                        
                        	
                        //pagina=this.getBDL().getMensajesPorUsuario(loginAF.getUsuario().getIdUsuario(),"1",pagina.getPagina(),"T");                 
             	     
                    
                        if(pagina.getElementosTotales()==0)
                        {
                        	pagina.setUltimaPagina(0);  
                        }
                 
                        HashMap mapaHash=(HashMap)request.getSession(false).getAttribute("mapaHash");
                        if (mapaHash==null){
                        	mapaHash=new HashMap();
                        }
                        request.getSession(false).setAttribute("mapaHash",mapaHash);
                        //request.getSession(false).setAttribute("mapaHash",mapaHash);
                        
                        mensajesAF.setPagina(pagina);     
                        
                        
                		//forward = mapping.findForward("mensajes");
                		if (logger.isDebugEnabled())logger.info("El usuario tiene mas de un mensaje");             			
                    
                    
                    }  
               }   	
              }
              
              BusquedasAF busquedasAF=(BusquedasAF)request.getSession().getAttribute("busquedasAF");
              if(busquedasAF==null)
                {
            	 
                  busquedasAF=new BusquedasAF();
                  request.getSession().setAttribute("busquedasAF",busquedasAF);
                  
                  //atualiza el mensaje en linea al usuario
                 
                  MensajeOnlineTO mensajeOnline=this.getBDL().getMensajeOnline();                  
                  
                  if(mensajeOnline==null)
                    {
                      mensajeOnline=new MensajeOnlineTO();
                      mensajeOnline.setEstado(0);
                      mensajeOnline.setPrioridad(1);
                      mensajeOnline.setMensaje("");
                    }
                  if(mensajeOnline.getIdMensaje()==0 ||  mensajeOnline.getMensaje().equals(""))
                  {                      
                	  
                	  mensajeOnline.setMensaje("  *   Bienvenidos al Sistema Integrado de Gesti�n de las Finanzas P�blicas SIGECOF *   ");
                  } 
                  
                /*  logger.info("Id Mensaje: "+mensajeOnline.getIdMensaje());
                  logger.info("Mensaje: "+mensajeOnline.getMensaje());
                  logger.info("Prioridad: "+mensajeOnline.getPrioridad());*/
                  
                  loginAF.setMensaje(mensajeOnline.getMensaje());
                  
                  loginAF.setPrioridad(mensajeOnline.getPrioridad());
                  
                }  
              
              
            } 
        catch (Exception e) 
           {
            //ocurrio un error general en la aplicacion
            logger.error("Ocurri� un error general en la aplicaci�n ...", e);
            errors.add("error.workflow.errorGeneral", new ActionMessage("error.workflow.errorGeneral"));
            
           }

        //ocurrio un error general en la aplicacion 
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            logger.error("!errors.isEmpty() ");
            //retornando a la pagina de login
            forward = mapping.findForward("login");
        }
        logger.info("forward " + forward);
        
        // Finish with
        return (forward);
    }
}
