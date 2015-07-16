function getFecha(dateHour){
	
	fecha = new Date(dateHour);	
	fecha = fecha.getDate() + "/" + (fecha.getMonth() +1) + "/" + fecha.getFullYear();		
	
    return fecha;
}

function getHora(dateHour){
	
	var hora=dateHour.getHours();
	
	if (hora > 12){
        hora = hora -12;
        periodo = 'pm';
    }else{
        periodo = 'am';
    }

    var minutos=dateHour.getMinutes();
    var segundos=dateHour.getSeconds();

    if(hora<10){ hora='0'+hora;}
    if(minutos<10){minutos='0'+minutos; }
    if(segundos<10){ segundos='0'+segundos; }
    tiempo=hora+":"+minutos+":"+segundos + ' ' + periodo;
    return tiempo;
}