/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DTOs.userDTO;
import c195.C195;
import java.time.LocalDateTime;

/**
 * User Object. 
 * @author eric
 */
public class User {
    
    private String name;
    private String password;
    private String lastUpdatedBy;
    private String createdBy;
    private Integer userID;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdated;
    
    /**
     * Constructor. 
     * @param name
     */
    public User(String name) {
       userDTO data = C195.serviceFactory.getUserByName(name);
       this.name = data.getUserName();
       this.password = data.getPassword();
       this.lastUpdatedBy = data.getLastUpdatedBy();
       this.createdBy = data.getCreatedBy();
       this.userID = data.getUserID();
       this.createDate = data.getCreateDate();
       this.lastUpdated = data.getLastUpdated();
    }
    
    /**
     * Constructor. 
     * @param user userDTO to create user object from
     */
    public User(userDTO user) {
        this.userID = user.getUserID();
       this.name = user.getUserName();
    }
    
    /**
     * userID getter.
     * @return ID of user
     */
    public Integer getUserID() {
        return this.userID;
    }
    
    /**
     * password getter. 
     * @return password of user
     */
    public String getPassword() {
        return this.password;
    }
    
    
    /**
     * Custom toString method. 
     * @return string representation of userID
     */
    @Override
    public String toString() {
        return this.userID.toString();
    }
    
}
