
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.regex.Matcher"%>
<%@ page import="java.util.regex.Pattern"%>

<%
	Map conteMenu = (HashMap)request.getAttribute("contenido");

	if (conteMenu.get("menu") != null) {
		//System.out.println("es nulo");
		String rutaTemp = null;
		String vtOpciones[][] = (String[][]) conteMenu.get("menu");

		String menu = "<ul id=\"xx\" class=\"xx\">";

		for (int i = 0; i < vtOpciones.length; i++) {

			int indiceActual = i;
			String vtOpc[][] = vtOpciones;
			Pattern p = Pattern.compile("^" + vtOpc[indiceActual][0]
					+ ".*");
			boolean resp = false;

			if (indiceActual + 1 < vtOpc.length) {
				Matcher m = p.matcher(vtOpc[indiceActual + 1][0]); // get a matcher object
				if (m.find()) {
					resp = true;
				} else {
					resp = false;
				}
			}

			if (resp == true) {
				menu += "<li>" + vtOpciones[i][1] + "<ul>";
			} else {

				
				rutaTemp = "/" + request.getRequestURI().split("/")[1];

				if (vtOpciones[i][2].indexOf(".do") != -1) {
					menu += "<li> <a href=\"" + rutaTemp + "/"
							+ vtOpciones[i][2]
							+ "?accion=nuevo\" class=\"level-1\">";
				} else {
					if (!vtOpciones[i][2].equals("")) {
						menu += "<li> <a href=\"" + rutaTemp + "/"
								+ vtOpciones[i][2]
								+ ".jsf\" class=\"level-1\">";
					} else {
						menu += "<li> <a href=\"" + rutaTemp + "/"
								+ "home.jsf\" class=\"level-1\">";
					}
				}
				menu += vtOpciones[i][1] + "</a></li>";

			}

			if (i + 1 < vtOpciones.length) {
				if (vtOpciones[i + 1][0].length() < vtOpciones[i][0]
						.length()) {

					int cont = vtOpciones[i][0].split("\\.").length
							- vtOpciones[i + 1][0].split("\\.").length;
					for (int y = 0; y < cont; y++) {
						menu += "</ul></li>";
					}
				}
			} else if (i + 1 == vtOpciones.length) {
				if (vtOpciones[i][0].split("\\.").length > 1) {

					int cont = vtOpciones[i][0].split("\\.").length - 1;
					for (int y = 0; y < cont; y++) {
						menu += "</ul></li>";
					}
				}
			}

		}

		menu += "<li>";
		menu += "<a class=\"level-1 level-3\" href=\""+rutaTemp+"/logOff.htm\">&nbsp;&nbsp;Salir</a>";
		menu += "</li>";
		menu += "</ul>";

		//System.out.println("menu control de acceso: "+ menu);

		menu = menu.replace("<ul id=\"xx\" class=\"xx\">", "<ul id=\"idmenu\" class=\"mnmenu\">");
		menu = menu.replace("<li>",	"<li class=\"level-0 middle last\">");
		menu = menu.replace("<ul>", "<ul class=\"level-1\">");

		//menu="<ul class=\"mnmenu\" id=\"idmenu\"><li class=\"level-0 middle last\">Planificaci�n<ul class=\"level-1\" style=\"display: none;\"><li class=\"level-0 middle last level-1 first\">Estructura Geogr�fica<ul class=\"level-1 level-2\" style=\"display: none;\"><li class=\"level-0 middle last level-2 first\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/ubicacion/Continente.jsf\">Continentes</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/ubicacion/RegionContinente.jsf\">Regiones Continentales</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/ubicacion/Pais.jsf\">Pa�ses</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/ubicacion/Estado.jsf\">Estados</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/ubicacion/Ciudad.jsf\">Ciudades</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/ubicacion/Municipio.jsf\">Municipios</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/ubicacion/Parroquia.jsf\">Parroquias</a></li></ul><span class=\"arrow\"> �</span></li><li class=\"level-0 middle last level-1\">Estructura Organizativa<ul class=\"level-1 level-2\" style=\"display: none;\"><li class=\"level-0 middle last level-2 first\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/estructura/Organismo.jsf\">Organismos</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/estructura/NombreOrganismo.jsf\">Nombres del Organismo</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/estructura/GrupoOrganismo.jsf\">Grupos del Organismo</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/estructura/Region.jsf\">Regiones</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/estructura/Sede.jsf\">Sedes</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/estructura/LugarPago.jsf\">Lugares de Pago por Sedes</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/estructura/UnidadAdministradora.jsf\">Unidades Administradores</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/estructura/UnidadEjecutora.jsf\">Unidades Ejecutoras Locales</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/estructura/AdministradoraUel.jsf\">Unidades Ejecutoras por Unidad Administradora</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/estructura/Estructura.jsf\">Estructuras Organizativas</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/estructura/UnidadFuncional.jsf\">Unidades Funcionales</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/estructura/TipoDependencia.jsf\">Tipos de Dependencias</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/estructura/Dependencia.jsf\">Dependencias Administrativas</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/estructura/TipoCaracteristica.jsf\">Tipos de Caracter�sticas(Familias)</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/estructura/CaracteristicaDependencia.jsf\">Caracter�sticas de Dependencias</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/estructura/ClasificacionDependencia.jsf\">Clasificaci�n de Dependencias</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/estructura/ReportEstructura.jsf\">Reportes de Estructura</a></li></ul><span class=\"arrow\"> �</span></li><li class=\"level-0 middle last level-1\">Clases de Cargos y Tabuladores<ul class=\"level-1 level-2\" style=\"display: none;\"><li class=\"level-0 middle last level-2 first\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/cargo/RamoOcupacional.jsf\">Ramos Ocupacionales</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/cargo/GrupoOcupacional.jsf\">Grupos Ocupacionales</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/cargo/SerieCargo.jsf\">Series de Cargos</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/cargo/Tabulador.jsf\">Tabuladores</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/cargo/DetalleTabulador.jsf\">Detalles de un Tabulador</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/cargo/ManualCargo.jsf\">Clasificaci�n de Cargos</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/cargo/Cargo.jsf\">Cargos</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/base/cargo/ReportCargos.jsf\">Reporte de Cargos</a></li></ul><span class=\"arrow\"> �</span></li><li class=\"level-0 middle last level-1\">Clasificaci�n<ul class=\"level-1 level-2\" style=\"display: none;\"><li class=\"level-0 middle last level-2 first\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/planificacion/clasificacion/Perfil.jsf\">Perfil de un Cargo</a></li><li class=\"level-0 middle last level-2\">Requisitos de un Cargo<ul class=\"level-1 level-3\" style=\"display: none;\"><li class=\"level-0 middle last level-3 first\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/sigefirrhh/planificacion/clasificacion/ProfesionCargo.jsf\">Profesionales</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/sigefirrhh/planificacion/clasificacion/ExperienciaCargo.jsf\">Experiencia Laboral</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/sigefirrhh/planificacion/clasificacion/AdiestramientoCargo.jsf\">Adiestramiento</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/sigefirrhh/planificacion/clasificacion/HabilidadCargo.jsf\">Habilidades/Competencias</a></li></ul><span class=\"arrow\"> �</span></li></ul><span class=\"arrow\"> �</span></li><li class=\"level-0 middle last level-1\">Registro de Oferentes/Elegible<ul class=\"level-1 level-2\" style=\"display: none;\"><li class=\"level-0 middle last level-2 first\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/planificacion/elegible/.jsf\">Datos Personales</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/planificacion/elegible/ElegibleEducacion.jsf\">Educaci�n Formal</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/planificacion/elegible/ElegibleProfesion.jsf\">Profesiones y/o Oficios</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/planificacion/elegible/ElegibleEstudio.jsf\">Estudios Informales</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/planificacion/elegible/ElegibleCertificacion.jsf\">Certificaciones</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/planificacion/elegible/ElegibleExperiencia.jsf\">Experiencia Laboral</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/planificacion/elegible/ElegibleIdioma.jsf\">Idiomas</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/planificacion/elegible/ElegibleFamiliar.jsf\">Grupo Familiar</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/planificacion/elegible/ElegibleAfiliacion.jsf\">Afiliaciones a Gremios</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/planificacion/elegible/ElegibleActividadDocente.jsf\">Actividades Docentes</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/planificacion/elegible/ElegibleOtraActividad.jsf\">Otras Actividades</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/planificacion/elegible/ElegibleHabilidad.jsf\">Habilidades/Competencias</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/planificacion/elegible/ElegiblePublicacion.jsf\">Publicaciones</a></li></ul><span class=\"arrow\"> �</span></li><li class=\"level-0 middle last level-1\">Reclutamiento y Selecci�n<ul class=\"level-1 level-2\" style=\"display: none;\"><li class=\"level-0 middle last level-2 first\">Concursos<ul class=\"level-1 level-3\" style=\"display: none;\"><li class=\"level-0 middle last level-3 first\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/sigefirrhh/planificacion/seleccion/Concurso.jsf\">Concursos P�blicos</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/sigefirrhh/planificacion/seleccion/ConcursoCargo.jsf\">Cargos a asociar a Concursos</a></li></ul><span class=\"arrow\"> �</span></li><li class=\"level-0 middle last level-2\">Procesos de Reclutamiento<ul class=\"level-1 level-3\" style=\"display: none;\"><li class=\"level-0 middle last level-3 first\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/sigefirrhh/planificacion/seleccion/PostuladoConcurso.jsf\">Postulados a Cargos por Concursos</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/sigefirrhh/planificacion/seleccion/Preseleccionar.jsf\">Preselecci�n de Postulados</a></li></ul><span class=\"arrow\"> �</span></li><li class=\"level-0 middle last level-2\">Procesos de Selecci�n<ul class=\"level-1 level-3\" style=\"display: none;\"><li class=\"level-0 middle last level-3 first\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/sigefirrhh/planificacion/seleccion/PruebaPreseleccionado.jsf\">Registrar Resultados Pruebas y Entrevistas</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/sigefirrhh/planificacion/seleccion/BaremoPreseleccionado.jsf\">Registrar Resultados Baremos</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/sigefirrhh/planificacion/seleccion/Seleccionar.jsf\">Actualizar Resultados del Proceso de Selecci�n</a></li></ul><span class=\"arrow\"> �</span></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/planificacion/seleccion/PruebaSeleccion.jsf\">Tipos de Pruebas y/o Entrevistas</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/planificacion/seleccion/Varemos.jsf\">Baremos</a></li></ul><span class=\"arrow\"> �</span></li><li class=\"level-0 middle last level-1\">Capacitaci�n<ul class=\"level-1 level-2\" style=\"display: none;\"><li class=\"level-0 middle last level-2 first\">Tablas B�sicas<ul class=\"level-1 level-3\" style=\"display: none;\"><li class=\"level-0 middle last level-3 first\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/sigefirrhh/base/adiestramiento/AreaConocimiento.jsf\">Areas de Conocimiento</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/sigefirrhh/base/adiestramiento/TipoCurso.jsf\">Tipos de Cursos</a></li></ul><span class=\"arrow\"> �</span></li><li class=\"level-0 middle last level-2\">Planificaci�n<ul class=\"level-1 level-3\" style=\"display: none;\"><li class=\"level-0 middle last level-3 first\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/sigefirrhh/planificacion/capacitacion/PlanAdiestramiento.jsf\">Planes Capacitaci�n por Unidad Funcional</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/sigefirrhh/planificacion/capacitacion/ActualizarParticipante.jsf\">Incluir Participantes</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/sigefirrhh/planificacion/capacitacion/ActualizarEstudio.jsf\">Registrar Resultados de un Plan</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/sigefirrhh/planificacion/capacitacion/ReportePlanAdiestramiento.jsf\">Reportes</a></li></ul><span class=\"arrow\"> �</span></li></ul><span class=\"arrow\"> �</span></li><li class=\"level-0 middle last level-1\">Evaluaciones de Desempe�o<ul class=\"level-1 level-2\" style=\"display: none;\"><li class=\"level-0 middle last level-2 first\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/planificacion/evaluacion/Evaluacion.jsf\">Consulta de Evaluaciones</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/planificacion/evaluacion/RegistrarEvaluacion.jsf\">Registrar Evaluaciones</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/planificacion/evaluacion/ActualizarEvaluacion.jsf\">Actualizar/Eliminar Evaluaciones</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/planificacion/evaluacion/Apelacion.jsf\">Apelaciones</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/planificacion/evaluacion/NoEvaluacion.jsf\">Trabajadores No Evaluados</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/planificacion/evaluacion/AccionEvaluacion.jsf\">Tipos de Acciones Resultantes</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/planificacion/evaluacion/ResultadoEvaluacion.jsf\">Tipos de Resultados de Evaluaci�n</a></li><li class=\"level-0 middle last level-2\">Reportes<ul class=\"level-1 level-3\" style=\"display: none;\"><li class=\"level-0 middle last level-3 first\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/sigefirrhh/planificacion/evaluacion/ReportEvaluaciones.jsf\">Reportes Detalle Evaluaciones</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/sigefirrhh/planificacion/evaluacion/ReportResumenEvaluaciones.jsf\">Reportes Resumen Evaluaciones</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Reportes Notificaci�n de Resultados</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Reportes Trabajadores No Evaluados</a></li></ul><span class=\"arrow\"> �</span></li></ul><span class=\"arrow\"> �</span></li><li class=\"level-0 middle last level-1\">Planes de Personal<ul class=\"level-1 level-2\" style=\"display: none;\"><li class=\"level-0 middle last level-2 first\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/planificacion/plan/PlanPersonal.jsf\">Definici�n de Planes</a></li><li class=\"level-0 middle last level-2\">Componentes<ul class=\"level-1 level-3\" style=\"display: none;\"><li class=\"level-0 middle last level-3 first\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/sigefirrhh/planificacion/plan/MovimientosPlan.jsf\">Movimientos de Personal</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/sigefirrhh/planificacion/plan/CargosPlan.jsf\">Movimientos de Cargos</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/sigefirrhh/planificacion/plan/MovimientosPlan.jsf\">Acciones Administrativas</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/sigefirrhh/planificacion/plan/ContratosPlan.jsf\">Contratos de Personal</a></li></ul><span class=\"arrow\"> �</span></li><li class=\"level-0 middle last level-2\">Reportes<ul class=\"level-1 level-3\" style=\"display: none;\"><li class=\"level-0 middle last level-3 first\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/sigefirrhh/planificacion/plan/ReportPlanPersonal.jsf\">Reportes de Planes de Personal</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/sigefirrhh/planificacion/plan/ReportPlanIndicadores.jsf\">Reportes de Indicadores Planes de Personal</a></li></ul><span class=\"arrow\"> �</span></li></ul><span class=\"arrow\"> �</span></li></ul><span class=\"arrow\"> �</span></li><li class=\"level-0 middle last\">Expediente<ul class=\"level-1\" style=\"z-index: 3; left: 0px; right: auto; top: 22px; bottom: auto; display: none;\"><li class=\"level-0 middle last level-1 first\">Expediente<ul class=\"level-1 level-2\" style=\"display: none;\"><li class=\"level-0 middle last level-2 first\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/personal/expediente/Personal.jsf\">Datos Personales</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/personal/expediente/Educacion.jsf\">Educaci�n Formal</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/personal/expediente/ProfesionTrabajador.jsf\">Profesiones y/o Oficios</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/personal/expediente/Estudio.jsf\">Estudios Informales</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/personal/expediente/Certificacion.jsf\">Certificaciones</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/personal/expediente/Experiencia.jsf\">Experiencia Laboral (S. Privado)</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/personal/expediente/Idioma.jsf\">Idiomas</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/personal/expediente/Familiar.jsf\">Grupo Familiar</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/personal/expediente/Afiliacion.jsf\">Afiliaciones a Gremios</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/personal/expediente/ActividadDocente.jsf\">Actividades Docentes</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/personal/expediente/OtraActividad.jsf\">Otras Actividades</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/personal/expediente/Habilidad.jsf\">Habilidades/Competencias</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/personal/expediente/Reconocimiento.jsf\">Reconocimientos</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/sigefirrhh/personal/expediente/Sancion.jsf\">Sanciones</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/personal/expediente/Averiguacion.jsf\">Averiguaciones Administrativas</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/personal/expediente/Certificado.jsf\">Certificados de Carrera</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/personal/expediente/Publicacion.jsf\">Publicaciones</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/personal/expediente/Pasantia.jsf\">Pasant�as</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/sigefirrhh/personal/expediente/Declaracion.jsf\">Declaraciones Juradas</a></li></ul><span class=\"arrow\"> �</span></li><li class=\"level-0 middle last level-1\">Historial<ul class=\"level-1 level-2\" style=\"display: none;\"><li class=\"level-0 middle last level-2 first\">Trayectoria y Antecedenes<ul class=\"level-1 level-3\" style=\"display: none;\"><li class=\"level-0 middle last level-3 first\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Trayectoria en APN</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Actualizar Trayectoria en Organismo</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Antecedentes de Servicio sujeto a LEFP</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Antecedentes de Servicio no sujeto a LEFP</a></li><li class=\"level-0 middle last level-3\">Reportes<ul class=\"level-1 level-4\" style=\"display: none;\"><li class=\"level-0 middle last level-4 first\"> <a class=\"level-1 level-5\" href=\"/sigefirrhh/home.jsf\">Relaci�n de Cargos Trabajador</a></li><li class=\"level-0 middle last level-4\"> <a class=\"level-1 level-5\" href=\"/sigefirrhh/home.jsf\">Antecedentes de Servicios del Trabajador</a></li></ul><span class=\"arrow\"> �</span></li></ul><span class=\"arrow\"> �</span></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/home.jsf\">Encargadur�as</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/home.jsf\">Suplencias</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/home.jsf\">Contratos</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/home.jsf\">Comisi�n de Servicio(Personal Interno)</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/home.jsf\">Servicio Exterior</a></li></ul><span class=\"arrow\"> �</span></li><li class=\"level-0 middle last level-1\">Tablas B�sicas Expediente<ul class=\"level-1 level-2\" style=\"display: none;\"><li class=\"level-0 middle last level-2 first\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/home.jsf\">Grupos de Profesi�n</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/home.jsf\">Subgrupos de Profesi�n</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/home.jsf\">Profesionales/Oficios</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/home.jsf\">Areas de Carrera</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/home.jsf\">Carreras</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/home.jsf\">Carreras por Area</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/home.jsf\">T�tulos de Educaci�n</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/home.jsf\">Idiomas</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/home.jsf\">Tipos de Sanciones</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/home.jsf\">Tipos de Reconocimientos</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/home.jsf\">Tipos de Habilidades/Competencias</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/home.jsf\">Tipos de Otras Actividades</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/home.jsf\">Instituciones P�blicas</a></li></ul><span class=\"arrow\"> �</span></li><li class=\"level-0 middle last level-1\">Reportes<ul class=\"level-1 level-2\" style=\"display: none;\"><li class=\"level-0 middle last level-2 first\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/home.jsf\">Hoja de Vida</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/home.jsf\">Hoja de Vida por Regi�n</a></li><li class=\"level-0 middle last level-2\"> <a class=\"level-1 level-3\" href=\"/sigefirrhh/home.jsf\">Criterios Varios</a></li></ul><span class=\"arrow\"> �</span></li></ul><span class=\"arrow\"> �</span></li><li class=\"level-0 middle last\">Administraci�n<ul class=\"level-1\" style=\"z-index: 3; left: 0px; right: auto; top: 22px; bottom: auto; display: none;\"><li class=\"level-0 middle last level-1 first\">Parametrizaci�n<ul class=\"level-1 level-2\" style=\"z-index: 4; left: 325px; right: auto; top: 0px; bottom: auto; display: none;\"><li class=\"level-0 middle last level-2 first\">Definiciones<ul class=\"level-1 level-3\" style=\"display: none;\"><li class=\"level-0 middle last level-3 first\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/sigefirrhh/base/definiciones/CategoriaPersonal.jsf\">Categor�a de Personal</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Relaci�n Laboral</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Clasificaci�n de Personal</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Grupos de N�mina</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Tipos de Personal</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Restricciones de Personal</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Clasificadores Asociados a Tipo de Personal</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Frecuencias de Pago</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Conceptos</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Frecuencias por Tipo de Personal</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Conceptos por Tipo de Personal</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Conceptos por Cargo</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Conceptos por Tipo Dependencia</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Turnos</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Tipos de Contrato</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Archivos (Disquetes)</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Detalle Archivos (Disquetes)</a></li></ul><span class=\"arrow\"> �</span></li><li class=\"level-0 middle last level-2\">Par�metros y F�rmulas<ul class=\"level-1 level-3\" style=\"display: none;\"><li class=\"level-0 middle last level-3 first\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Conceptos Asociados</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Prima Antiguedad</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Prima por Hijo</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Par�metros Retenciones</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">F�rmulas Bono Vacacional</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">D�as Vacaciones por A�o</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">F�rmula Bono Fin de A�o</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Par�metros Varios</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Par�metros Planilla ARI</a></li></ul><span class=\"arrow\"> �</span></li><li class=\"level-0 middle last level-2\">Tablas B�sicas y N�mina<ul class=\"level-1 level-3\" style=\"display: none;\"><li class=\"level-0 middle last level-3 first\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Bancos</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Cuentas de Banco</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Per�odos Semanales</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Meses</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Sindicatos</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Contratos Colectivos</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Tarifas ARI</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Firmas para Reportes</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Tipos de Ausencias</a></li></ul><span class=\"arrow\"> �</span></li><li class=\"level-0 middle last level-2\">Reportes<ul class=\"level-1 level-3\" style=\"display: none;\"><li class=\"level-0 middle last level-3 first\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Definicios de Conceptos</a></li></ul><span class=\"arrow\"> �</span></li></ul><span class=\"arrow\"> �</span></li><li class=\"level-0 middle last level-1\">Datos del Trabajador<ul class=\"level-1 level-2\" style=\"z-index: 4; left: 325px; right: auto; top: 0px; bottom: auto; display: none;\"><li class=\"level-0 middle last level-2 first\">Trabajador<ul class=\"level-1 level-3\" style=\"z-index: 5; left: auto; right: 325px; top: 0px; bottom: auto; display: none;\"><li class=\"level-0 middle last level-3 first\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Ubicar Trabajador</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Consultar Datos N�mina</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Actualizar Datos del Trabajador</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Actualizar Fechas del Trabajador</a></li><li class=\"level-0 middle last level-3\"> <a class=\"level-1 level-4\" href=\"/sigefirrhh/home.jsf\">Correcci�n/Cambio de C�dula</a></li></ul><span class=\"arrow\"> �</span></li></ul><span class=\"arrow\"> �</span></li></ul><span class=\"arrow\"> �</span></li><li class=\"level-0 middle last\"><a href=\"/sigefirrhh/logout.jsf\" class=\"level-1 level-3\">&nbsp;&nbsp;Salir</a></li></ul>";

		//System.out.println("menu control de acceso2: "+ menu);
%>
		
	<style type="text/css" media="print">
		@media print {
		#contenedor {display:none;}
		#cintilloImagenes {display:none;}
		#barraEstatus {display:none;}
		}
	</style>

		<div id="contenedor" class="contenedor">		
				<script type="text/javascript">
					menu= '<%=menu%>';
					document.write(menu);
				</script>		
		</div>	
<%} %>		
		
		