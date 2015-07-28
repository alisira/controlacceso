<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html>
<HEAD>
<script type="text/javascript" language="Javascript">
<!-- Begin
document.oncontextmenu = function(){return false}
// End -->
</script>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<LINK rel="STYLESHEET"
	href="<%=request.getContextPath()%>/estilos/estilo-sigecof.css"
	media="screen,print" type="text/css">
<LINK rel="STYLESHEET"
	href="<%=request.getContextPath()%>/estilos/estilos-interfaz.css"
	media="screen" type="text/css">
<META http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<TITLE>Interfaz de Gestión de Tareas - Login de Usuario</TITLE>
<SCRIPT language="JavaScript"
	SRC="<%=request.getContextPath()%>/js/sigecof-valores.js"> </SCRIPT>
<!--Archivo para la validacion-->
<script language="JavaScript"
	src="<%=request.getContextPath()%>/js/gen_validatorv2.js"
	type="text/javascript"></script>
<!--Archivo para la validacion de clave -->
<script language="JavaScript"
	src="<%=request.getContextPath()%>/js/md5.js" type="text/javascript"></script>
<SCRIPT>
	/*******************************************************************************************************
	********************************************************************************************************/
	function encriptar(){		
		document.getElementById("clave").value = hex_md5(document.getElementById("claveAux").value);		
	}
													
	/*******************************************************************************************************
	********************************************************************************************************/
	function cualFue(e)
	{
		if(document.all){
			if (window.event.keyCode==13) 
			{
				validarForma();
			}
		}else{
			if(e.keyCode==13){
				validarForma();
			}
		}
	}
 function abre()
	  {
	    var ventana=window;
		var ancho=830;
		var alto=780;
		var x=1;
		var y=1;
		//ventana.resizeTo(ancho,alto);
	    //ventana.moveTo(x,y);
	    ventana.toolbar='no'; 
	   
	    if(document.getElementById("maximo").firstChild.value=="true")
	       {
	         document.getElementById("maximo").firstChild.value="false";
	         var ventanaCerrarSesiones= window.open("<%=request.getContextPath()%>/jsp/InterfazNegociadora/cerrarSesiones.jsp","ventanaCerrarSesiones","toolbar=0,copyhistory=0,location=0,directories=0,status=0,menubar=0,scrollbars=0,resizable=0,width=310,height=150,left=300,top=300");
	         ventanaCerrarSesiones.focus();		
	       }
	  }
	
 var id,position=0,veces=0,sw=0;

 function scorrevole()
  {
    var i;
	var k;
	var msg="           "+document.getElementById("mensaje").value+"           ";
	    
	k=(100/msg.length)+1;		
     
	for(i=0;i<=k;i++)
	    msg+=" "+msg;
			 
		document.getElementById("scorrevole2").value=msg.substring(position,position+100);
        position++;
		var limite=position+100; 
 		if(limite>=msg.length) 
		  { position=0;           
          }

     if(sw==0)
       {
        veces=veces+1;
         if(veces>=1000)
           {
             veces=0; 
             actualizaMensaje();
           }    
        }  
     else
        {
         veces=0; 
        }
		id=setTimeout("scorrevole()",50); 		
   }


    //para el manejo de mensajes online
    function actualizaMensaje()
     {sw=1;
      cargarData("<%=request.getContextPath()%>/mensajeOnline.gestion",procesarMensaje);      	
     }

   

      function procesarMensaje() 
		{
			if (req.readyState == 4) 
			{
			 if (req.status == 200) 
				{						
					
					var descDoc = req.responseXML;
					var docEl = descDoc.documentElement;
					var mensaje = obtenerTextoXML(docEl,"mensaje"); 
					document.getElementById("mensaje").value=mensaje;		
                    var prioridad = obtenerTextoXML(docEl,"prioridad");			
                    var prioridadAnterior=document.getElementById("prioridad").value;		
                    document.getElementById("prioridad").value=prioridad;			
					if(prioridad==3 || prioridad==2){
                      document.getElementById("scorrevole2").style.backgroundColor="yellow";
					}else{
                      document.getElementById("scorrevole2").style.backgroundColor="#84B5E1";
                    }
					if(prioridad==3 && prioridadAnterior!=3)
            	       {  
                          var mensajeLinea= window.open("<%=request.getContextPath()%>/jsp/InterfazNegociadora/mensajeAlerta.jsp","mensajeLinea","toolbar=0,copyhistory=0,location=0,directories=0,status=0,menubar=0,scrollbars=0,resizable=0,width=310,height=150,left=300,top=300");
	                      mensajeLinea.focus();		                     
                       }  
                    sw=0;
				} 
			 else
	          {
	           if (req.status == 404)
				{
			 	  document.getElementById("mensaje").value="Problemas al intentar conectarse al servidor";	 
                  sw=0;
				}
				else 
				{
 			 	  document.getElementById("mensaje").value="Problemas al intentar conectarse al servidor";	 
                  sw=0;
				}
	         } 
			} 
		}


     //fin de mensaje
	
	 //cargar el organismo /////
	
	  function listaOrganismos()
	  {
	     var ventanaListaOrganismo= window.open("<%=request.getContextPath()%>/organismos.gestion?procesarData=lista","ventanaListaOrganismos","toolbar=0,copyhistory=0,location=0,directories=0,status=1,menubar=0,scrollbars=1,resizable=1,width=560,height=420,left=100,top=50");
	     ventanaListaOrganismo.focus();		
	  }
	
	  function actualizarOrganismo(codigo,denominacion)
	  {
	     document.getElementById("organismo").value=codigo;
	     document.getElementById("denominacion").value=denominacion;    
	     document.getElementById("idUsuario").focus();
         //cargarDenominacionOrganismo();
	  }
   
	
	 function traerDatosOrganismo(organismo) 
	  {
			orga_id = organismo.value;

			if(!V2validateData("req",organismo,"Este campo es obligatorio")
			||!V2validateData("numeric",organismo,"Este campo sólo acepta caracteres numéricos")
			||!V2validateData("maxlen=2",organismo,"El tamaño del campo es de 2 caracteres")
			 )
			{	
				organismo.focus();
				return false;
			}

			if (orga_id != "")
			{
	        	cargarData("<%=request.getContextPath()%>/organismos.gestion?procesarData=campo&orga="+orga_id,procesarDatosOrganismo);
			}
	       else
	        {      
			 document.getElementById("organismo").value="";
		     document.getElementById("denominacion").value="";    
		     document.getElementById("organismo").focus();
             //cargarDenominacionOrganismo();
		    }    
	  }
	
	function procesarDatosOrganismo() 
		{
			if (req.readyState == 4) 
			{
			 if (req.status == 200) 
				{						
					var descDoc = req.responseXML;
					var docEl = descDoc.documentElement;
					//window.alert(docEl);
	
					var denominacion = obtenerTextoXML(docEl, "denominacion"); 
					//window.alert(denominacion);
	               
	                document.getElementById("denominacion").value=denominacion;
	                document.getElementById("idUsuario").focus();
					
				} 
			 else
	          {
	           if (req.status == 404)
				{
					window.alert("El organismo no está registrado o existe una falla de comunicación");
	                document.getElementById("organismo").value="";
					document.getElementById("organismo").fous();
	                document.getElementById("denominacion").value="";
				}
				else 
				{
	                document.getElementById("organismo").value="";
					document.getElementById("organismo").fous();
	                document.getElementById("denominacion").value="";
					window.alert("No se puede realizar la búsqueda intente de nuevo por favor");
				}
	         } 
			}
           //cargarDenominacionOrganismo();
		}
	 //cargar el organismo /////
	
	/////////Lectura con XML//////////////////////////
	  var req;
			
		function cargarData(url, funcion) {
			if(window.XMLHttpRequest) 
			{
	   			try {
					req = new XMLHttpRequest();
	        	} catch(e) {
					req = false;
	        	}
			} 
			else if(window.ActiveXObject) 
			{
				try {
					req = new ActiveXObject("Msxml2.XMLHTTP");
				} catch(e) {
					try {
						req = new ActiveXObject("Microsoft.XMLHTTP");
					} catch(e) {
						req = false;
					}
				}
			}
			if (req) 
			{
				req.onreadystatechange = funcion;
				req.open("GET", url, true);
				req.send("");
			}
		}
	
	   function obtenerTextoXMLNoNull(elem, tag) {
			var texto = obtenerTextoXML(elem, tag);
			if (texto == null)
				return "";
			return texto;
		}
	
		function obtenerTextoXML(elem, tag) {
			var tags = elem.getElementsByTagName(tag);
			if (tags.length == 0)
				return null;
			var textoNode = tags.item(0).firstChild;
			if (textoNode == null)
				return null;
			return textoNode.nodeValue;
		}
	/////////Fin lectura con XML//////////////////////////

    function cargarDenominacionOrganismo()
    {
     var denominacion = document.getElementById("denominacion").value;
     denominacion=denominacion+" ";
     var i=35;
     if(denominacion.length>0)
     { 
     while(denominacion.substring(i-1,i)!=" ")
       {
        i--;
       }
     document.getElementById("organismo1").value=denominacion.substring(0,i);
     document.getElementById("organismo2").value=denominacion.substring(i,70);
     }
    }

