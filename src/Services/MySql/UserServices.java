/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.MySql;

import c195.DBConnection;
import DTOs.userDTO;
import Services.TimeConverter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * UserServices Class. 
 * @author eric
 */
public class UserServices {
    
    /**
     * Get user from DB by username.
     * @param user_name username of user to retrieve
     * @return userDTO for username provided
     */
    public static userDTO getUserByName(String user_name) {
    try {
        String sql = "select * from users where User_Name = ?";

        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

        ps.setString(1, user_name);

        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            return new userDTO(
                rs.getInt("user_id"),
                rs.getString("user_name"),
                rs.getString("password"),
                TimeConverter.toLocal(rs.getTimestamp("create_date").toLocalDateTime()),
                rs.getString("created_by"),
                TimeConverter.toLocal(rs.getTimestamp("last_update").toLocalDateTime()),
                rs.getString("last_updated_by")
            );
        }
        DBConnection.closeConnection();
    } catch(SQLException throwables) {
        throwables.printStackTrace();
    }
    return null;

}
}
