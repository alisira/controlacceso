package com.mf.controlacceso.dao;

import java.util.List;

public interface GenericDAO {
    public void create();
    public void saveOrUpdate(Object reg);
    public Object get(Object obj);
    public void delete(Object id);
    List<Object> findAll();
    public List<Object> listar(Object entity);   
}
