<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<br><br>
<div class="span3" style="height: 60px"></div>

<div class="span4 well">
	
	<form:form method="post" action="/controlacceso/login.htm" name="login.htm" id="login.htm">

	    <table border="0"  cellpadding="0" cellspacing="0" class="fondo_tabla01">
	
	        <tr class="titulo01">
	            <td height="20" colspan="2" >
	                <div class="alert-info" align="center">
	                    <strong>Inicio de Sesi&oacute;n </strong>
	                </div>
	            </td>
	        </tr>
	
	        <tr class="error">
	            <td height="20" colspan="2">
	                <div align="center">
	                    <strong><?php if(isset($mensaje))echo $mensaje;   ?></strong>
	                </div>
	                <div align="center"></div>
	            </td>
	        </tr>
	
	        <tr>
	            <td id ="rowdinamico" width="28%" rowspan="7">
	                <div align="center">
	                    <img src="imagenes/gpg.png" width="64" height="64" />
	                </div>
	            </td>
	            <td height="20" class="etiqueta01">
	                <div align="left" class="texto_10"><span>Nombre de Usuario</span>
	                </div>
	            </td>
	        </tr>
	
	        <tr>
	            <td width="72%">
	                <div align="left">
	                    <input name="loginTxt" type="text" id="loginTxt" maxlength="20" autofocus="autofocus" placeholder="Nombre de usuario"  />
	                </div>
	            </td>
	        </tr>
	
	        <tr>
	            <td height="20" class="etiqueta01"><div align="left"><span class="texto_10">Contrase&ntilde;a</span></div></td>
	        </tr>
	
	        <tr>
	            <td><div align="left">
	                    <input name="password" type="password" id="password" maxlength="20" placeholder="Contrase&ntilde;a" />
	                </div>
	            </td>
	        </tr>        
	
	        <tr id="botones_normal">
	            <td class="etiqueta01">
	                <input type="submit" id="sumitir" name="sumitir" value="Entrar" />
	                <span class="fondo3">
	                    <input type="hidden" name="log" value="true" />                                                    
	                </span>
	            </td>
	        </tr>
	
	        <tr class="error">
	            <td colspan="2">&nbsp;
	     </td>
	        </tr>
	    </table>                                    

    </form:form>
</div>

<div style="height: 400px"><p></p>
</div>