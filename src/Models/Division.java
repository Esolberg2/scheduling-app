/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DTOs.divisionDTO;
import java.time.LocalDateTime;

/**
 * Division Object. 
 * @author eric
 */
public class Division {
    
    private String Division_ID;
    private String Division_Name;
    private LocalDateTime Create_Date;
    private String Create_By;
    private LocalDateTime Last_Updated;
    private String Last_Updated_By;
    private String Country_ID; 
    
    /**
     * Constructor. 
     * @param data divisioneDTO object
     */
    public Division(divisionDTO data) {
        
        this.Division_ID = data.getDivision_ID();
        this.Division_Name = data.getDivision_Name();
        this.Create_Date = data.getCreate_Date();
        this.Create_By = data.getCreate_By();
        this.Last_Updated = data.getLast_Updated();
        this.Last_Updated_By = data.getLast_Updated_By();
        this.Country_ID = data.getCountry_ID();
    }

    /**
     * Division_ID getter. 
     * @return ID of division
     */
    public String getDivision_ID() {
        return Division_ID;
    }

    /**
     * Division_ID getter. 
     * @return name of division
     */
    public String getDivision_Name() {
        return Division_Name;
    }

    /**
     * Create_Date getter. 
     * @return date that division record was created
     */
    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    /**
     * Create_By getter. 
     * @return method that created the division record
     */
    public String getCreate_By() {
        return Create_By;
    }

    /**
     * Last_Updated getter. 
     * @return datetime that division record was last updated
     */
    public LocalDateTime getLast_Updated() {
        return Last_Updated;
    }

    /**
     * Last_Updated_By getter. 
     * @return method that last updated the division record
     */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /**
     * Country_ID getter. 
     * @return ID of country associated with division
     */
    public String getCountry_ID() {
        return Country_ID;
    }
    
    /**
     * Custom toString method. 
     * @return name of division
     */
    @Override
    public String toString() {
        return Division_Name;
    }
    
    
}
