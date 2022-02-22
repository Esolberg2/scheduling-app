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
 * CustomerRoster Object
 * @author eric
 */
public class CustomerRoster {
    
    public static ObservableList<Customer> customers = FXCollections.observableArrayList();;
    
    /**
     * Constructor, contains Lambda. Lambda passed to a 
     * forEach loop, dictating the action to be taken on each item encountered during the iteration.
     */
    public CustomerRoster() {
        C195.serviceFactory.loadAllCustomers().forEach((c) -> {
            customers.add(new Customer(c));
        });
    }
            
    /**
     * Reloads class customers variable from database, contains Lambda. Lambda passed to a 
     * forEach loop, dictating the action to be taken on each item encountered during the iteration.
     */
    public void refreshRoster() {
        this.customers.clear();
        C195.serviceFactory.loadAllCustomers().forEach((customer) -> {
            customers.add(new Customer(customer));
        });
    }
    
    /**
     * customers getter. 
     * @return reference to customers class variable
     */
    public ObservableList<Customer> getCustomers() {
        return this.customers;
    }
    
    /**
     * Remove customer object from customers class variable. 
     * @param customer customer object to remove
     */
    public void removeCustomer(Customer customer) {
        try {
            C195.appointmentRoster.removeCustomerAppointment(customer.getCustomerID());
            C195.serviceFactory.deleteCustomer(customer.getCustomerID());
            this.customers.remove(customer);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
   
}

