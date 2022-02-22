/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import DTOs.Quartet;
import DTOs.appointmentDTO;
import DTOs.contactDTO;
import DTOs.countryDTO;
import DTOs.customerDTO;
import DTOs.divisionDTO;
import DTOs.userDTO;
import Models.Appointment;
import Models.Customer;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 * Factory class to consolidate MySql specific query functions.
 * @author eric
 */
public class MySQL_Service_Factory extends Service_Factory_Interface{
    
//  --- User Services ---

    /**
     * Calls MySQL service function of same name. 
     * @param user_name name of user
     * @return DTO for user record
     */
    public userDTO getUserByName(String user_name) {
        return Services.MySql.UserServices.getUserByName(user_name);
    }
    
//    --- Customer Roster Services ---

    /**
     * Calls MySQL service function of same name.
     * @return ObservableList of customerDTO objects, generated based on MySQL customer records
     */
    public ObservableList<customerDTO> loadAllCustomers() {
        return Services.MySql.CustomerRosterServices.loadAllCustomers();
    }
    
//    --- Country Roster Services ---

    /**
     * Calls MySQL service function of same name.
     * @return TreeMap of country objects, generated based on MySQL country records
     */
    public TreeMap<Integer, countryDTO> loadAllCountries() {
        return Services.MySql.CountryRosterServices.loadAllCountries();
    }
    
//    --- Division Roster Services ---

    /**
     * Calls MySQL service function of same name.
     * @return TreeMap of divisionDTO objects, generated based on MySQL division records
     */
    public TreeMap<Integer, divisionDTO> loadAllDivisions() {
        return Services.MySql.DivisionRosterServices.loadAllDivisions();
    }
    
//    --- Customer Services ---

    /**
     * Calls MySQL service function of same name.
     * @param ID ID of customer
     * @return customerDTO object generated based on MySQL customer record for specified ID
     */
    public customerDTO getCustomerByID(Integer ID) {
        return Services.MySql.CustomerServices.getCustomerByID(ID);
    }
    
    /**
     * Calls MySQL service function of same name.
     * @param customer customer object
     */
    public void saveCustomer(Customer customer) {
        Services.MySql.CustomerServices.saveCustomer(customer);
    }
    
    /**
     * Calls MySQL service function of same name.
     * @param Customer_Name name of customer to be saved
     * @param Address address of customer to be saved
     * @param Postal_Code postal code of customer to be saved
     * @param Phone phone number of customer to be saved
     * @param Division_ID division ID of customer to be saved
     */
    public void saveCustomer(
            String Customer_Name,
            String Address,
            String Postal_Code,
            String Phone,
            Integer Division_ID) {
        Services.MySql.CustomerServices.saveCustomer(Customer_Name, Address, Postal_Code, Phone, Division_ID);
    }
    
    /**
     * Calls MySQL service function of same name.
     * @param Customer_Name name of customer to be saved
     * @param Address address of customer to be saved
     * @param Postal_Code postal code of customer to be saved
     * @param Phone phone number of customer to be saved
     * @param Division_ID division ID of customer to be saved
     * @param Customer_ID Customer ID of customer to be saved
     */
    public void saveCustomer(
            String Customer_Name,
            String Address,
            String Postal_Code,
            String Phone,
            Integer Division_ID,
            Integer Customer_ID) {
        Services.MySql.CustomerServices.saveCustomer(Customer_Name, Address, Postal_Code, Phone, Division_ID, Customer_ID);
    }
    
