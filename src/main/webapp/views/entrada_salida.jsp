<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>
    fecha = new Date("${contenido.fecha}");
    actualiza_pagina(false);
</script>
<br>

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
            <?php
            $attributes = array('id' => 'control_acceso_con');
            echo form_open('control_acceso_con/', $attributes)
            ?>
            <div class="span4 well" >
                <div id="contenedorVideo" class="contenedor" style="display:<?php echo $visibleVideo; ?>; caption-side: top; clear: inherit; clip: inherit;float: left;">
                    <video id="video" autoplay="autoplay" ></video>
                </div>

                <div id="contenedorFoto" class="contenedor" style="display:<?php echo $visibleFoto; ?>; caption-side: top; clear: inherit; clip: inherit;float: left;">
                    <img id="fotoTrabajador" name="fotoTrabajador" src="<?php echo $fotoTXT; ?>"  >                                       
                </div> 
            </div>
            <div class="span6 well" >
                <div class="contenedor" style="display:block; text-align: center ">
                    <div id="AlertaNoCedula" style="text-align: center; display:none;"></div>                                                       
                    <div>                                                         
                        <b>
                            <font color="#006600" style="font-size:18px">
                                CEDULA DEL EMPLEADO
                            </font>
                        </b>
                    </div>
                    <div>
                        <input id="cedula" name="cedula" type="text" autofocus="autofocus"  onkeypress="return ocultar_mostrar(event)" placeholder="C&eacute;dula del empleado" <?php echo set_value('cedula') ?>>
                    </div>
                </div> 
                <div id="contenedorObservacion" style="display:block; text-align: center ">
                    <div>
                        <b>
                            <font color="#006600" style="font-size:18px">
                                OBSERVACION
                            </font>
                        </b>
                    </div>
                    <div>
                        <input id="observacion" name="observacion" onkeypress="return ocultar_mostrar(event)" type="text" value="" size ="35" >
                    </div>   

                </div>
                <div id="resultado" class="contenedor" style="text-align: center; display:block; caption-side: top; clear: inherit; clip: inherit;">
                    <?php if (strlen($nombre_apellido) > 0) { ?>

                        <b><font color="#FF0000" style="font-size:18px" face="Arial, Helvetica, sans-serif">

                            <?php if ($tipo_registro == 0) { ?>
                                <?php echo "ENTRADA DE: " . $nombre_apellido . "<BR>" ?>
                                </font>
                            </b>
                            <img src="<?php echo $config->ruta_imagenes; ?>entra.png" width="120" height="110"><BR>
                        <?php } ?>

                        <?php
                        if ($tipo_registro == 1) {
                            echo "SALIDA DE: " . $nombre_apellido . "<BR>"
                            ?>
                            </font>
                            </b>
                            <img src="<?php echo $config->ruta_imagenes; ?>sale.png" width="120" height="110"><BR>
                        <?php } ?>



                    <?php } else if ($mensaje) { ?>

                        <b><font color="#FF0000" style="font-size:18px" face="Arial, Helvetica, sans-serif">
                            <?php echo $mensaje ?>
                            </font>
                        </b>                                         
                        <BR>
                        <img src="<?php echo $config->ruta_imagenes; ?>LogoError.jpeg" width="120" height="110">

                    <?php } ?>
                </div>
                <div id="contenedorCanvas" style="display:none">
                    <canvas id="micanvas" name="micanvas" ></canvas>                                        
                </div>                             

                <div style="display:block; caption-side: top; clear: both; clip: inherit;float: none; ">
                    <input type="hidden" id="fotoTXT" name="fotoTXT" value ="">                                        
                </div>
                <div><?php echo anchor('miformato_con', 'Ir', 'title="Titulares"');
                    ?></div>
            </div>    
            </form>                                    
        </td>  
    </tr>
</table>

<div style="height: 50px"></div>

<script>
    var canvas = document.getElementById("micanvas");
    var ctx = canvas.getContext("2d");
    //document.getElementById('cedula').focus();
    hora()
</script>    