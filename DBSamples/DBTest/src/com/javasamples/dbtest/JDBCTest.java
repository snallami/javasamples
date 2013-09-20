package com.javasamples.dbtest;

import java.io.IOException;
import java.sql.*;

public class JDBCTest {
	
	public static void main(String[] args) throws IOException {
	     Connection connection = null;
	        try {
	        	connection = DBUtil.getDirectJdbcConnection();
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
