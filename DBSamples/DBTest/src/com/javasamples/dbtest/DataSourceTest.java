package com.javasamples.dbtest;

import java.io.IOException;
import java.sql.*;

import javax.naming.NamingException;

public class DataSourceTest {
	public static void main(String[] args) throws IOException, NamingException {
	     Connection connection = null;
	        try {
	        	connection = DBUtil.getConnectionFromPool();
	        	System.out.println("Executing query");
	        	Statement statement = connection.createStatement() ;
	        	ResultSet resultset = statement.executeQuery("select * from employee") ;
	        	
	        	DBUtil.displayData(resultset);
	        	
	        } catch (java.sql.SQLException ex) {
	            System.out.println(ex);
	        } finally {
	        	DBUtil.closeConnection(connection);
	        }

	}

}
