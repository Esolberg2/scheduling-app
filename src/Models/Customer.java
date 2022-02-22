/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DTOs.customerDTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Customer Object
 * @author eric
 */
public class Customer{
    
    private String address;
    private LocalDateTime createDate;
    private String createdBy;
    private Integer customerID;
    private String customerName;
    private Integer divisionID;
    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;
    private String phoneNumber;
    private String postalCode;
    
    /**
     * Constructor using individual values.
     * @param customerName
     * @param address
     * @param phoneNumber
     * @param postalCode
     * @param createDate
     * @param createdBy
     * @param lastUpdated
     * @param lastUpdatedBy
     * @param divisionID
     */
    public Customer(
            String customerName,
            String address,
            String phoneNumber,
            String postalCode,
            LocalDateTime createDate,
            String createdBy,
            LocalDateTime lastUpdated,
            String lastUpdatedBy,
            Integer divisionID
        ) {
            this.customerName = customerName;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.postalCode = postalCode;
            this.createDate = createDate;
            this.createdBy = createdBy;
            this.lastUpdated = lastUpdated;
            this.lastUpdatedBy = lastUpdatedBy;
            this.divisionID = divisionID;
        }
    
    /**
     * Constructor using customerDTO. 
     * @param data customerDTO 
     */
    public Customer(customerDTO data) {
            this.customerID = data.getCustomerID();
            this.customerName = data.getCustomerName();
            this.address = data.getAddress();
            this.phoneNumber = data.getPhone();
            this.postalCode = data.getPostalCode();
            this.createDate = data.getCreateDate();
            this.createdBy = data.getCreatedBy();
            this.lastUpdated = data.getLastUpdated();
            this.lastUpdatedBy = data.getLastUpdatedBy();
            this.divisionID = data.getDivisionID();
        }
    
    
    /**
     * Custom toString method. 
     * @return String representation of customerID
     */
    @Override
    public String toString() {
        return this.customerID.toString();
    }
    
    /**
     * customerName setter. 
     * @param name new name for customer
     */
    public void setCustomerName(String name) {
        this.customerName = name;
    }
    
    /**
     * address setter. 
     * @param address new address for customer
     */
    public void setCustomerAddress(String address) {
        this.address = address;
    }
    
    /**
     * postalCode setter. 
     * @param code new postal code for customer
     */
    public void setCustomerPostalCode(String code) {
        this.postalCode = code;
    }
    
    /**
     * phoneNumber setter. 
     * @param phone new phone number for customer
     */
    public void setCustomerPhone(String phone) {
        this.phoneNumber = phone;
    }
    
    /**
     * lastUpdated setter. 
     */
    public void setLastupdated() {
        this.lastUpdated = LocalDateTime.now();
    }
        
    /**
     * divisionID setter. 
     * @param divisionID new divisionID for customer
     */
    public void setDivisionID(Integer divisionID) {
        this.divisionID = divisionID;
    }
    
    
    // getters 

    /**
     * customerID getter. 
     * @return ID for customer
     */
    
    public Integer getCustomerID() {
        return this.customerID;
    }
    
    /**
     * customerName getter. 
     * @return name of customer
     */
    public String getCustomerName() {
        return this.customerName;
    }
    
    /**
     * address getter. 
     * @return address of customer
     */
    public String getCustomerAddress() {
        return this.address;
    }
    
    /**
     * postalCode getter. 
     * @return postal code for customer
     */
    public String getCustomerPostalCode() {
        return this.postalCode;
    }
    
    /**
     * phoneNumber getter. 
     * @return phone number for customer
     */
    public String getCustomerPhone() {
        return this.phoneNumber;
    }
    
    /**
     * createDate getter. 
     * @return datetime customer record was created
     */
    public LocalDateTime getCreateDate() {
        return this.createDate;
    }
    
    /**
     * formatted createDate getter. 
     * @return ISO_DATE formatted createDate
     */
    public String getCreateDateFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a");
        return formatter.format(this.createDate);
    }
    
    /**
     * lastUpdated getter. 
     * @return datetime customer record was last updated
     */
    public LocalDateTime getLastupdated() {
        return this.lastUpdated;
    }
    
    /**
     * formatted lastUpdated getter. 
     * @return ISO_DATE formatted lastUpdated
     */
    public String getLastupdatedFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a");
        return formatter.format(this.lastUpdated);
    }
    
    /**
     * lastUpdatedBy getter. 
     * @return method that last updated the customer record
     */
    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }
        
    /**
     * divisionID getter. 
     * @return division ID for customer
     */
    public Integer getDivisionID() {
        return this.divisionID;
    }
    
    /**
     * createdBy getter. 
     * @return method that created the customer record
     */
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    /**
     * lastUpdated getter. 
     * @return datetime the customer record was last updated
     */
    public LocalDateTime getLastUpdated() {
        return this.lastUpdated;
    }
    
}
