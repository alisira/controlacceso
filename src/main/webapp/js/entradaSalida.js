$(document).ready(
    function(){
        //var canvas = $('#micanvas'),
        //cxt = canvas[0].getContext('2d'),
        video = $('#video'),
        video = video[0];
 
        if (navigator.getUserMedia){
            navigator.getUserMedia(
                { 'video': true },
                function(stream){
                    alert('paso getUserMedia');
                    video.src = stream;
                    video.play();
                }
            );
        }else if (navigator.webkitGetUserMedia){
            navigator.webkitGetUserMedia
            (
                { 'video': true },
                function(stream)
                {
                    alert('paso webkitGetUserMedia');
                    video.src = window.webkitURL.createObjectURL(stream);
                    video.play();
                }
            );
        }else if (navigator.mozGetUserMedia){
            //Cuando se usa firefox
            navigator.mozGetUserMedia
            (
                { 'video': true },
                function(stream)
                {
                    //alert('paso mozGetUserMedia');
                    video.mozSrcObject = stream;
                    video.play();
                },
                function(err){
                    //
                    //alert('An error occured! '+err);
                }
            );
        }
   
        $("#cedula").keypress(function(event){
            ocultar_mostrar(event);
        });
        
        $("#observacion").keypress(function(event){
            ocultar_mostrar(event);
        });
        
    }    
);
            


//Temporizador para poner la hora en pantalla de acuerdo a la q tiene el servidor web
function temporizador(){
	
	tiempo = getHora(fechaHora);
    document.getElementById('hora').innerHTML= "Hora: " + tiempo;
    fechaHora.setSeconds(fechaHora.getSeconds()+1);
    setTimeout("temporizador()",1000);        
    document.getElementById('fecha').innerHTML= "Fecha: " + fechaHora.getDate() + "/" + (fechaHora.getMonth() +1) + "/" + fechaHora.getFullYear();
    
    //alert(ultimoAcceso + ' ' + getHora(ultimoAcceso));
    
    if (document.getElementById('ultimoAcceso') != null){
    	document.getElementById('ultimoAcceso').innerHTML = getHora(ultimoAcceso);	
    }
    
}

timerID = '';
function actualiza_pagina(pValor){

    //alert(pValor);                
    if (pValor===false){
        clearTimeout(timerID);
        timerID = setTimeout("actualiza_pagina(true)", 240000);
    }else{
        document.getElementById("cedula").value = "";                    
        form = document.getElementById("entradaSalida");                    
        form.submit();
    }

}

function enviar(){

    //alert('enviar')
    oCamara = jQuery('#video');
    oFoto = jQuery('#micanvas');
    w = oCamara.width();
    h = oCamara.height();
    oFoto.attr({
        'width': w,
        'height': h
    });
    cxt = oFoto[0].getContext('2d');
    cxt.drawImage(oCamara[0], 0, 0, w, h);

    foto = document.getElementById("foto");
    foto.value = canvas.toDataURL("image/jpeg", 0.8 )||canvas.toDataURL("image/jpeg");

    //alert(foto.value );
    form = document.getElementById("entradaSalida");                    
    form.submit();                 
}

function ocultar_mostrar(event){

    actualiza_pagina(false);//Reiniciar el contador de actualización de la pagina

    document.getElementById('AlertaNoCedula').style.display = 'none';
    document.getElementById('resultado').style.display = 'none';
    document.getElementById('contenedorFoto').style.display = 'none';
    document.getElementById('contenedorVideo').style.display = 'block';
    cedula = $("#cedula").val();
    validacion_cedula = /^[0-9]{0,8}?$/;

    if (event.keyCode === 13 ){
        if (cedula !== '' || !validacion_cedula.test(cedula)){
            enviar();
         }else{                         
                       document.getElementById('AlertaNoCedula').style.display = 'block';
            document.getElementById('AlertaNoCedula').innerHTML = '<label class="titulo03 blanco14"><b>Debe ingresar su n&uacute;mero de c&eacute;dula</b></label>'
            document.getElementById('resultado').innerHTML = '<label class="titulo03 blanco14"><b>Debe ingresar su n&uacute;mero de c&eacute;dula</b></label>'

            //alert("Favor Ingrese Cedula");
        }
    }
}
