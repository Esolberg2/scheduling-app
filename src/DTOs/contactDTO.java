/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

/**
 * Transfer Object for contact data
 * @author eric
 */
public class contactDTO {
    private Integer contactID;
    private String contactName;
    private String contactEmail;
    
    /**
     *
     * @param contactID
     * @param contactName
     * @param contactEmail
     */
    public contactDTO(Integer contactID, String contactName, String contactEmail) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    /**
     * contactID getter. 
     * @return ID of contact
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
     * @return email for contact.
     */
    public String getContactEmail() {
        return contactEmail;
    }
    
}

