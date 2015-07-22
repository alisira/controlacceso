INSERT INTO public.aplicacion(id_aplicacion, nombre, estatus, descripcion) VALUES ('1'::integer, 'controlacceso'::text, 'A'::text, 'Control de Acceso Empleados y Visitantes'::text)

INSERT INTO public.perfil(id_perfil, denominacion, estatus, id_aplicacion) VALUES ('1'::integer, 'Administrador Control de Acceso'::text, 'A'::text, '1'::integer)

INSERT INTO public.perfil_usuario(id_perfil_usuario, id_perfil, id_usuario) VALUES ('1'::integer, '1'::integer, '1'::integer)

INSERT INTO public.proceso(id_proceso, id_aplicacion, denominacion, estatus, descripcion, cod_aplicacion) VALUES ('1'::integer, '1'::integer, 'Control de Acceso'::text, 'A'::text, 'Menu Principal de Control de Acceso'::text, '1.0.0'::text)