/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.MySql;

import DTOs.contactDTO;
import c195.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

/**
 * ContactRosterServices Class. 
 * @author eric
 */
public class ContactRosterServices {
    
    /**
     * Get all contacts from database.
     * @return Map of contactDTOs
     */
    public static TreeMap<Integer, contactDTO> getContacts(){
        TreeMap<Integer, contactDTO> contacts = new TreeMap<Integer, contactDTO>();
    
        try {
        String sql = "select * from contacts";

        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            contacts.put(rs.getInt("Contact_ID"), new contactDTO(
                rs.getInt("Contact_ID"),
                rs.getString("Contact_Name"),
                rs.getString("Email")
            ));
        }
        DBConnection.closeConnection();
    } catch(SQLException throwables) {
        throwables.printStackTrace();
    }
    return contacts;
    }
    
}
