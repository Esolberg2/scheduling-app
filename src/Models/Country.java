/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DTOs.countryDTO;
import java.time.LocalDateTime;

/**
 * Country Object. 
 * @author eric
 */
public class Country {
    
    private int countryID;
    private String countryName;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    
    /**
     * Constructor. 
     * @param data
     */
    public Country(countryDTO data) {
            this.countryID = data.getCountryID();
            this.countryName = data.getCountryName();
            this.createDate = data.getCreateDate();
            this.createdBy = data.getCreatedBy();
            this.lastUpdate = data.getLastUpdate();
            this.lastUpdatedBy = data.getLastUpdatedBy();
        }

     /**
     * Custom toString method. 
     * @return name of country
     */
    @Override
    public String toString() {
        return this.countryName;
    }
    
    /**
     * countryID getter. 
     * @return countryID
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * contryName getter. 
     * @return name of country
     */
    public String getCountryName() {
        return countryName;
    }
        
}
