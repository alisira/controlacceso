package ve.gob.mf.sigecof.workflow.interfaznegociadora.actionForms;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import ve.gob.mf.sigecof.workflow.negocio.dao.ConstanteBDWorkFlow;
import ve.gob.mf.sigecof.workflow.negocio.to.GrupoTrabajoTO;
import ve.gob.mf.sigecof.workflow.negocio.to.OrganismoWFTO;
import ve.gob.mf.sigecof.workflow.negocio.to.SesionTO;
import ve.gob.mf.sigecof.workflow.negocio.to.UsuarioWFTO;

/**
 * Manejar la infomacion de la forma de ingreso de login y la mantiene ne la session
 * @version 	1.0
 * @author Robinson Garces 02/04/2006
 */
public class LoginAF extends ActionForm

{
    //variable escribir las trazas 
    private static Logger logger = Logger.getLogger(LoginAF.class);
       
    //para mantener los datos del usuario en la session
    private UsuarioWFTO usuario=new UsuarioWFTO();  
    
    //contiene la infomacion de la session actual es decir IP y conexion Etc
    private SesionTO  sesion=null;
    
    //indica si ya llego al maximo de conexiones permitidad para este usuario
    private boolean maximo=false;
    
    //indica si la clave ya fue verificada por esta conexion 
    private boolean claveVerificada=false;
    
    //mensaje en linea que se muestra en la aplicacion 
    private String mensaje="      Bienvenidos al Sistema Integrado de Gesti�n     ";
    private int prioridad=0;
    
    private String textoImagen="";
    //indica si de va a iniciar con cambio de clave o con tareas la siguiente pantalla
    private int entrada=2; 
    
    private GrupoTrabajoTO grupoTrabajoTO=new GrupoTrabajoTO();
    
    
   private boolean cambiarClave=false;
   
   private boolean mensajes=false;
   
   
   
    
    
    private  String abierta=ConstanteBDWorkFlow.estadoWorkItem.ABIERTA;
    private  String cerrada=ConstanteBDWorkFlow.estadoWorkItem.CERRADA;
    private  String pendiente=ConstanteBDWorkFlow.estadoWorkItem.PENDIENTE;    
    
