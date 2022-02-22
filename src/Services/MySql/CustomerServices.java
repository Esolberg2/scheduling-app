/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.MySql;

import c195.DBConnection;
import DTOs.customerDTO;
import Models.Customer;
import Services.TimeConverter;
import c195.C195;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * CustomerServices Class. 
 * @author eric
 */
public class CustomerServices {
    
    /**
     * Get a specific customer from database by customer ID. 
     * @param ID ID of customer
     * @return customerDTO for specified customer
     */
    public static customerDTO getCustomerByID(Integer ID) {
        try {
        
        String sql =  "select * from customers where Customer_ID = ?";
      
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setString(1, String.valueOf(ID));
        ResultSet rs = ps.executeQuery();
        
        return new customerDTO(
                    rs.getInt("Customer_ID"),
                    rs.getString("Customer_Name"),
                    rs.getString("Address"),
                    rs.getString("Postal_Code"),
                    rs.getString("Phone"),
                    TimeConverter.toLocal(rs.getTimestamp("Create_Date").toLocalDateTime()),
                    rs.getString("Created_By"),
                    TimeConverter.toLocal(rs.getTimestamp("Last_Update").toLocalDateTime()),
                    rs.getString("Last_Updated_By"),
                    rs.getInt("Division_ID")
                   );

        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
           return null;
    }
    
    /**
     * Function to update existing customer record.
     * @param customer Customer object to save to database
     */
    public static void saveCustomer(Customer customer) {
        saveCustomer(
                customer.getCustomerName(),
                customer.getCustomerAddress(),
                customer.getCustomerPostalCode(),
                customer.getCustomerPhone(),
                customer.getDivisionID(),
                customer.getCustomerID()
                );
    }
    
    /**
     * Function used to save a new customer to the database. 
     * @param Customer_Name
     * @param Address
     * @param Postal_Code
     * @param Phone
     * @param Division_ID
     */
    public static void saveCustomer( // use for creating new customer.  passes default value of -1 to customer ID.  This causes the next call to saveCustoer to insert rather than update.
            String Customer_Name,
            String Address,
            String Postal_Code,
            String Phone,
            Integer Division_ID
    ){
        saveCustomer(Customer_Name, Address, Postal_Code, Phone, Division_ID, -1);
    }
    
    /**
     * Save customer to database if customer does not exist, otherwise update the matching customer record. 
     * @param Customer_Name
     * @param Address
     * @param Postal_Code
     * @param Phone
     * @param Division_ID
     * @param Customer_ID
     */
    public static void saveCustomer(
            String Customer_Name,
            String Address,
            String Postal_Code,
            String Phone,
            Integer Division_ID,
            Integer Customer_ID
    ) {
    try {
        String sql = "INSERT INTO customers\n" +
        "(Customer_ID," +
        "Customer_Name, " +
        "Address, " +
        "Postal_Code, " +
        "Phone, " +
        "Create_Date, " +
        "Created_By, " +
        "Last_Update, " +
        "Last_Updated_By, " +
        "Division_ID) " +
        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n" +
        "ON DUPLICATE KEY UPDATE\n" +
        "Customer_Name = ?,\n" +
        "Address = ?,\n" +
        "Postal_Code = ?,\n" +
        "Phone = ?,\n" +
        "Last_Update = ?,\n" +
        "Last_Updated_By = ?,\n" +
        "Division_ID = ?";
        
//        insert
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
       
        if (Customer_ID == -1) {
            ps.setNull(1, java.sql.Types.INTEGER);
        } else {
            ps.setInt(1, Customer_ID);
        }
        ps.setString(2, Customer_Name);
        ps.setString(3, Address);
        ps.setString(4, Postal_Code);
        ps.setString(5, Phone);
        ps.setString(6, TimeConverter.toUTC(LocalDateTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        ps.setString(7, "application");
        ps.setString(8, TimeConverter.toUTC(LocalDateTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        ps.setString(9, "application"); 
        ps.setInt(10, Division_ID);
        
//        update
        ps.setString(11, Customer_Name);
        ps.setString(12, Address);
        ps.setString(13, Postal_Code);
        ps.setString(14, Phone);
        ps.setString(15, TimeConverter.toUTC(LocalDateTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        ps.setInt(16, C195.currentUser.getUserID()); 
        ps.setInt(17, Division_ID);
        
        int rs = ps.executeUpdate();

    } catch(SQLException throwables) {
        throwables.printStackTrace();
    }

}
    
    /**
     * Delete customer record from database. 
     * @param ID ID of customer to delete
     * @throws SQLException
     */
    public static void deleteCustomer(Integer ID) throws SQLException {
        String sql =  String.format("delete from customers where Customer_ID = %s", ID.toString());
        Connection connection;
           try {
               connection = DBConnection.getConnection();
               try{
                    connection.setAutoCommit(false);
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate(sql);
                    connection.commit();
                } catch (SQLException e) {
                    connection.rollback();
                    throw e;
                } finally {
                    connection.setAutoCommit(true);
                    DBConnection.closeConnection();
                }
               
            } catch(Exception e) {
                throw e;
            }    
    }
}
