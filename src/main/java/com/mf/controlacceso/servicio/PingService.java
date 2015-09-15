package com.mf.controlacceso.servicio;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class PingService {
	
	InitialContext cxt = null;
	DataSource ds = null;
	Connection conn = null;
	
	public PingService(){
		super();
		try{
    		this.cxt = new InitialContext();
    		this.ds = (DataSource) cxt.lookup( "java:jboss/datasources/DBPoolSigefirrhh" );
    		
    	}catch (Exception e){
    		e.printStackTrace();
    	}
	}
	
	public boolean pingDB() throws SQLException	 {
		
		conn = ds.getConnection();
    	conn.close();    	

  	   	return true;  	
	}
	
	public boolean pingWeb() throws SQLException	 {
		
		conn = ds.getConnection();
    	conn.close();    	

  	   	return true;  	
	}
	
	public boolean pingWebDB() throws SQLException	 {
		
		pingDB();
		pingWeb();

  	   	return true;  	
	}
}
