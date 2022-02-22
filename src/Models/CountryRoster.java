/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import c195.C195;
import java.util.TreeMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * CountryRoster Object
 * @author eric
 */
public class CountryRoster {
    
    private static TreeMap<Integer, Country> countries = new TreeMap<Integer, Country>();
    
    /**
     * Constructor, contains Lambda. Lambda passed to a 
     * forEach loop, dictating the action to be taken on each item encountered during the iteration.
     */
    public CountryRoster(){
        C195.serviceFactory.loadAllCountries().forEach((k, v) -> {
            countries.put(k, new Country(v));
        });
    }

    /**
     * Get country object from countries class variable by ID. 
     * @param ID ID of country to return
     * @return Country that corresponds to ID
     */
    public Country getCountryByID(Integer ID) {
        return this.countries.get(ID);
    }
    
    /**
     * Get ObservableList of countries from countries class variable. 
     * @return ObservableList of Country Objects
     */
    public ObservableList<Country> getCountries() {
        return FXCollections.observableArrayList(this.countries.values());
    }
}