    private  String abierto=ConstanteBDWorkFlow.estadoExpediente.ABIERTO;
    private  String cerrado=ConstanteBDWorkFlow.estadoExpediente.CERRADO;   
    
    
    /**  
     * para inicializar los objetos obligatorios para el ingreso de la data   
     **/
    public LoginAF()
    {
        super();
        logger.debug("la clase login a sido creada satisfactoriamente");
        //inicializa el objeto organismo del usuario
        usuario.setClave("");
        usuario.setIdUsuario("");
        OrganismoWFTO organismoWFTO=new OrganismoWFTO();
        usuario.setOrganismo(organismoWFTO);        
        
    }
    
    
    
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        logger.debug("entrando al reset:");
        usuario.setClave("");
        usuario.setIdUsuario("");
        OrganismoWFTO organismoWFTO=new OrganismoWFTO();
        organismoWFTO.setIdOrganismo("");
        organismoWFTO.setDenominacion("");
        usuario.setOrganismo(organismoWFTO);    
        maximo=false;
        claveVerificada=false;
    }
    
    
    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
        
        logger.debug("entrando al validate");
        ActionErrors errors = new ActionErrors();        
        
        if(usuario.getClave().equals("") && usuario.getIdUsuario().equals(""))
        {   
           logger.error("no ingreso el identificador y la clave");
           errors.add("error.workflow.ingresoUsuarioClave",new ActionMessage("error.workflow.ingresoUsuarioClave"));
        }       
        
        if(usuario.getClave().equals("") && !usuario.getIdUsuario().equals(""))
         {   
            logger.error("no ingreso la clave");
            errors.add("error.workflow.ingresoClave",new ActionMessage("error.workflow.ingresoClave"));
         }
        
        
        if(usuario.getIdUsuario().equals("") && !usuario.getClave().equals(""))
         {
            logger.error("no ingreso el idUsuario");
            errors.add("error.workflow.ingresoUsuario",new ActionMessage("error.workflow.ingresoUsuario"));
         }
        
        
        if(usuario.getIdUsuario().equals("") && !usuario.getClave().equals(""))
        {
           logger.error("no ingreso el idUsuario");
           errors.add("error.workflow.ingresoUsuario",new ActionMessage("error.workflow.ingresoUsuario"));
        }
       
    /*    if(usuario.getOrganismo().getIdOrganismo().equals(""))          
          {
              logger.error("no ingreso el organismo correcto");
              errors.add("error.workflow.ingresoOrganismo",new ActionMessage("error.workflow.ingresoOrganismo"));
          } */
       
        return errors;
    }
    
    
    
    
    
    
    public boolean isMensajes() {
    	
    	return mensajes;
    }
    
    public void setMensajes(boolean mensajes) {
    	
    	this.mensajes = mensajes;
    }
    
    
	public String getTextoImagen() {
		
		return textoImagen;
	}
	public void setTextoImagen(String textoImagen) {
		
		this.textoImagen = textoImagen;
	}
    /**
     * @return Retorna el atributo entrada.
     */

    public int getEntrada()
    {
        return entrada;
    }
    /**
     * @param El argumento entrada  es asignado al campo entrada.
     */

    public void setEntrada(int entrada)
    {
        this.entrada = entrada;
    }
  
    /**
     * @return Retorna el atributo usuario.
     */

    public UsuarioWFTO getUsuario()
    {
        return usuario;
    }
    
    /**
     * @param El argumento usuario  es asignado al campo usuario.
     */

    public void setUsuario(UsuarioWFTO usuario)
    {
        this.usuario = usuario;
    }
    /**
     * @return Retorna el atributo sesion.
     */

    public SesionTO getSesion()
    {
        return sesion;
    }
    /**
     * @param El argumento sesion  es asignado al campo sesion.
     */

    public void setSesion(SesionTO sesion)
    {
        this.sesion = sesion;
    }
   
    /**
     * @return Retorna el atributo maximo.
     */

    public boolean isMaximo()
    {
        return maximo;
    }
    /**
     * @param El argumento maximo  es asignado al campo maximo.
     */

    public void setMaximo(boolean maximo)
    {
        this.maximo = maximo;
    }
    /**
     * @return Retorna el atributo claveVerificada.
     */

    public boolean isClaveVerificada()
    {
        return claveVerificada;
    }
    /**
     * @param El argumento claveVerificada  es asignado al campo claveVerificada.
     */

    public void setClaveVerificada(boolean claveVerificada)
    {
        this.claveVerificada = claveVerificada;
    }
    /**
     * @return Retorna el atributo mensaje.
     */

    public String getMensaje()
    {
        return mensaje;
    }
    /**
     * @param El argumento mensaje  es asignado al campo mensaje.
     */

    public void setMensaje(String mensaje)
    {
        this.mensaje = mensaje;
    }
    /**
     * @return Retorna el atributo abierta.
     */

    public String getAbierta()
    {
        return abierta;
    }
    /**
     * @param El argumento abierta  es asignado al campo abierta.
     */

    public void setAbierta(String abierta)
    {
        this.abierta = abierta;
    }
    /**
     * @return Retorna el atributo abierto.
     */

    public String getAbierto()
    {
        return abierto;
    }
    /**
     * @param El argumento abierto  es asignado al campo abierto.
     */

    public void setAbierto(String abierto)
    {
        this.abierto = abierto;
    }
    /**
     * @return Retorna el atributo cerrada.
     */

    public String getCerrada()
    {
        return cerrada;
    }
    /**
     * @param El argumento cerrada  es asignado al campo cerrada.
     */

    public void setCerrada(String cerrada)
    {
        this.cerrada = cerrada;
    }
    /**
     * @return Retorna el atributo cerrado.
     */

    public String getCerrado()
    {
        return cerrado;
    }
    /**
     * @param El argumento cerrado  es asignado al campo cerrado.
     */

    public void setCerrado(String cerrado)
    {
        this.cerrado = cerrado;
    }
    /**
     * @return Retorna el atributo pendiente.
     */

    public String getPendiente()
    {
        return pendiente;
    }
    /**
     * @param El argumento pendiente  es asignado al campo pendiente.
     */

    public void setPendiente(String pendiente)
    {
        this.pendiente = pendiente;
    }
/**
 * @return Retorna el atributo cambiarClave.
 */

public boolean isCambiarClave()
{
    return cambiarClave;
}
/**
 * @param El argumento cambiarClave  es asignado al campo cambiarClave.
 */

public void setCambiarClave(boolean cambiarClave)
{
    this.cambiarClave = cambiarClave;
}
    /**
     * @return Retorna el atributo prioridad.
     */

    public int getPrioridad()
    {
        return prioridad;
    }
    /**
     * @param El argumento prioridad  es asignado al campo prioridad.
     */

    public void setPrioridad(int prioridad)
    {
        this.prioridad = prioridad;
    }
    /**
     * @return Retorna el atributo grupoTrabajoTO.
     */

    public GrupoTrabajoTO getGrupoTrabajoTO()
    {
        return grupoTrabajoTO;
    }
    /**
     * @param El argumento grupoTrabajoTO  es asignado al campo grupoTrabajoTO.
     */

    public void setGrupoTrabajoTO(GrupoTrabajoTO grupoTrabajoTO)
    {
        this.grupoTrabajoTO = grupoTrabajoTO;
    }
}
