/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DTOs.Quartet;
import c195.C195;
import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Function;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * AppointmentRoster object
 * @author eric
 */
public class AppointmentRoster {
    
    public static Runnable lastRun;
    public static ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    
    public void refreshAppointments() {
        if (Objects.isNull(lastRun)) {
            
        } else {
            this.appointments.clear();
            lastRun.run(); 
        }
    }
    
    /**
     * Set class variable for appointments to contain all appointments, contains Lambda. Lambda passed to a 
     * forEach loop, dictating the action to be taken on each item encountered during the iteration.
     */
    public void getAllAppointments() {
        this.appointments.clear();
        
        lastRun = () -> {
            C195.serviceFactory.getAllAppointments().forEach((customerDTO) -> {
            this.appointments.add(new Appointment(customerDTO));
            });
        };
        lastRun.run();
    }
    
    /**
     * get count of each appointment type by month
     * @return ObservableList of Quartets, containing counts of each appointment type by month
     */
    public ObservableList<Quartet> getAppointmentCountTypeMonth() {
        return C195.serviceFactory.getAppointmentCountTypeMonth();
    }
    
    /**
     * Calls service factory method to retrieved appointments within 15 mintues, contains Lambda. Lambda passed to a 
     * forEach loop, dictating the action to be taken on each item encountered during the iteration.
     * @return ObservableList of appointments within 15 minutes
     */
    public ObservableList<Appointment> getAppointmentsInFifteen() {
        ObservableList<Appointment> upcomingAppointments = FXCollections.observableArrayList();
        
        C195.serviceFactory.getFifteenMinuteCheck().forEach((customerDTO) -> {
            upcomingAppointments.add(new Appointment(customerDTO));
        });
        return upcomingAppointments;
    }
    
    /**
     * Gets all appointments for a given customerID, contains Lambda. Lambda passed to a 
     * forEach loop, dictating the action to be taken on each item encountered during the iteration.
     * @param customerID ID of customer to get appointments for
     * @return ObservableList of all appointments for a customer
     */
    public ObservableList<Appointment> getAppointmentsByCustomer(String customerID) {
        ObservableList<Appointment> upcomingAppointments = FXCollections.observableArrayList();
        
        C195.serviceFactory.getAppointmentsByCustomer(customerID).forEach((customerDTO) -> {
            upcomingAppointments.add(new Appointment(customerDTO));
        });
        return upcomingAppointments;
    }
    
    /**
     * Get all appointments assigned to a contact, contains Lambda. Lambda passed to a 
     * forEach loop, dictating the action to be taken on each item encountered during the iteration.
     * @param contactID ID of contact to get appointments for
     * @return ObservableList of all appointments assigned to the provided contact ID
     */
    public ObservableList<Appointment> getAppointmentsByContact(String contactID) {
        ObservableList<Appointment> upcomingAppointments = FXCollections.observableArrayList();
        
        C195.serviceFactory.getAppointmentsByContact(contactID).forEach((customerDTO) -> {
            upcomingAppointments.add(new Appointment(customerDTO));
        });
        return upcomingAppointments;
    }
    
    /**
     * Set class variable for appointments to contain appointments during specified month, contains Lambda. Lambda passed to a 
     * forEach loop, dictating the action to be taken on each item encountered during the iteration.
     * @param year year for appointments
     * @param month month of year for appointments
     */
    public void getMonthlyAppointments(Integer year, Integer month) {
        System.out.println("*** Monthly Appointments ***");
        this.appointments.clear();
        
        lastRun = () -> {
            C195.serviceFactory.getMonthlyAppointments(year, month).forEach((customerDTO) -> {
                this.appointments.add(new Appointment(customerDTO));
            });
        };
        
        lastRun.run();
    }
    
    /**
     * Set class variable for appointments to contain appointments during specified week, contains Lambda. Lambda passed to a 
     * forEach loop, dictating the action to be taken on each item encountered during the iteration.
     * @param date date that falls within week to be searched
     */
    public void getWeeklyAppointments(LocalDate date) {
        this.appointments.clear();
        
        lastRun = () -> {
        C195.serviceFactory.getWeeklyAppointments(date).forEach((customerDTO) -> {
            this.appointments.add(new Appointment(customerDTO));
         });
        };
        
        lastRun.run();
    }
    
    /**
     * Add appointment to class appointments variable. 
     * @param appointment new appointment object to be added
     */
    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }
    
    /**
     * Remove appointment from class appointments variable. 
     * @param appointment appointment object to remove
     */
    public void removeAppointment(Appointment appointment) {
        try {
            C195.serviceFactory.deleteAppointment(appointment.getAppointment_ID());
            this.appointments.remove(appointment);
        } catch(Exception e) {
        }
    }
    
    /**
     * Remove all appointments for a customer, contains Lambda. Lambda passed to removeIf()
     * to determine if the element within the list should be removed based on the criteria 
     * set by the lambda.
     * @param ID ID of customer to delete all appointments for
     */
    public void removeCustomerAppointment(Integer ID) {
        try {
            C195.serviceFactory.deleteCustomerAppointment(ID);
            
            this.appointments.removeIf((Appointment appointment) -> appointment.getCustomer_ID() == ID);
            
        } catch(Exception e) {
        }
    }
    
}
