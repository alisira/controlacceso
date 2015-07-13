<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Login_con extends CI_Controller{        
    
    public function __construct() {
        parent::__construct();
        $this->load->model('Utilidades_db_mod');
        $this->load->helper('url');

        if (!$this->Utilidades_db_mod->ambiente[0]) {
            //Si Se Perdio la conexion con la base de datos redirige al home del sistema;
            redirect('', 'location');
        }
        //Carga los demas modelos y las configuraciones comunes
        $this->load->model('configuracion_mod');
        $this->load->library('funciones');
        $this->load->model('usuario_mod');
        $this->load->model('menu_mod');
        $this->load->helper('form'); 
        $this->load->helper('html');
        
        //Parametros comunes en todos los metodos
        $this->data['ambiente'] = $this->Utilidades_db_mod->ambiente[1];
        $this->data['config'] = $this->configuracion_mod;
        $this->data['titulo_pagina'] = 'Menu de Opciones';        
    }
    
    public function __destruct() {        
        $this->Utilidades_db_mod=null;
        $this->configuracion_mod = null;
        $this->usuario_mod = null;
        $this->menu_mod = null;
        $this->data = null;
    }    

    public function valida_usuario() {
        $login = $this->input->post('login');
        $pass = md5($this->input->post('password'));
        $this->data['css'] = array('bootstrap.css', 'global_admin.css', 'styleIE.css');
        $this->data['js'] = array('jqmin.js', 'jquery.js', 'menu_view.js');
        $this->data['contenido'] = 'login_view';
        $this->data['menu'] = '';
        $this->data['mensaje'] = '';
        $valida1 = $this->usuario_mod->validar_usuario($login, $pass);
        
        if ($valida1) {
            $valida2 = $this->menu_mod->opcionMenu($login);
            if (count($valida2) > 0) {
                $this->data['mensaje'] = "Acceso exitoso";
                $this->data['title'] = "Pagina Principal";
                $this->data['opciones_menu'] = $valida2;
                $this->data['contenido'] = 'principal_view';
                $this->data['mensaje'] = 'Bienvenido al sistema control de acceso';
                $usuario = $this->usuario_mod->usuario($login);
                $id_instalacion = $usuario['id_instalacion'];
                $_SESSION['id_instalacion'] = $id_instalacion;
                $_SESSION['login'] = $login;

                $this->data['alto_menu'] = '5px';
                $_SESSION['opciones_menu'] = $this->data['opciones_menu'];
                $this->data['salir'] = $this->configuracion_mod->logoff;
            } else {
                $this->data['mensaje'] = "Error No Tiene Procesos Asignados, consulte al administrador";                
                $this->data['contenido'] = 'login_view';                
            }
        } else {
            $this->data['mensaje'] = "Datos Invalidos favor corregir, gracias";
            $this->data['contenido'] = 'login_view';            
        }
        $this->load->view('templates/plantilla', $this->data);
    }

    public function cambia_clave(){
        echo 'Cambia Clave';
    }
}

