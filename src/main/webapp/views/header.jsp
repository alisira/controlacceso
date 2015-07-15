<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page import="com.mf.controlacceso.helpers.Html" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap"%>

<%
	HttpServletRequest httpServletRequest = (HttpServletRequest)pageContext.getRequest();
	
	Map contenido = (HashMap)request.getAttribute("contenido");
	String js[] = (String[]) contenido.get("js");
	String css[] = (String[]) contenido.get("css");;
%>
        
<html xmlns="http://www.w3.org/1999/xhtml">
	<meta charset="utf-8" /> 
    <head>
		<%=Html.escribirJS(js, httpServletRequest) %>
		<%=Html.escribirCSS(css, httpServletRequest) %>

        <title>${contenido.tituloPagina}</title>
    </head>
    <body>	
    
        <br>   
            <div class="container bg-container">
                <div>
                    <img src="imagenes/cintillo.png"  /> 

                </div>
            </div>
              
                <div class="container bg-container">        
                    <div class="alert-info">

                        <table width="100%" border="0" cellspacing="2" cellpadding="0">

                            <tr>
                                <td width="10%" class="sombra1">
                                    <div align="right"><strong>Ambiente:</strong></div>
                                </td>

                                <td width="62%" class="sombra2">
                                    <div align="left">&nbsp;[<c:out value="${contenido.ambiente}"/> ]:

                                    </div>                                    
                                </td>        

                                <td width="28%" class="sombra2" align="right">
                                    <?php echo $this->funciones->print_fecha() ?>
                                </td>              
                            </tr>
                        </table>
                    </div>