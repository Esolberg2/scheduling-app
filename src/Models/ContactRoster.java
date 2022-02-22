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
 * ContactRoster Object
 * @author eric
 */
public class ContactRoster {
    
    private static TreeMap<Integer, Contact> contacts = new TreeMap<Integer, Contact>();
    
    /**
     * Constructor, contains Lambda. Lambda passed to a 
     * forEach loop, dictating the action to be taken on each item encountered during the iteration.
     */
    public ContactRoster() {
        C195.serviceFactory.getContacts().forEach((k, v) -> {
            contacts.put(k, new Contact(v));
        });
    }

    /**
     * Contacts list getter. 
     * @return ObservableList of contacts
     */
    public ObservableList<Contact> getContacts() {
        return FXCollections.observableArrayList(this.contacts.values());
    }
    
}
