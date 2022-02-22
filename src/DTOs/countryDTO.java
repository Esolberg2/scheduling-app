/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.time.LocalDateTime;

/**
 * Transfer Object for country data
 * @author eric
 */
public class countryDTO {
    int countryID;
    String countryName;
    LocalDateTime createDate;
    String createdBy;
    LocalDateTime lastUpdate;
    String lastUpdatedBy;
    
    /**
     *
     * @param countryID
     * @param country
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     */
    public countryDTO(
        int countryID,
        String country,
        LocalDateTime createDate,
        String createdBy,
        LocalDateTime lastUpdate,
        String lastUpdatedBy
        ) {
        
        this.countryID = countryID;
        this.countryName = country;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        
    }

    /**
     * countryID getter. 
     * @return ID for country
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * countryName getter. 
     * @return name of country
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * createDate getter. 
     * @return creation date for country record
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * createdBy getter. 
     * @return method that created the country record.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * lastUpdate getter. 
     * @return last updated datetime for country record.
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * lastUpdatedBy getter. 
     * @return method that the country record was last updated.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    
    /**
     * Overridden function. 
     * @return the name of the country
     */
    @Override
    public String toString() {
        return this.getCountryName();
    }
    
    
}