function validarForma(){
	/*var objeto = document.getElementById("organismo");
	if(!V2validateData("req",objeto,"Por Favor introduzca el código de un Organismo")|
	!V2validateData("numeric",objeto,"Este campo sólo acepta números")|
	!V2validateData("maxlen=2",objeto, "El tamaño máximo del campo es de 2"))
	{
		objeto.focus();
		return false;
	}*/
	
	objeto = document.getElementById("idUsuario");
	if(!V2validateData("req",objeto,"Por Favor introduzca el nombre del Usuario")|
	!V2validateData("alphanumeric",objeto,"")|
	!V2validateData("sql",objeto,"")|
	!V2validateData("maxlen=15",objeto,"El tamaño máximo del campo es de 15"))
	{
		objeto.focus();
		return false;
	}


	objeto = document.getElementById("claveAux");
	if(!V2validateData("req",objeto,"Por Favor introduzca la clave del Usuario")||
	!V2validateData("alphanumeric",objeto,"")||
	!V2validateData("sql",objeto,"")||
	!V2validateData("maxlen=15",objeto, "El tamaño máximo del campo es de 15"))
	{
		objeto.focus();
		return false;
	}

	encriptar();
	document.forms.login.submit();
}
	</SCRIPT>
<!--
	<style type="text/css">

			body {
			background-attachment: fixed;
			background-image: url(<%=request.getContextPath()%>/images/min_banner.png);
			background-repeat: no-repeat;
			background-position: top center;
			}

	</style>
