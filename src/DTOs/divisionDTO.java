/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.time.LocalDateTime;

/**
 * Transfer Object for division data
 * @author eric
 */
public class divisionDTO {
    
    private String Division_ID;
    private String Division_Name;
    private LocalDateTime Create_Date;
    private String Create_By;
    private LocalDateTime Last_Updated;
    private String Last_Updated_By;
    private String Country_ID; 
    
    /**
     * Constructor for creating divisionDTO. 
     * @param Division_ID
     * @param Division_Name
     * @param Create_Date
     * @param Create_By
     * @param Last_Updated
     * @param Last_Updated_By
     * @param Country_ID
     */
    public divisionDTO(
        String Division_ID,
        String Division_Name,
        LocalDateTime Create_Date,
        String Create_By,
        LocalDateTime Last_Updated,
        String Last_Updated_By,
        String Country_ID) {
        
        this.Division_ID = Division_ID;
        this.Division_Name = Division_Name;
        this.Create_Date = Create_Date;
        this.Create_By = Create_By;
        this.Last_Updated = Last_Updated;
        this.Last_Updated_By = Last_Updated_By;
        this.Country_ID = Country_ID;
    }

    /**
     * Division_ID getter. 
     * @return ID for division
     */
    public String getDivision_ID() {
        return Division_ID;
    }

    /**
     * Division_Name getter. 
     * @return name of division
     */
    public String getDivision_Name() {
        return Division_Name;
    }

    /**
     * Create_Date getter. 
     * @return datetime that division record was created
     */
    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    /**
     * Create_By getter. 
     * @return method that created division record
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
     * @return method that last updated division record
     */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /**
     * Country_ID getter
     * @return ID of country associated with division record
     */
    public String getCountry_ID() {
        return Country_ID;
    }
    
    
}

