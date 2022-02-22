/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DTOs.contactDTO;

/**
 * Contact Object
 * @author eric
 */
public class Contact {
    
    private Integer contactID;
    private String contactName;
    private String contactEmail;
    
    /**
     * Constructor
     * @param data contactDTO to create Contact object from. 
     */
    public Contact(contactDTO data) {
        this.contactID = data.getContactID();
        this.contactEmail = data.getContactEmail();
        this.contactName = data.getContactName();
                
    }

    /**
     * contactID getter. 
     * @return ID for contact
     */
    public Integer getContactID() {
        return contactID;
    }

    /**
     * contactName getter. 
     * @return name of contact
     */
    public String getContactName() {
        return contactName;
    }   

    /**
     * contactEmail getter. 
     * @return email for contact
     */
    public String getContactEmail() {
        return contactEmail;
    }

    
     /**
     * Custom toString method for Contact object. 
     * @return String representation of contact ID
     */
    @Override
    public String toString() {
        return this.contactID.toString();
    }
}