-->	
	<style type="text/css">

			body {
			background-attachment: fixed;
			background-image: url(<%=request.getContextPath()%>/images/bg_interfazNeg.png);
			background-repeat: no-repeat;
			background-position: top center;
			overflow:hidden;
			}

	</style>
	
	
</HEAD>


<!-- <BODY
	onload="abre();scorrevole();cargarDenominacionOrganismo();actualizaMensaje();"
	style=" background-color: #FFFFFF;" leftMargin=0 topMargin=0
	rightMargin=0 marginwidth="0" marginheight="0" bottommargin="0">  -->
	<BODY
	onload="abre();scorrevole();actualizaMensaje();"
	style=" background-color: #FFFFFF;" leftMargin=0 topMargin=0
	rightMargin=0 marginwidth="0" marginheight="0" bottommargin="0"> 
<table align="center" width="100%">
	<tr>
		<td>
		<table width="825" border="0" bordercolor="#ffffff" align="center">

			<tr>
				<td width="1" height="602">&nbsp;</td>
				<td style="background-repeat:no-repeat;" width="780">
				<br>
				<br>
				<br>
				<br>
				<br>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<table width="769" border="0" height="526" align="center">

					<tr height="220" valign="top">
						<!-- desde aqui -------------------------------
				  		  
				  -->
						<td width="429" valign="top" align="center"><br>

						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>

						<table width="100%">
							<tr>
								<td>

								<table width="96%" cellpadding="0" cellspacing="0" border="0"
									align="center">
									<tr>

										<td valign="top" align="right" width="153">&nbsp;</td>
										<td width="44"></td>
										<td width="203" valign="top" align="left" colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;</td>

									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td>
								<table border="0" cellpadding="0" cellspacing="0" width="95%"
									align="center">
									<tr height="220" align="left">
										<td width="160" height="70" align="left"><br>&nbsp; </td>
										<td valign="middle" width="236">&nbsp;</td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td>
								<table border="0" cellpadding="0" cellspacing="0" width="95%"
									align="center">
									<tr height="220" align="left">
										<td width="160" height="70" align="left"><br>&nbsp; </td>
										<td valign="middle" width="236">&nbsp;</td>
									</tr>
								</table>
								</td>
							</tr>
							
							<tr>
								<td>
								<table border="0" cellpadding="0" cellspacing="0" width="46%" align="center">

									<tr valign="top">
										<td valign="top" colspan="3" id="center_align_top" align="center">&nbsp;</td>
									</tr>
								</table>

								</td>
							</tr>
						</table>
