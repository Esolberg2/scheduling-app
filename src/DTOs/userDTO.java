/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

/**
 * 
 * @author eric
 */
import java.time.LocalDateTime;

/**
 * Transfer Object for user data. 
 * @author eric
 */
public class userDTO {
    
    private int userID;
    private String userName;
    private String password;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdated;
    private String lastUpdatedBy;
    
    /**
     * Constructor for creating userDTO. 
     * @param userID
     * @param userName
     * @param password
     * @param createDate
     * @param createdBy
     * @param lastUpdated
     * @param lastUpdatedBy
     */
    public userDTO(
            int userID,
            String userName,
            String password,
            LocalDateTime createDate,
            String createdBy,
            LocalDateTime lastUpdated,
            String lastUpdatedBy){
        
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
        
    }
    
    /**
     * Constructor for creating partially filled userDTO. 
     * @param userID ID of user
     * @param userName name of user
     */
    public userDTO(
            int userID,
            String userName){
        
        this.userID = userID;
        this.userName = userName;
    }
    
    /**
     * userID getter. 
     * @return ID of user
     */
    public int getUserID(){
        return userID;
    }
    
    /**
     * userName getter. 
     * @return name of user
     */
    public String getUserName(){
        return userName;
    }
    
    /**
     * password getter.
     * @return password of user
     */
    public String getPassword(){
        return password;
    }
    
    /**
     * createDate getter. 
     * @return datetime user record was created
     */
    public LocalDateTime getCreateDate(){
        return createDate;
    }
    
    /**
     * createdBy getter. 
     * @return method that created user record
     */
    public String getCreatedBy(){
        return createdBy;
    }
    
    /**
     * lastUpdated getter. 
     * @return datetime user record was last updated
     */
    public LocalDateTime getLastUpdated(){
        return lastUpdated;
    }
    
    /**
     * lastUpdatedBy getter. 
     * @return method used for last update to user record
     */
    public String getLastUpdatedBy(){
        return lastUpdatedBy;
    }
    
}