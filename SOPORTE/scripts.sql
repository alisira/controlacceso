--Aplicacion

ALTER TABLE perfil DROP CONSTRAINT perfil1
ALTER TABLE perfil
  ADD CONSTRAINT perfil_aplicacion FOREIGN KEY (id_aplicacion) REFERENCES aplicacion (id_aplicacion)
   ON UPDATE NO ACTION ON DELETE NO ACTION;

alter table proceso drop CONSTRAINT proceso1;
alter table proceso add CONSTRAINT proceso1 FOREIGN KEY (id_aplicacion)
      REFERENCES aplicacion (id_aplicacion) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

alter table perfil_usuario drop  CONSTRAINT perfil_usuario1;
alter table perfil_usuario add CONSTRAINT perfil_usuario1 FOREIGN KEY (id_perfil) 
      REFERENCES perfil (id_perfil) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

alter table perfil_usuario drop  CONSTRAINT perfil_usuario2;
alter table perfil_usuario add CONSTRAINT perfil_usuario2 FOREIGN KEY (id_usuario)
      REFERENCES usuario (id_usuario) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

alter table perfil_proceso drop CONSTRAINT perfil_proceso1; 
alter table perfil_proceso add CONSTRAINT perfil_proceso1 FOREIGN KEY (id_perfil)
      REFERENCES perfil (id_perfil) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

alter table perfil_proceso drop CONSTRAINT perfil_proceso2;      
alter table perfil_proceso add CONSTRAINT perfil_proceso2 FOREIGN KEY (id_proceso)
      REFERENCES proceso (id_proceso) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
   
--1;"controlacceso";"A";"Control de Acceso Empleados y Visitantes"
DELETE FROM aplicacion;
INSERT INTO public.aplicacion(id_aplicacion, nombre, estatus, descripcion) VALUES ('1'::integer, 'controlacceso'::text, 'A'::text, 'Control de Acceso Empleados y Visitantes'::text)


delete from proceso;
delete from perfil_proceso;
delete from perfil_usuario;
delete from perfil;
delete from aplicacion;


--aplicacion
"1";"controlacceso";"A";"Control de Acceso Empleados y Visitantes"
--Perfil
"1";"Administrador Control de Acceso";"A";1
--Perfil_Usuario
"1";1;1
--Perfil_Proceso
"1";1;1;"A"
"2";1;2;"A"
"3";1;3;"A"
"4";1;4;"A"
"5";1;5;"A"
"6";1;6;"A"
"7";1;7;"A"
"8";1;8;"A"
"9";1;9;"A"
"10";1;10;"A"
"11";1;11;"A"
"12";1;12;"A"
"13";1;13;"A"
"14";1;14;"A"
--Proceso
"1";1;"Control de Acceso";"A";"Menu Principal de Control de Acceso";"";"1.0.0               "
"2";1;"Acceso de Visitantes";"A";"Pantalla de Acceso a Visitantes";"";"1.1.0               "
"3";1;"Acceso de Empleados";"A";"Pantalla de Acceso a Empleados";"control_acceso_con";"1.2.0               "
"4";1;"Reportes";"A";"Acceso Principal a Reportes";"";"2.0.0               "
"5";1;"Reporte de Acceso Empleados";"A";"Reporte de Acceso Empleados";"";"2.1.0               "
"6";1;"Reporte de Acceso Visitantes";"A";"Reporte de Acceso Visitantes";"";"2.2.0               "
"7";1;"RRHH";"A";"RRHH";"";"3.0.0               "
"8";1;"Empleados";"A";"Empleados";"";"3.1.0               "
"9";1;"Administracion";"A";"Administracion del Sistema";"";"4.0.0               "
"10";1;"Procesos";"A";"Procesos";"";"4.1.0               "
"11";1;"Perfil";"A";"Perfil";"";"4.2.0               "
"12";1;"Proceso por Perfil";"A";"Proceso por Perfil";"";"4.3.0               "
"13";1;"Usuario";"A";"Usuario";"";"4.4.0               "
"14";1;"Cerrar Sesion";"A";"Salir del sistema";"logoff";"5.0.0               "
"";;"";"";"";"";""