login-
						</TD>
						<TD id="center_align_top" width="330" >
						<html:form action="/login.gestion" styleId="login">
						
							<table border="0" width="292" height="22" align="center">
								<TR>
									<TD>
									<table border="0" width="292" height="22" align="center"
										style="display:none;">
										<tr height="19">
											<TD id="mensaje_style" align="left" width="292">
											<div id="mensaje_style2"><INPUT type="text" size="42"
												id="scorrevole2" class="inputlov"><input
												type="hidden" id="prioridad"
												value='<bean:write name="loginAF" property="prioridad"/>' />
											<input type="hidden" id="mensaje"
												value='<bean:write name="loginAF" property="mensaje"/>' /></div>
											</TD>
										</tr>
									</table>

									<div id="maximo"><html:hidden property="maximo" /></div>
									<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><!-- aqui login -->
									<table border="0" width="292" height="179" >
										<tr height="179">
											<TD width="312" bgcolor="#ffffff">

											<table width="290" height="184" border="0">
												<tr>
													<TD height="3" colspan="4">&nbsp;</TD>
												</tr>
												<!-- <tr>
													<td width="74" HEIGHT="40" CLASS="text8n"
														STYLE="padding-left: 3px; text-align: left" valign="top"><B><font
														color="#006699"
														face="Verdana, Arial, Helvetica, sans-serif">Organismo</font></B></td>
													<TD WIDTH="28" HEIGHT="40" CLASS="text8n"
														STYLE="padding-left: 3px; text-align: left" valign="top">
													<TABLE HEIGHT="14" BORDER="0" WIDTH="24" CELLSPACING="1px"
														CELLPADDING="0" CLASS="inputtable">
														<TR>
															<TD WIDTH="18" HEIGHT="14" CLASS="inputtdtext"><html:text
																property="usuario.organismo.idOrganismo"
																styleId="organismo" tabindex="1" size="1" maxlength="2"
																style="text-align:right;padding-right:2px"
																styleClass="inputlov"
																onchange="traerDatosOrganismo(this);"></html:text></TD>
															<TD WIDTH="10" HEIGHT="14" tabindex="-1"
																CLASS="inputtdlov"><INPUT TYPE="button"
																tabindex="-1" VALUE="..." NAME="lovOrganismo"
																CLASS="inputlovbutton" onclick="listaOrganismos()">
															</TD>
														</TR>
													</TABLE>
													</TD>
													<TD WIDTH="170" HEIGHT="40"
														STYLE="padding-left: 6px; text-align: left; color: #336699;"
														valign="top"><html:hidden
														property="usuario.organismo.denominacion"
														styleId="denominacion" /> <INPUT type="text" size="26"
														id="organismo1" class="inputlov"
														style="color: #006699; font-size: 7pt; font-family: verdana; font-weight: bold;"
														readonly="readonly"> <INPUT type="text" size="26"
														id="organismo2" class="inputlov"
														style="color: #006699; font-size: 7pt; font-family: verdana; font-weight: bold;"
														readonly="readonly"></TD>
													<td width="3">&nbsp;</td>
												</tr>  -->

												<tr>
													<td HEIGHT="13" CLASS="text8n"
														STYLE="padding-left: 3px; text-align: left"
														valign="middle"><B><font color="#006699"
														face="Verdana, Arial, Helvetica, sans-serif">Usuario</font></B></td>
													<td colspan="2" STYLE="padding-left: 3px; text-align: left"><html:text
														property="usuario.idUsuario" styleId="idUsuario"
														maxlength="15" /></td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td HEIGHT="13" CLASS="text8n"
														STYLE="padding-left: 3px; text-align: left"
														valign="middle"><B><font color="#006699"
														face="Verdana, Arial, Helvetica, sans-serif">Clave</font></B></td>
													<td colspan="2" STYLE="padding-left: 3px; text-align: left"><input
														type="password" id="claveAux" maxlength="15"
														onkeypress="cualFue(event);" /></td>
													<td>&nbsp;</td>
												</tr>

												<tr>
													<td colspan="3" HEIGHT="13" CLASS="text8n"
														STYLE="padding-left: 3px; text-align: center"
														valign="middle"><html:button property="boton"
														value="Entrar" onclick="validarForma();" /></td>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<TD height="34" colspan="4" CLASS="text8n"
														STYLE="padding-left: 0px; text-align: left">&nbsp;</TD>
												</tr>
											</table>
											</TD>
										</tr>
									</table>

									<html:hidden styleId="clave" property="usuario.clave" />
									<table border="0" width="289" height="18" id="center_align"
										align="center">
										<tr height="18" align="left">
											<TD width="283" align="left">
											<div align="center"><font color="#660033" size="2"><strong>
											<html:errors property="error.workflow.ingresoOrganismo" /> <br>
											<!--<html:errors property="error.workflow.ingresoUsuarioClave" />-->
											<html:errors property="error.workflow.ingresoUsuario" /> <html:errors
												property="error.workflow.ingresoClave" /> <html:errors
												property="error.workflow.LoginClaveInvalida" /> <html:errors
												property="error.workflow.maximoConexiones" /> <html:errors
												property="error.workflow.usuarioInativo" /> <html:errors
												property="error.workflow.LoginTextoInvalida" /> </strong></font></div>
											</TD>
										</tr>
									</table>
									</html:form></TD>
								</TR>
							</table></TD>
					</tr>
					<tr>
						<TD colspan="2">&nbsp;</TD>
					</tr>
				</table>
				</td>
				<td width="16">&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<!-- 
<map name="Map">
	<area shape="rect" coords="1,1,130,73" href="http://www.oncop.gov.ve"
		target="_blank"
		alt="Ir a Página Oficial de la Oficina Nacional de Contabilidad Pública">

</map>
<map name="Map2">
	<area shape="rect" coords="0,1,140,58" href="http://www.ont.mf.gov.ve/"
		target="_blank"
		alt="Ir a Página Oficial de la Oficina Nacional del Tesoro">
</map>
<map name="Map3">
	<area shape="rect" coords="-1,0,130,59"
		href="http://www.ocepre.gov.ve/" target="_blank"
		alt="Ir a Página Oficial de la Oficina Nacional de Presupuesto">
</map>
<map name="Map4">
	<area shape="rect" coords="2,1,133,59" href="http://www.oncp.gob.ve/"
		target="_blank"
		alt="Ir a Página Oficial de la Oficina Nacional de Credito Público">

</map>
 -->

</BODY>
</html:html>
