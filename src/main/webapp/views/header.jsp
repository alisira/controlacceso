<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page import="com.mf.controlacceso.helpers.html" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap"%>

<% 	HttpServletRequest httpServletRequest = (HttpServletRequest)pageContext.getRequest();
	
	Map contenido = (HashMap)request.getAttribute("contenido");
	String js[] = (String[]) contenido.get("js");
	String css[] = (String[]) contenido.get("css");
%>
        
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<%=html.escribirJS(js, httpServletRequest) %>
		<%=html.escribirCSS(css, httpServletRequest) %>

        <title><?php echo $titulo_pagina ?></title>
    </head>
    <body>	
    
        <br>   
            <div class="container bg-container">
                <div>
                    <img src="<?php echo $config->ruta_imagenes; ?>cintillo WEB ministerio Juventud Bicentenaria-1.jpg"  /> 

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
                                    <div align="left">&nbsp;[
                                        <?php
                                        echo $ambiente;
                                        ?>
                                        ]:

                                    </div>                                    
                                </td>        

                                <td width="28%" class="sombra2" align="right">
                                    <?php echo $this->funciones->print_fecha() ?>
                                </td>              
                            </tr>
                        </table>
                    </div>