/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import c195.C195;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * UserRoster Objects. 
 * @author eric
 */
public class UserRoster {
    
    private static ObservableList<User> users = FXCollections.observableArrayList();;
    
    /**
     * Constructor, contains Lambda. Lambda passed to a 
     * forEach loop, dictating the action to be taken on each item encountered during the iteration.
     */
    public UserRoster() {
        C195.serviceFactory.loadAllUsers().forEach((c) -> {
            users.add(new User(c));
        });
    }
    
    /**
     * Get users from users class variable. 
     * @return ObservableList of User objects
     */
    public ObservableList<User> getUsers() {
        return this.users;
    }
   
}
