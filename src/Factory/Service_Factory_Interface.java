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
import java.util.TreeMap;
import javafx.collections.ObservableList;

/**
 * Abstract class to define contents of all service factories. 
 * @author eric
 */
public abstract class Service_Factory_Interface {
    
    /**
     * Top level function for selecting which Service Factory file will be used at runtime.
     * @param db database type (MySQL, MsSQL, Mongo, etc)
     * @return Service Factory object
     */
    public static Service_Factory_Interface getFactory(String db){
        switch (db) {
            case ("MySQL"): 
                return new MySQL_Service_Factory();     
        }
        return null;
    }
    
    // --- User Services ---

    /**
     *
     * @param username
     * @return
     */
    public abstract userDTO getUserByName(String username);
    
    // --- Customer Roster Services ---

    /**
     *
     * @return
     */
    public abstract ObservableList<customerDTO> loadAllCustomers();
    
    // --- Country Roster Services ---

    /**
     *
     * @return
     */
    public abstract TreeMap<Integer, countryDTO> loadAllCountries();
    
    // --- Division Roster Services ---

    /**
     *
     * @return
     */
    public abstract TreeMap<Integer, divisionDTO> loadAllDivisions();
    
    // --- Customer Services ---

    /**
     *
     * @param ID
     * @return
     */
    public abstract customerDTO getCustomerByID(Integer ID);

    /**
     *
     * @param customer
     */
    public abstract void saveCustomer(Customer customer);

    /**
     *
     * @param Customer_Name
     * @param Address
     * @param Postal_Code
     * @param Phone
     * @param Division_ID
     */
    public abstract void saveCustomer(
            String Customer_Name,
            String Address,
            String Postal_Code,
            String Phone,
            Integer Division_ID);

    /**
     *
     * @param Customer_Name
     * @param Address
     * @param Postal_Code
     * @param Phone
     * @param Division_ID
     * @param Customer_ID
     */
    public abstract void saveCustomer(
            String Customer_Name,
            String Address,
            String Postal_Code,
            String Phone,
            Integer Division_ID,
            Integer Customer_ID);
    
    /**
     *
     * @param ID
     */
    public abstract void deleteCustomer(Integer ID);
    
    // --- Appointment Services ---

    /**
     *
     * @param candidateAppoingment
     * @param appointment
     * @throws Exception
     */
    public abstract void saveAppointment(Appointment candidateAppoingment, Appointment appointment) throws Exception;
    
    /**
     *
     * @param ID
     */
    public abstract void deleteAppointment(Integer ID);
    
    // --- Appointment Roster Services ---
    
    /**
     *
     */
    public abstract ObservableList<appointmentDTO> getAllAppointments();

    
    /**
     *
     * @param date
     * @return
     */
    public abstract ObservableList<appointmentDTO> getWeeklyAppointments(LocalDate date);

    /**
     *
     * @param year
     * @param month
     * @return
     */
    public abstract ObservableList<appointmentDTO> getMonthlyAppointments(Integer year, Integer month);

    /**
     *
     * @param ID
     * @throws SQLException
     */
    public abstract void deleteCustomerAppointment(Integer ID) throws SQLException;

    /**
     *
     * @return
     */
    public abstract ObservableList<appointmentDTO> getFifteenMinuteCheck();

    /**
     *
     * @param contactID
     * @return
     */
    public abstract ObservableList<appointmentDTO> getAppointmentsByContact(String contactID);

    /**
     *
     * @return
     */
    public abstract ObservableList<Quartet> getAppointmentCountTypeMonth();

    /**
     *
     * @param customerID
     * @return
     */
    public abstract ObservableList<appointmentDTO> getAppointmentsByCustomer(String customerID);
    
    // --- Contacts Roster Services ---

    /**
     *
     * @return
     */
    
    public abstract TreeMap<Integer, contactDTO> getContacts();
    
    // --- Users Roster Services ---

    /**
     *
     * @return
     */
    public abstract ObservableList<userDTO> loadAllUsers();
}

