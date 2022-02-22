/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConnection Class. 
 * @author eric
 */
public class DBConnection {
    
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//wgudb.ucertify.com:3306/";
    private static final String dbName = "WJ05wZb";
    private static final String jdbcURL = protocol + vendorName + ipAddress + dbName;
    private static final String MYSQLJDBCDriver = "com.mysql.jdbc.Driver";
    private static final String userName = "U05wZb";
    private static final String password = "53688631053";
    private static Connection conn = null;
    
    /**
     * Starts a connection with the DB. 
     * @return
     * @throws SQLException
     */
    public static Connection startConnection() throws SQLException {
        
        if (conn != null && conn.isValid(1)) {
            return conn;
        }
        
        try {
            Class.forName(MYSQLJDBCDriver);
            conn = DriverManager.getConnection(jdbcURL, userName, password);
        } catch(SQLException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return conn;
    }
    
    /**
     * Gets a connection with the DB. 
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        if (conn != null && conn.isValid(1)) {
            return conn;
        } else {
        return DBConnection.startConnection();
        }
    }
    
    /**
     * Closes database connection. 
     */
    public static void closeConnection() {
        try {
            conn.close();
        } catch(Exception e) {
        }
    }
    
}
