/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.MySql;

import c195.DBConnection;
import DTOs.divisionDTO;
import Services.TimeConverter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

/**
 * DivisionRosterServices Class. 
 * @author eric
 */
public class DivisionRosterServices {
    
    /**
     * Get all division records from DB.
     * @return Map of divisionDTOs for all divisions in database
     */
    public static TreeMap<Integer, divisionDTO> loadAllDivisions() {
        
        TreeMap<Integer, divisionDTO> divisions = new TreeMap<Integer, divisionDTO>();
        
        try {
            String sql = "select * from first_level_divisions";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                divisions.put(rs.getInt("Division_ID"), new divisionDTO(
                    rs.getString("Division_ID"),
                    rs.getString("Division"),
                    TimeConverter.toLocal(rs.getTimestamp("Create_Date").toLocalDateTime()),
                    rs.getString("Created_By"),
                    TimeConverter.toLocal(rs.getTimestamp("Last_Update").toLocalDateTime()),
                    rs.getString("Last_Updated_By"),
                    rs.getString("Country_ID")
                ));
            }
            DBConnection.closeConnection();
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return divisions;
    }
}

