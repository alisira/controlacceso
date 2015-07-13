$(document).ready(function(){
    $("#sumitir").click(function() {
        login = $("#login").val();
        password = $("#password").val();
        validacion_login = /^[a-z\d_]{3,14}$/;
        validacion_password = /^[a-z\d_]{3,14}$/;

        if (login == "" || !validacion_login.test(login)){
            $("#login").focus();
            alert('Login Invalido, Favor Corregir');
            return false;
        }else if(password == "" || !validacion_password.test(password)){
            $("#password").focus();
            alert('Contrase\u00f1a Invalida, Favor Corregir');
            return false;
        }else{
            return true;
        }
    });
    
});

function foco_lg(){
    document.getElementById('login').focus();
}

function foco2(){
    document.getElementById('newpassword').focus();
}      