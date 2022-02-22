/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.time.LocalDateTime;

/**
 * Transfer Object for customer data
 * @author eric
 */
public class customerDTO {
    private Integer customerID;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;
    private Integer divisionID;
    

    /**
     * Transfer Object for customer data
     * @param customerID
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param createDate
     * @param createdBy
     * @param lastUpdated
     * @param lastUpdatedBy
     * @param divisionID
     */
    public customerDTO(
            Integer customerID,
            String customerName,
            String address,
            String postalCode,
            String phone,
            LocalDateTime createDate,
            String createdBy,
            LocalDateTime lastUpdated,
            String lastUpdatedBy,
            Integer divisionID){
        
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
    }
    
    // getters

    /**
     * customerID getter. 
     * @return ID of customer
     */
    public int getCustomerID(){
        return customerID;
    }
    
    /**
     * customerName getter. 
     * @return Name of customer
     */
    public String getCustomerName(){
        return customerName;
    }
    
    /**
     * address getter. 
     * @return address of customer.
     */
    public String getAddress(){
        return address;
    }
    
    /**
     * postalCode getter. 
     * @return postal code of customer
     */
    public String getPostalCode(){
        return postalCode;
    }
    
    /**
     * phone getter. 
     * @return phone number for customer
     */
    public String getPhone(){
        return phone;
    }
    
    /**
     * createDate getter. 
     * @return creation date for customer record
     */
    public LocalDateTime getCreateDate(){
        return createDate;
    }
    
    /**
     * createdBy getter. 
     * @return method that created the customer record.
     */
    public String getCreatedBy(){
        return createdBy;
    }
    
    /**
     * lastUpdated getter. 
     * @return last updated datetime for customer record
     */
    public LocalDateTime getLastUpdated(){
        return lastUpdated;
    }
    
    /**
     * lastUpdatedBy getter. 
     * @return method that last updated the customer record
     */
    public String getLastUpdatedBy(){
        return lastUpdatedBy;
    }
    
    /**
     * divisionID getter. 
     * @return division ID for the customer
     */
    public Integer getDivisionID(){
        return divisionID;
    }
    
    // setters

    /**
     * customerName setter. 
     * @param name name of customer
     */
    public void setCustomerName(String name){
        this.customerName = name;
    }
    
    /**
     * address getter. 
     * @param address address of customer
     */
    public void setAddress(String address){
        this.address = address;
    }
    
    /**
     * postalCode setter. 
     * @param postalCode postal code for customer
     */
    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }
    
    /**
     * phone setter. 
     * @param phone phone number for customer
     */
    public void setPhone(String phone){
        this.phone = phone;
    }  
    
    /**
     * lastUpdated setter. Uses current datetime of action.
     */
    public void setLastUpdated(){
        this.lastUpdated = LocalDateTime.now();
    }
    
    /**
     * divisionID setter. 
     * @param divisionID divisionID for customer.
     */
    public void setDivisionID(Integer divisionID){
        this.divisionID = divisionID;
    }
    
}
