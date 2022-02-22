/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DTOs.divisionDTO;
import c195.C195;
import java.util.TreeMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Division Object. 
 * @author eric
 */

public class DivisionRoster {
    private static TreeMap<Integer, Division> divisions = new TreeMap<Integer, Division>();
    
    /**
     * Constructor, contains Lambda. Lambda passed to a 
     * forEach loop, dictating the action to be taken on each item encountered during the iteration.
     */
    public DivisionRoster(){
        TreeMap<Integer, divisionDTO> temp = C195.serviceFactory.loadAllDivisions();
        
        temp.forEach((k, v) -> {
            divisions.put(k, new Division(v));
        });
    }

    /**
     * Get division object from divisions class variable by ID. 
     * @param ID ID of division to be retrieved
     * @return division object associated with ID
     */
    public Division getDivisionByID(Integer ID) {
        return this.divisions.get(ID);
    }
    
    /**
     * Get country ID associated with a division, specified by a division ID
     * @param ID ID of division
     * @return ID of country to which the provided divisionID belongs
     */
    public Country getCountryByDivisionByID(Integer ID) {
        Division div = this.divisions.get(ID);
        return C195.countries.getCountryByID(Integer.valueOf(div.getCountry_ID()));
    }
    
    /**
     * Get all divisions from divisions class variable. 
     * @return ObservableList of Division objects
     */
    public ObservableList<Division> getDivisions() {
        return FXCollections.observableArrayList(this.divisions.values());
    }
}
