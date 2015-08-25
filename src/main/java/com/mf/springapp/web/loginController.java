package com.mf.springapp.web;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mf.controlacceso.dao.UsuarioDAO;
import com.mf.controlacceso.facade.sistema.SistemaFacade;
import com.mf.controlacceso.imple.UsuarioDAOImple;
import com.mf.controlacceso.modelo.sistema.Usuario;


@Controller
public class loginController {

    protected final Log logger = LogFactory.getLog(getClass());
    private SistemaFacade sistemaFacade = new SistemaFacade();

    @RequestMapping(value="/login.htm")
    public ModelAndView handleRequest(String loginTxt, String password, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    	
    	
    	Map<String, Object> contenido = new HashMap<String, Object>();
    	String vista = null;    	
    	
    	try{
	    	    	
	    	String mensaje = null;

	    	Map objetoValidacion = new HashMap();
	    	objetoValidacion.put("login", loginTxt);
	    	objetoValidacion.put("password", password);
	    	
	        if (reglaValidacion(objetoValidacion)){
	
	            Usuario usuarioTemp = new Usuario();
	            usuarioTemp.setLogin(loginTxt);
	            usuarioTemp.setPassword(MD5(password));
	            usuarioTemp.setEstatus("A");
	        	
	            Usuario usuario = sistemaFacade.validarUsuario(usuarioTemp);
	        	if (usuario !=null){
	        		if (usuario.getEstatus().equals("A")){
	        			if (usuario.getPerfilUsuario().size() > 0){
	        				vista = "principal.jsp";
	        			}else{
	        				mensaje = "Usuario No Tiene un Rol Asociado, Comuníquese con el administrador";
			            	contenido.put("mensaje", mensaje);
			            	vista = "login.jsp";
	        			}	        			
	        		}else{
	        			mensaje = "Usuario Inactivo, Comuníquese con el administrador";
		            	contenido.put("mensaje", mensaje);
		            	vista = "login.jsp";
	        		}

	                //$this->data['contenido'] = 'login_view';
	                //$this->data['menu'] = '';
	                //$this->data['mensaje'] = '';
	                //$valida1 = $this->usuario_mod->validar_usuario($login, $pass);
	                
	                
	        		/*if ($valida1) {
	                    $valida2 = $this->menu_mod->opcionMenu($login);
	                    if (count($valida2) > 0) {
	                        $this->data['mensaje'] = "Acceso exitoso";
	                        $this->data['title'] = "Pagina Principal";
	                        $this->data['opciones_menu'] = $valida2;
	                        $this->data['contenido'] = 'principal_view';
	                        $this->data['mensaje'] = 'Bienvenido al sistema control de acceso';
	                        $usuario = $this->usuario_mod->usuario($login);
	                        $id_instalacion = $usuario['id_instalacion'];
	                        $_SESSION['id_instalacion'] = $id_instalacion;
	                        $_SESSION['login'] = $login;

	                        $this->data['alto_menu'] = '5px';
	                        $_SESSION['opciones_menu'] = $this->data['opciones_menu'];
	                        $this->data['salir'] = $this->configuracion_mod->logoff;
	                    } else {
	                        $this->data['mensaje'] = "Error No Tiene Procesos Asignados, consulte al administrador";                
	                        $this->data['contenido'] = 'login_view';                
	                    }
	                } else {
	                    $this->data['mensaje'] = "Datos Invalidos favor corregir, gracias";
	                    $this->data['contenido'] = 'login_view';            
	                }
	                $this->load->view('templates/plantilla', $this->data);
	            }*/	        	
	        		
	        	//$resultado = $this->control_acceso_mod->insertarCA($cedula, $observacion, $fotoTXT, $id_instalacion);
	                
	                //$empleado = $this->empleado_mod->empleado($cedula);

	    	    	
	    	    	
	                
	            } else {
	            	mensaje = "Usuario o contraseña invalida, Favor Revisar";
	            	contenido.put("mensaje", mensaje);
	            	vista = "login.jsp";
	            	System.out.println("3994");
	            }	        
	        }
        
    	}catch (Exception e){
    		logger.error("Exception: ", e);
    		String mensaje = "Problema de Aplicación favor notificar al administrador, gracias";
        	contenido.put("mensaje", mensaje);
        	vista = "resultado.jsp";
    	
    	}finally{
    		Locale l = new Locale("es","VE");
	    	Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Caracas"),l);
	    	String hora = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
	    	String fechaHora = (cal.get(Calendar.MONTH)+1) + " " + cal.get(Calendar.DATE) + " " + cal.get(Calendar.YEAR) + " " + hora;
	    	
	    	String js[] = {"jquery-2.1.4.min","jquery_menu", "menu_view", "comun"};        
	        String css[] = {"bootstrap", "global_admin" ,"styleIE", "controlAcceso", "theme"};
	        
	        contenido.put("tituloPagina", "Control de Entrada y Salida del Personal");
	        contenido.put("js", js);
	        contenido.put("css", css);
	        contenido.put("vista", vista);	        
	        contenido.put("fechaHora", fechaHora);	        
    	}
    	
        return new ModelAndView("plantilla", "contenido", contenido);
    }
    
    
    private boolean reglaValidacion(Map datos){
    	
    	if ((String)datos.get("login") == null ||  ((String)datos.get("login")).length() < 1 ) {
    		throw new RuntimeException("login vacio");
    	}
    	if ((String)datos.get("password") == null || ((String)datos.get("password")).length() < 1 ) {
    		throw new RuntimeException("Password vacio");
    	}
    	return true;
    }
    

    public String MD5(String md5) {
    	try {
    		java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
    		byte[] array = md.digest(md5.getBytes());
    		StringBuilder sb = new StringBuilder();
    		for (int i = 0; i < array.length; ++i) {
    			sb.append(
    					Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
    		}
    		return sb.toString();
    	} catch (java.security.NoSuchAlgorithmException e) {
    	}
    	return null;
    }    
    
    
}