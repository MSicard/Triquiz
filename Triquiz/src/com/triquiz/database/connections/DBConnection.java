package com.triquiz.database.connections;

import java.sql.Connection;
import java.sql.DriverManager;

import com.triquiz.database.tools.Commons;

public class DBConnection {
	private static Connection conn;
	
	public static Connection getInstance() throws Exception{
		if(conn == null){
			Class.forName(Commons.DRIVER).newInstance();
			conn = DriverManager.getConnection(Commons.URL + Commons.DBNAME,
					Commons.USER, Commons.PASSWORD);
		}
		return conn;
	}
	
	public static void close(){
		try{
			conn.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
