/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.MySql;

import DTOs.userDTO;
import c195.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * UserRosterServices Class. 
 * @author eric
 */
public class UserRosterServices {
    
    /**
     * Get all users from database. 
     * @return ObservableList of userDTOs for all users in DB
     */
    public static ObservableList<userDTO> loadAllUsers() {
        
          ObservableList<userDTO> usersData = FXCollections.observableArrayList();
          
        try {
                String sql =  "select * from WJ05wZb.users order by User_ID";

                PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

                ResultSet rs = ps.executeQuery();
           
                while(rs.next()){
                    usersData.add(new userDTO(
                        rs.getInt("User_ID"),
                        rs.getString("User_Name")     
                    ));
                }
                DBConnection.closeConnection();
            } catch(SQLException throwables) {
                throwables.printStackTrace();
            }
        return usersData;
    }
    
}