    /**
     * Calls MySQL service function of same name.
     * @param ID ID of customer to be deleted
     */
    @Override
    public void deleteCustomer(Integer ID) {
        try {
            Services.MySql.CustomerServices.deleteCustomer(ID);
        } catch (SQLException ex) {
            Logger.getLogger(MySQL_Service_Factory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    --- Appointment Services ---

    /**
     * Calls MySQL service function of same name.
     * @param candidateAppoingment modified appointment object
     * @param appointment original appointment object
     * @throws Exception
     */
    public void saveAppointment(Appointment candidateAppoingment, Appointment appointment) throws Exception {
        try {
            Services.MySql.AppointmentServices.saveAppointment(candidateAppoingment, appointment);
        } catch (Exception ex) {
            throw ex;
        }
    }
    

    /**
     * Calls MySQL service function of same name.
     * @param ID ID of appointment to be deleted
     */
    
    public void deleteAppointment(Integer ID) {
        Services.MySql.AppointmentServices.deleteAppointment(ID);
    }
    
    
//    --- Apppointment Roster Services ---
    
    /**
     * Calls MySQL service function of same name.
     * @return ObservableList of appointmentDTOs, created from MySQL records
     */
    public ObservableList<appointmentDTO> getAllAppointments() {
        return Services.MySql.AppointmentRosterServices.getAllAppointments();
    }
    
    /**
     * Calls MySQL service function of same name.
     * @param date date within week to be returned
     * @return ObservableList of appointmentDTOs, created from MySQL records
     */
    public ObservableList<appointmentDTO> getWeeklyAppointments(LocalDate date) {
        return Services.MySql.AppointmentRosterServices.getWeeklyAppointments(date);
    } 
    
    /**
     * Calls MySQL service function of same name. Deletes all appointments for given customer
     * @param ID ID of customer to delete all appointments for
     * @throws SQLException
     */
    public void deleteCustomerAppointment(Integer ID) throws SQLException {
        Services.MySql.AppointmentRosterServices.deleteCustomerAppointment(ID);
    }
    
    /**
     * Calls MySQL service function of same name.
     * @param year year to be searched for appointments
     * @param month month of year to be searched for appointments
     * @return ObservableList of appointmentDTO objects, created from MySQL records
     * for the month specified
     */
    public ObservableList<appointmentDTO> getMonthlyAppointments(Integer year, Integer month) {
        return Services.MySql.AppointmentRosterServices.getMonthlyAppointments(year, month);
    } 
    
    /**
     * Calls MySQL service function of same name.
     * @return ObservableList of appointmentDTOs for appointments found within 15 minutes of current datetime.
     */
    public ObservableList<appointmentDTO> getFifteenMinuteCheck() {
        return Services.MySql.AppointmentRosterServices.getFifteenMinuteCheck();
    }
    
    /**
     * Calls MySQL service function of same name.
     * @param contactID ID of contact for which to get all appointments
     * @return ObservableList of appointmentDTOs, created from MySQL appointment records for specified contact ID
     */
    public ObservableList<appointmentDTO> getAppointmentsByContact(String contactID) {
        return Services.MySql.AppointmentRosterServices.getAppointmentsByContact(contactID);
    }
    
    /**
     * Calls MySQL service function of same name.
     * @return ObservableList of Quartets, containing data for each row in MonthlyAppointmentSummary report
     */
    public ObservableList<Quartet> getAppointmentCountTypeMonth() {
        return Services.MySql.AppointmentRosterServices.getAppointmentCountTypeMonth();
    }
    
    /**
     * Calls MySQL service function of same name.
     * @param customerID ID of customer to get all appointments for
     * @return ObservableList of appointmentDTOs for all appointments scheduled for specified customer.
     */
    public ObservableList<appointmentDTO> getAppointmentsByCustomer(String customerID) {
        return Services.MySql.AppointmentRosterServices.getAppointmentsByCustomer(customerID);
    }
    
//    --- Contact Roster Services ---

    /**
     * Calls MySQL service function of same name.
     * @return TreeMap of contactDTOs for all contacts in database
     */
    public TreeMap<Integer, contactDTO> getContacts() {
        return Services.MySql.ContactRosterServices.getContacts();
    }
    
//    --- Users Roster Services ---

    /**
     * Calls MySQL service function of same name.
     * @return ObservableList of userDTOs for all users in DB
     */
    public ObservableList<userDTO> loadAllUsers() {
        return Services.MySql.UserRosterServices.loadAllUsers();
    }
}
