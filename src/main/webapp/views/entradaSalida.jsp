<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script>
    var fechaHora = new Date("${contenido.fechaHora}");
    var ultimoAcceso= new Date("${contenido.fechaHora}");
    actualiza_pagina(false);
</script>

<br>

<form:form method="post" commandName="entradaSalida" name="entradaSalida">
	<table width="100%" border="0" cellpadding="4" cellspacing="0" class="fondo_tabla01" >

	    <tr>
	        <td align="left">
	            <b>
	            	<font color="#006600" style="font-size:18px"> 
	            		<div id="fecha" style="text-align: left; font-size:18px" >
	                        Fecha: 
	                    </div>
					</font>
				<b>
	        </td>
	        <td align="right">
	            <b>
	                <font color="#006600" style="font-size:18px"> 
	                    <div id="hora" style="text-align: right; font-size:18px" >
	                        Hora: 
	                    </div> 
	                </font>
	            <b> 
	        </td>
	    </tr>	
	
	    <tr>
	        <td colspan="2" class="titulo01" align="center"><div class="titulo01" align="center" style="font-size:14px">
	            <div  class="alert-info">Control de Acceso de Empleados</div>
	        </td>
	    </tr>
	    
	    <tr>
	        <td align="center" valing="middle" colspan="2">
	            <br>	           
	            <div class="span6 well" >
	                <div id="contenedorVideo" class="contenedorVideoFoto" style="display:${contenido.visibleVideo};">
	                    <video id="video" autoplay="autoplay" ></video>
	                </div>
	
	                <div id="contenedorFoto" class="contenedorVideoFoto" style="display:${contenido.visibleFoto}; height:300px ">
	                    <img id="fotoImagen" name="fotoImagen" src="${contenido.foto}" >
	                    <input id="foto" name="foto" type= "hidden" />
	                </div>
	            </div>
	            <div class="span6 well" >
	                <div id="contenedorCedula" style="display:block; text-align: center;font-family: Verdana,Arial,Helvetica,sans-serif; ">
	                    <div id="AlertaNoCedula" style="text-align: center; display:none;"></div>    
	                        
                        <div style="font-size:18px; color: #006600; font-weight: bold;">
							CEDULA DEL EMPLEADO
						</div>
	                    
	                    <div>
	                        <input id="cedula" name="cedula" type="text" autofocus="autofocus"  onkeypress="return ocultar_mostrar(event)" placeholder="C&eacute;dula" value="${contenido.cedula}" style="width:80px" />
	                    </div>
	                </div>
	                
	                <div id="contenedorObservacion" style="display:block; text-align: center;font-family: Verdana,Arial,Helvetica,sans-serif; ">
	                    <div style="font-size:18px; color: #006600; font-weight: bold;">
							OBSERVACION
	                    </div>
	                    
	                    <div>
	                        <input id="observacion" name="observacion" onkeypress="return ocultar_mostrar(event)" type="text" value="" placeholder="Ingrese una Observación en caso necesario" style="width:370px">
	                    </div>   
	
	                </div>
	                
	                <div id="resultado" class="contenedor" style="text-align: center; display:block; caption-side: top; clear: inherit; clip: inherit;">

						<c:if test="${contenido.visibleFoto == 'block'}">
							<c:if test="${contenido.entrada == true }">
								<div style="font-size: 18px; color: #006600; font-weight: bold;">
									Entrada de:
									<c:out value="${contenido.nombreApellido}" /> a las <div id="ultimoAcceso"></div>
									<br> 
									<img src="imagenes/entra.png" width="120" height="110">
									<br>
								</div>
							</c:if>
							<c:if test="${contenido.entrada == false }">
								<div style="font-size: 18px; color: #006600; font-weight: bold;">
									Salida de:  
									<c:out value="${contenido.nombreApellido}"/> a las <div id="ultimoAcceso"></div>  
									<br>
									<img src="imagenes/sale.png" width="120" height="110" >
									 <br>
								 </div>
							</c:if>							
						</c:if>
						
						<c:if test="${contenido.mensaje !=''}">
							<c:if test="${contenido.salida == '1' }">
								<div style="font-size: 18px; color: #006600; font-weight: bold;">
									MENSAJE: <c:out value="${contenido.mensaje}}"/>
									<img src="imagenes/LogoError.jpeg" width="120" height="110">									
								 </div>
							</c:if>
						</c:if>
						
	                <div id="contenedorCanvas" style="display:none">
	                    <canvas id="micanvas" name="micanvas" ></canvas>                                        
	                </div>                             
	
	                <div style="display:block; caption-side: top; clear: both; clip: inherit;float: none; ">
	                    <input type="hidden" id="fotoTXT" name="fotoTXT" value ="">                                        
	                </div>
	                <div><?php echo anchor('miformato_con', 'Ir', 'title="Titulares"');
                    ?></div>
	            </div>    
				</div>                 
	        </td>  
	    </tr>
	</table>
</form:form>

<div style="height: 50px"></div>

<script>
    var canvas = document.getElementById("micanvas");
    var ctx = canvas.getContext("2d");
    //document.getElementById('cedula').focus();
    temporizador()
</script>    