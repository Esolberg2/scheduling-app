/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.MySql;

import c195.DBConnection;
import DTOs.countryDTO;
import Services.TimeConverter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

/**
 * CountryRosterServices Class. 
 * @author eric
 */
public class CountryRosterServices {
    
    /**
     * Get all country records from DB. 
     * @return Map of countryDTOs
     */
    public static TreeMap<Integer, countryDTO> loadAllCountries() {
        
        TreeMap<Integer, countryDTO> countries = new TreeMap<Integer, countryDTO>();
        
        try {
            String sql = "select * from countries";

            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                countries.put(rs.getInt("Country_ID"), new countryDTO(
                    rs.getInt("Country_ID"),
                    rs.getString("Country"),
                    TimeConverter.toLocal(rs.getTimestamp("Create_Date").toLocalDateTime()),
                    rs.getString("Created_By"),
                    TimeConverter.toLocal(rs.getTimestamp("Last_Update").toLocalDateTime()),
                    rs.getString("Last_Updated_By")
                ));
            }
            DBConnection.closeConnection();
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        return countries;
    }
}
