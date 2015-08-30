--Aplicacion

ALTER TABLE perfil DROP CONSTRAINT perfil1;
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

ALTER TABLE proceso DROP COLUMN id_padre;
alter table proceso rename column cod_aplicacion to jerarquia_menu;
alter table proceso rename column ruta to ruta_menu;
ALTER TABLE proceso  ALTER COLUMN url TYPE character varying(120);
ALTER TABLE proceso DROP CONSTRAINT proceso_denominacion_key;
ALTER TABLE proceso ALTER COLUMN denominacion TYPE character varying(90);
ALTER TABLE perfil_proceso ALTER COLUMN priuse DROP NOT NULL;
ALTER TABLE proceso ALTER COLUMN jerarquia_menu set NOT NULL;
ALTER TABLE proceso ALTER COLUMN jerarquia_menu TYPE character varying;

