package com.mf.controlacceso.facade.sistema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mf.controlacceso.dao.ProcesoDAO;
import com.mf.controlacceso.dao.UsuarioDAO;
import com.mf.controlacceso.imple.ProcesoDAOImple;
import com.mf.controlacceso.imple.UsuarioDAOImple;
import com.mf.controlacceso.modelo.sistema.Perfil;
import com.mf.controlacceso.modelo.sistema.PerfilProceso;
import com.mf.controlacceso.modelo.sistema.Proceso;
import com.mf.controlacceso.modelo.sistema.Usuario;
import com.mf.controlacceso.sistema.LoginSession;
import com.mf.controlacceso.sistema.Rol;
import com.mf.controlacceso.sistema.UsuarioRol;
import com.mf.controlacceso.dominio.ProcesoDTO;
import com.mf.controlacceso.dominio.UsuarioDTO;
/*import sigefirrhh.login.LoginSession;
import sigefirrhh.persistencia.dao.OpcionDAO;
import sigefirrhh.persistencia.dao.imple.OpcionDAOImple;
import sigefirrhh.persistencia.modelo.CriterioBusqueda;
import sigefirrhh.persistencia.modelo.Opcion;
import sigefirrhh.sistema.UsuarioRol;
*/

/**
 * 
 * Provee un acceso comun a los paquetes de la capa de negocios. Funcionan como intermediarios entre los distintos paquetes
 *
 */
public class SistemaFacade implements Serializable {	
	
	//private SistemaBusiness sistemaBusiness = new SistemaBusiness();
	private UsuarioDAO userDAO = null;
	
	public SistemaFacade() {
		super();
		userDAO = new UsuarioDAOImple();
	}
	
