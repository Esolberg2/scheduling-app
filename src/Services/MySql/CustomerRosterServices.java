/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.MySql;

import c195.DBConnection;
import DTOs.customerDTO;
import Services.TimeConverter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * CustomerRosterServices Class. 
 * @author eric
 */
public class CustomerRosterServices {
    
    /**
     * Get all customers from DB. 
     * @return ObservableList of customerDTOs
     */
    public static ObservableList<customerDTO> loadAllCustomers() {
        System.out.println("  loadAllCustomers run ");
        
          ObservableList<customerDTO> customersData = FXCollections.observableArrayList();
        try {
                String sql =  "select * from WJ05wZb.customers order by Customer_ID";

                PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    customersData.add(new customerDTO(
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
                    ));
                }
                DBConnection.closeConnection();
            } catch(SQLException throwables) {
                throwables.printStackTrace();
            }
        return customersData;
    }
    
}
