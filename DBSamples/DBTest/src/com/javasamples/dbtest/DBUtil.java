package com.javasamples.dbtest;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.DataSource;

public final class DBUtil {
     

    /**
     * This API tries to acquire Oracle Database connection by direct JDBC lookup.
     * @return Connection Oracle Database connection
     * @throws SQLException
     * @throws IOException
     */
    public static Connection getDirectJdbcConnection() throws SQLException, IOException {
    	System.out.println("Trying to get DB connection via direct JDBC call");
    	Properties prop		  = loadProperties("db.properties");
        String 	   jdbcUrl	  = "jdbc:oracle:thin:@" + prop.getProperty("database_hostname") + 
        						":"+ prop.getProperty("database_port") + ":" + prop.getProperty("database_name");
        Connection connection = DriverManager.getConnection(jdbcUrl, prop.getProperty("database_username"), prop.getProperty("database_password"));
        System.out.println("Got Database connection successfully");
        return connection;
    }

   /**
    * This API tries to acquire Database connection from weblogic connection pool
    * @return Connection Oracle Database connection
    * @throws SQLException
    * @throws IOException	 
    */
    public static Connection getConnectionFromPool() throws SQLException, NamingException, IOException {
    	System.out.println("Trying to get DB connection from connection pool");
    	Properties prop		  = loadProperties("db.properties");
    	
    	Hashtable<String,String> ht = new Hashtable<String,String>();
    	ht.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
    	ht.put(Context.PROVIDER_URL, prop.getProperty("wls_serverURL"));
    	ht.put(Context.SECURITY_PRINCIPAL, prop.getProperty("wls_username"));
    	ht.put(Context.SECURITY_CREDENTIALS, prop.getProperty("wls_password"));

    	InitialContext context = new InitialContext(ht);
    	DataSource dataSource = (javax.sql.DataSource) context.lookup(prop.getProperty("jndi_name"));
    	Connection connection = dataSource.getConnection();
    	System.out.println("Got Database connection successfully");
    	return connection;
    }

    /**
     * This API loads properties from file
     * @param fileName
     * @return
     * @throws IOException
     */
    private static Properties loadProperties(String fileName) throws IOException {
    	Properties properties = new Properties();
    	
    	try {
    		properties.load(new FileInputStream(fileName));
    	} catch (IOException ex) {
    		ex.printStackTrace();
    		throw ex;
    	}
    	return properties;
    }
    
    /**
     * Generic API which display data in resultset along with column names
     * @param rs
     * @throws SQLException 
     */
    public static void displayData(ResultSet rs) throws SQLException {
    	ResultSetMetaData resultSetMetaData = rs.getMetaData();
    	int 			  columnCount       = resultSetMetaData.getColumnCount();
    	
    	// Prints Column names
    	for (int columnIndex = 1 ; columnIndex <= columnCount; columnIndex++) {
    		System.out.print(resultSetMetaData.getColumnLabel(columnIndex));
    		System.out.print("  ");
    	}
    	
    	// Prints data
    	while (rs.next()) {
    		System.out.println();
    		
    		for (int columnIndex = 1 ; columnIndex <= columnCount; columnIndex++) {
        		System.out.print(rs.getString(columnIndex));
        		System.out.print("  ");
        	}
    	}
    }
    
    public static void closeConnection(Connection connection) {
    	if (connection != null) {
            try {
            	connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    	
    }
    	
}