	public void addUsuario(UsuarioDTO usuario)
			throws Exception {
		try {

			//sistemaBusiness.addUsuario(usuario);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	
	public void guardarUsuario(UsuarioDTO usuario)
			throws Exception {
		try {
			//if(usuario.getActivo().equals("S") && usuario.getIntentos() > 0) usuario.setIntentos(0);		

			userDAO.saveOrUpdate(usuario);
			//sistemaBusiness.updateUsuario(usuario);
		} finally {
			//if (txn!=null) txn.close();
		}
	}

	public void borrarUsuario(UsuarioDTO usuario)
			throws Exception {
		try {

			//sistemaBusiness.deleteUsuario(usuario);
		} finally {
			//if (txn!=null) txn.close();
		}
	}

	public Usuario buscarUsuario(UsuarioDTO usuario)
			throws Exception {
		try {

			List<Object> ListaUsuario = userDAO.listar(usuario);
			Usuario usuarioTemp = (Usuario) ListaUsuario.get(0); 
			
			/* Usuario user = new Usuario();
	    		UsuarioDAO usuarioDAO = new UsuarioDAOImple();	    		
				List<Usuario> listadoUsuario= (List) usuarioDAO.listar(user);				
				System.out.println(listadoUsuario.size());*/
			 
			System.out.println("Nombre: " + usuarioTemp.getNombre());
			System.out.println("Size: " + usuarioTemp.getPerfilUsuario().size());
			
			
			
			

			return usuarioTemp;
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	
	public UsuarioDTO validarUsuario(UsuarioDTO usuarioDTO){
		//	try {
		
		
		Usuario usuario = new Usuario();
		usuario = (Usuario) ModeloUtil.llenarBean(usuarioDTO, usuario);
		
		usuario.setEstatus("A");
		usuario.setLogin("asira");
		List<Object> ListaUsuario = userDAO.listar(usuario);
		//Usuario usuarioTemp2 = (Usuario) ListaUsuario.get(0); 
		//System.out.println("PU: " + usuarioTemp2.getPerfilUsuario().size());
		
		
		if (ListaUsuario.size() > 0){

			Usuario usuarioTemp = (Usuario) ListaUsuario.get(0); 

			UsuarioDTO usuarioDTOTemp = usuarioDTO; 
			//System.out.println("Perfil Usuario: " + usuarioDTO.getPerfilUsuario().size());
			usuarioDTOTemp =  (UsuarioDTO) ModeloUtil.llenarBean(usuarioTemp, usuarioDTOTemp);


			return usuarioDTOTemp;
		}else{
			return null;
		}

		//} finally {
			//if (txn!=null) txn.close();
		//}
	}
	
	public String[][] generarMenu(UsuarioDTO usuario) {

		String vtOpciones[][] = null;

		//System.out.println("Entro en generarMenu");			

		for (int i=0;i< usuario.getPerfilUsuario().size();i++){

			//System.out.println("cantidad de perfilusuario = " + usuario.getPerfilUsuario().size());

			vtOpciones = new String[usuario.getPerfilUsuario().get(i).getPerfil().getPerfilProceso().size()][3]; 

			Proceso proceso = new Proceso();
			Perfil perfil = new Perfil();
			perfil = (Perfil) ModeloUtil.llenarBean(usuario.getPerfilUsuario().get(i).getPerfil(), perfil);
			proceso.setPerfilProceso(perfil.getPerfilProceso());
			ProcesoDAOImple procesoDAO = new ProcesoDAOImple();
			List<Proceso> listaProceso = procesoDAO.procesoPerfil(proceso);
			

			for (int y=0;y< listaProceso.size();y++){					

				//System.out.println("cantidad de perfilproceso = " + usuario.getPerfilUsuario().get(i).getPerfil().getPerfilProceso().size());

				vtOpciones[y][0] = listaProceso.get(y).getJerarquia_menu().toString();
				vtOpciones[y][1] = listaProceso.get(y).getDenominacion().toString();
				vtOpciones[y][2] = listaProceso.get(y).getUrl().toString();

				//System.out.println("este si: " + vtOpciones[y][1]);

			}
		}
			
			
		/*for (int z=0;z< usuario.getPerfilUsuario().get(0).getPerfil().getPerfilProceso().size();z++){
			
			System.out.println(vtOpciones[z][0] + "-" + vtOpciones[z][1] + "-" + vtOpciones[z][2]);

			//System.out.println("este si: " + vtOpciones[y][1]);

		}*/
			
			//imprimir contenido of the array
			
			
			
			
			
			
			//System.out.println("total Proceso1=" + ListaProceso1.size());
			
			
			//Proceso proTo2 =  (Proceso) ListaProceso2.get(0);
			
			//System.out.println("total perfilProceso=" + ListaProceso2.get(0).getPerfilProceso().size());
			
			return vtOpciones;
			/*OpcionDAO OpcionDAO = new OpcionDAOImple();
			List<Opcion> listadoOpcion = null;
			
			try {
				
				if (usuario.getAdministrador()) {
				
					Collection colUsuarioRol = new ArrayList();
					colUsuarioRol = (ArrayList)loginSession.getColUsuarioRol();	
					
					criterio = new CriterioBusqueda();
					Iterator iterator = colUsuarioRol.iterator();
					
					while (iterator.hasNext()){
						
						UsuarioRol usuarioRol = (UsuarioRol)iterator.next();
						criterio.addIdRol(usuarioRol.getRol().getIdRol());						
					}
					listadoOpcion = (List<Opcion>) OpcionDAO.buscar(criterio, "OpcionxRol");	
				}else{
					listadoOpcion = (List<Opcion>) OpcionDAO.buscar(null, "Opcion");
				}			
				
				String vtOpciones[][] = new String[listadoOpcion.size()][3]; 
				for (int i=0; i < listadoOpcion.size();i++){
					vtOpciones[i][0] = listadoOpcion.get(i).getJerarquia().toString();
					vtOpciones[i][1] = listadoOpcion.get(i).getDescripcion().toString();
					vtOpciones[i][2] = listadoOpcion.get(i).getRuta();
				}
				
				
				//System.out.println(vtOpciones[1][0] + " " + vtOpciones[1][1]);
				
				//Ponerle el bloque que esta en la oficina 
				
				
				menu = "<ul id=\"xx\" class=\"xx\">";
				
				for (int i=0; i<vtOpciones.length; i++) {
					if (tieneHijos(i, vtOpciones)) {				
						menu += "<li>" + vtOpciones[i][1] +  "<ul>";
					} else {
						
						String rutaTemp = null;
						rutaTemp = "/" + request.getRequestURI().split("/")[1] ;
						
						if (vtOpciones[i][2].indexOf(".do") != -1){
							menu += "<li> <a href=\"" + rutaTemp+ "/" + vtOpciones[i][2] + "?accion=nuevo\" class=\"level-1\">";	
						}else{
							if (!vtOpciones[i][2].equals("")){
								menu += "<li> <a href=\"" + rutaTemp+ "/" + vtOpciones[i][2] + ".jsf\" class=\"level-1\">";	
							}else{
								menu += "<li> <a href=\"" + rutaTemp+ "/" + "home.jsf\" class=\"level-1\">";
							}
						}					
						menu += vtOpciones[i][1] + "</a></li>";

					}
					
					if (i+1 < vtOpciones.length){
				    	 if (vtOpciones[i+1][0].length() < vtOpciones[i][0].length()){ 
				    		 
				    		 int cont = vtOpciones[i][0].split("\\.").length - vtOpciones[i+1][0].split("\\.").length ;
				    		 for (int y=0; y<cont; y++) {
				    			 menu +="</ul></li>";	 
				    		 }
				    	 }
					}else if (i+1 == vtOpciones.length){
				    	 if (vtOpciones[i][0].split("\\.").length > 1){
		 		    		 
				    		 int cont = vtOpciones[i][0].split("\\.").length-1 ;
				    		 for (int y=0; y<cont; y++) {
				    			 menu +="</ul></li>";	 
				    		 }
				    	 }
				    	
				    	 
				    	 
				     }
			    	// System.out.println(m.toString()+ " " + resp);			
					*/
		        }
	
	
		/*public Collection listarUsuario()
			throws Exception {
			try {
				
				return //sistemaBusiness.findAllUsuario();
			} finally {
				//if (txn!=null) txn.close();
			}
		}
		

		public Collection findUsuarioByUsuario(java.lang.String usuario) 
			throws Exception {
			try {
				
				return //sistemaBusiness.findUsuarioByUsuario(usuario);
			} finally {
				//if (txn!=null) txn.close();
			}
		}
		

		public Collection findUsuarioByApellidos(java.lang.String apellidos) 
			throws Exception {
			try {
				
				return //sistemaBusiness.findUsuarioByApellidos(apellidos);
			} finally {
				//if (txn!=null) txn.close();
			}
		}	
	

	
	/*public void addRol(Rol rol)
		throws Exception {
		try {
			
			//sistemaBusiness.addRol(rol);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public void updateRol(Rol rol)
		throws Exception {
		try {
			
			//sistemaBusiness.updateRol(rol);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public void deleteRol(Rol rol)
		throws Exception {
		try {
			
			//sistemaBusiness.deleteRol(rol);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public Rol findRolById(long rolId)
		throws Exception {
		try {
			
			Rol rol = 
				//sistemaBusiness.findRolById(rolId);
			PersistenceManager pm = PMThread.getPM();
			pm.makeTransient(rol);
			return rol;
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public Collection findAllRol()
		throws Exception {
		try {
			
			return //sistemaBusiness.findAllRol();
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	

	public Collection findRolByCodigoRol(java.lang.String codigoRol) 
		throws Exception {
		try {
			
			return //sistemaBusiness.findRolByCodigoRol(codigoRol);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	

	public Collection findRolByNombre(java.lang.String nombre) 
		throws Exception {
		try {
			
			return //sistemaBusiness.findRolByNombre(nombre);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	


	
	public void addRolOpcion(RolOpcion rolOpcion)
		throws Exception {
		try {
			
			//sistemaBusiness.addRolOpcion(rolOpcion);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public void updateRolOpcion(RolOpcion rolOpcion)
		throws Exception {
		try {
			
			//sistemaBusiness.updateRolOpcion(rolOpcion);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public void deleteRolOpcion(RolOpcion rolOpcion)
		throws Exception {
		try {
			
			//sistemaBusiness.deleteRolOpcion(rolOpcion);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public RolOpcion findRolOpcionById(long rolOpcionId)
		throws Exception {
		try {
			
			RolOpcion rolOpcion = 
				//sistemaBusiness.findRolOpcionById(rolOpcionId);
			PersistenceManager pm = PMThread.getPM();
			pm.makeTransient(rolOpcion);
			return rolOpcion;
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public Collection findAllRolOpcion()
		throws Exception {
		try {
			
			return //sistemaBusiness.findAllRolOpcion();
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	

	public Collection findRolOpcionByRol(long idRol) 
		throws Exception {
		try {
			
			return //sistemaBusiness.findRolOpcionByRol(idRol);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	


	
	public void addUsuario(Usuario usuario)
		throws Exception {
		try {
			
			//sistemaBusiness.addUsuario(usuario);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public void updateUsuario(Usuario usuario)
		throws Exception {
		try {
			
			/*
			if(usuario.getActivo().equals("S") && usuario.getIntentos() > 0)
				usuario.setIntentos(0);		
				
			//sistemaBusiness.updateUsuario(usuario);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public void deleteUsuario(Usuario usuario)
		throws Exception {
		try {
			
			//sistemaBusiness.deleteUsuario(usuario);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public Usuario findUsuarioById(long usuarioId)
		throws Exception {
		try {
			
			Usuario usuario = 
				//sistemaBusiness.findUsuarioById(usuarioId);
			PersistenceManager pm = PMThread.getPM();
			pm.makeTransient(usuario);
			return usuario;
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public Collection findAllUsuario()
		throws Exception {
		try {
			
			return //sistemaBusiness.findAllUsuario();
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	

	public Collection findUsuarioByUsuario(java.lang.String usuario) 
		throws Exception {
		try {
			
			return //sistemaBusiness.findUsuarioByUsuario(usuario);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	

	public Collection findUsuarioByApellidos(java.lang.String apellidos) 
		throws Exception {
		try {
			
			return //sistemaBusiness.findUsuarioByApellidos(apellidos);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	


	
	public void addUsuarioOrganismo(UsuarioOrganismo usuarioOrganismo)
		throws Exception {
		try {
			
			//sistemaBusiness.addUsuarioOrganismo(usuarioOrganismo);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public void updateUsuarioOrganismo(UsuarioOrganismo usuarioOrganismo)
		throws Exception {
		try {
			
			//sistemaBusiness.updateUsuarioOrganismo(usuarioOrganismo);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public void deleteUsuarioOrganismo(UsuarioOrganismo usuarioOrganismo)
		throws Exception {
		try {
			
			//sistemaBusiness.deleteUsuarioOrganismo(usuarioOrganismo);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public UsuarioOrganismo findUsuarioOrganismoById(long usuarioOrganismoId)
		throws Exception {
		try {
			
			UsuarioOrganismo usuarioOrganismo = 
				//sistemaBusiness.findUsuarioOrganismoById(usuarioOrganismoId);
			PersistenceManager pm = PMThread.getPM();
			pm.makeTransient(usuarioOrganismo);
			return usuarioOrganismo;
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public Collection findAllUsuarioOrganismo()
		throws Exception {
		try {
			
			return //sistemaBusiness.findAllUsuarioOrganismo();
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	

	public Collection findUsuarioOrganismoByUsuario(long idUsuario, long idOrganismo) 
		throws Exception {
		try {
			
			return //sistemaBusiness.findUsuarioOrganismoByUsuario(idUsuario, idOrganismo);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	

	public Collection findUsuarioOrganismoByOrganismo(long idOrganismo) 
		throws Exception {
		try {
			
			return //sistemaBusiness.findUsuarioOrganismoByOrganismo(idOrganismo);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	


	
	public void addUsuarioRol(UsuarioRol usuarioRol)
		throws Exception {
		try {
			
			//sistemaBusiness.addUsuarioRol(usuarioRol);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public void updateUsuarioRol(UsuarioRol usuarioRol)
		throws Exception {
		try {
			
			//sistemaBusiness.updateUsuarioRol(usuarioRol);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public void deleteUsuarioRol(UsuarioRol usuarioRol)
		throws Exception {
		try {
			
			//sistemaBusiness.deleteUsuarioRol(usuarioRol);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public UsuarioRol findUsuarioRolById(long usuarioRolId)
		throws Exception {
		try {
			
			UsuarioRol usuarioRol = 
				//sistemaBusiness.findUsuarioRolById(usuarioRolId);
			PersistenceManager pm = PMThread.getPM();
			pm.makeTransient(usuarioRol);
			return usuarioRol;
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public Collection findAllUsuarioRol()
		throws Exception {
		try {
			
			return //sistemaBusiness.findAllUsuarioRol();
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	

	public Collection findUsuarioRolByUsuario(long idUsuario) 
		throws Exception {
		try {
			
			return //sistemaBusiness.findUsuarioRolByUsuario(idUsuario);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	

	public Collection findUsuarioRolByRol(long idRol) 
		throws Exception {
		try {
			
			return //sistemaBusiness.findUsuarioRolByRol(idRol);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	


	
	public void addUsuarioTipoPersonal(UsuarioTipoPersonal usuarioTipoPersonal)
		throws Exception {
		try {
			
			//sistemaBusiness.addUsuarioTipoPersonal(usuarioTipoPersonal);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public void updateUsuarioTipoPersonal(UsuarioTipoPersonal usuarioTipoPersonal)
		throws Exception {
		try {
			
			//sistemaBusiness.updateUsuarioTipoPersonal(usuarioTipoPersonal);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public void deleteUsuarioTipoPersonal(UsuarioTipoPersonal usuarioTipoPersonal)
		throws Exception {
		try {
			
			//sistemaBusiness.deleteUsuarioTipoPersonal(usuarioTipoPersonal);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public UsuarioTipoPersonal findUsuarioTipoPersonalById(long usuarioTipoPersonalId)
		throws Exception {
		try {
			
			UsuarioTipoPersonal usuarioTipoPersonal = 
				//sistemaBusiness.findUsuarioTipoPersonalById(usuarioTipoPersonalId);
			PersistenceManager pm = PMThread.getPM();
			pm.makeTransient(usuarioTipoPersonal);
			return usuarioTipoPersonal;
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public Collection findAllUsuarioTipoPersonal()
		throws Exception {
		try {
			
			return //sistemaBusiness.findAllUsuarioTipoPersonal();
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	

	public Collection findUsuarioTipoPersonalByUsuario(long idUsuario) 
		throws Exception {
		try {
			
			return //sistemaBusiness.findUsuarioTipoPersonalByUsuario(idUsuario);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	

	public Collection findUsuarioTipoPersonalByTipoPersonal(long idTipoPersonal) 
		throws Exception {
		try {
			
			return //sistemaBusiness.findUsuarioTipoPersonalByTipoPersonal(idTipoPersonal);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	


//	NO SE GENERA
	public Collection findUsuarioByPassword(
		java.lang.String usuario, java.lang.String password) 
		throws Exception {
		try {
			
			return //sistemaBusiness.findUsuarioByPassword(
				usuario, password);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public Collection findRolOpcionByRolAndOpcion(
		  long idRol, String ruta) 
		  throws Exception {
		  try {
			  
			  return //sistemaBusiness.findRolOpcionByRolAndOpcion(
				  idRol, ruta);
		  } finally {
			  //if (txn!=null) txn.close();
		  }
	  }
	

	public void addUsuarioUnidadFuncional(UsuarioUnidadFuncional usuarioUnidadFuncional)
		throws Exception {
		try {
			
			//sistemaBusiness.addUsuarioUnidadFuncional(usuarioUnidadFuncional);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public void updateUsuarioUnidadFuncional(UsuarioUnidadFuncional usuarioUnidadFuncional)
		throws Exception {
		try {
			
			//sistemaBusiness.updateUsuarioUnidadFuncional(usuarioUnidadFuncional);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public void deleteUsuarioUnidadFuncional(UsuarioUnidadFuncional usuarioUnidadFuncional)
		throws Exception {
		try {
			
			//sistemaBusiness.deleteUsuarioUnidadFuncional(usuarioUnidadFuncional);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public UsuarioUnidadFuncional findUsuarioUnidadFuncionalById(long usuarioUnidadFuncionalId)
		throws Exception {
		try {
			
			UsuarioUnidadFuncional usuarioUnidadFuncional = 
				//sistemaBusiness.findUsuarioUnidadFuncionalById(usuarioUnidadFuncionalId);
			PersistenceManager pm = PMThread.getPM();
			pm.makeTransient(usuarioUnidadFuncional);
			return usuarioUnidadFuncional;
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	public Collection findAllUsuarioUnidadFuncional()
		throws Exception {
		try {
			
			return //sistemaBusiness.findAllUsuarioUnidadFuncional();
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	
	
	public Collection findUsuarioUnidadFuncionalByUsuario(long idUsuario) 
		throws Exception {
		try {
			
			return //sistemaBusiness.findUsuarioUnidadFuncionalByUsuario(idUsuario);
		} finally {
			//if (txn!=null) txn.close();
		}
	}
	
	
	public Collection findUsuarioUnidadFuncionalByUnidadFuncional(long idUnidadFuncional) 
		throws Exception {
		try {
			
			return //sistemaBusiness.findUsuarioUnidadFuncionalByUnidadFuncional(idUnidadFuncional);
		} finally {
			//if (txn!=null) txn.close();
		}
	}

	//RJB Metodos de parametroSistema:
	
	/**
	 * Busca un par�metro por nombre
	 * @param nombreParametro nombre del par�metro que se busca
	 * @return objeto ParametroSistema con la informaci�n requerida o null
	 * @throws Exception
	 
	public ParametroSistema findParametroSistemaByName(String nombreParametro)
			throws Exception{
		try {
						
			return //sistemaBusiness.findParametroSistemaByName(nombreParametro);
		} finally {
			//if (txn!=null) txn.close();
		}
			
	}

	/**
	 * Devuelve la cantidad de intentos de login fallidos permitidos antes de desactivar el usuario 
	 * @return cantidad de intentos fallidos permitidos
	 * @throws Exception
	 
	public int getParametroMaxIntentos()
			throws Exception{
		try {
						
			return //sistemaBusiness.getParametroMaxIntentos();
		} finally {
			//if (txn!=null) txn.close();
		}			
	}

	public int getParametroDiasVigenciaUsuario()
			throws Exception{
		try {
						
			return //sistemaBusiness.getParametroDiasVigenciaUsuario();
		} finally {
			//if (txn!=null) txn.close();
		}			
	}
	
	public int getParametroMinLongitudClave()
		throws Exception{
		try {
						
			return //sistemaBusiness.getParametroMinLongitudClave();		
		} finally {
			//if (txn!=null) txn.close();
		}			
	}
	
	/**
	 * Devuelve la cantidad de intentos de login fallidos permitidos antes de desactivar el usuario 
	 * @return cantidad de intentos fallidos permitidos
	 * @throws Exception
	 
	public int getParametroDiasDuracionClave()
			throws Exception{
		try {
						
			return //sistemaBusiness.getParametroDiasDuracionClave();
		} finally {
			//if (txn!=null) txn.close();
		}			
	}
	*/
	public void setSession(UsuarioDTO usuario){
		/*Organismo org = new Organismo();
		org.setIdOrganismo(11);
		
		Usuario user = new Usuario();
		user.setNombres("Ali");
		user.setApellidos("Sira");
			

		LoginSession a= new LoginSession();
		
		a.setUsuarioObject(user);	
		a.setUsuario("asira");
		a.setOrganismo(org);
		
		List listaUsuarioRol = new ArrayList();	
		
		UsuarioRol usuarioRol = new UsuarioRol();
		
		Rol rol = new Rol();
		rol.setCodigoRol("3");
		rol.setIdRol(41);
		rol.setNombre("ANALISTA COMPROMISO");

		usuarioRol.setRol(rol);
		usuarioRol.setIdUsuarioRol(31);
		
		//usuarioRol.setUsuario(usuario)
		listaUsuarioRol.add(usuarioRol);

		
		a.setColUsuarioRol(listaUsuarioRol);
		a.setAdministrador("N");
		//System.out.println(listaUsuarioRol.size());	

		session.setAttribute("loginSession",a);	*/
	}
	
}
