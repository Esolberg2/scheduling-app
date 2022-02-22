/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DTOs.appointmentDTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Appointment Object
 * @author eric
 */
public class Appointment {
    private String title;
    private Integer appointment_ID;
    private String description;
    private String location;
    private Integer contact;
    private String type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer customer_ID;
    private Integer user_ID;
        
    /**
     * Empty Constructor. 
     */
    public Appointment() {
        
    }
    
    /**
     * Constructor using appointmentDTO
     * @param data appointmentDTO
     */
    public Appointment(appointmentDTO data) {
        this.title = data.getTitle();
        this.appointment_ID = data.getAppointment_ID();
        this.description = data.getDescription();
        this.location = data.getLocation();
        this.contact = data.getContact_ID();
        this.type = data.getType();
        this.startTime = data.getStart();
        this.endTime = data.getEnd();
        this.customer_ID = data.getCustomer_ID();
        this.user_ID = data.getUser_ID();
    }
    
    /**
     * Update existing appointment object using new object
     * @param appointment new appointment object
     */
    public void Update(Appointment appointment) {
        this.title = appointment.getTitle();
        this.description = appointment.getDescription();
        this.location = appointment.getLocation();
        this.contact = appointment.getContact();
        this.type = appointment.getType();
        this.startTime = appointment.getStartTime();
        this.endTime = appointment.getEndTime();
        this.customer_ID = appointment.getCustomer_ID();
        this.user_ID = appointment.getUser_ID();
    }

    /**
     * title getter
     * @return appointment title
     */
    public String getTitle() {
        return title;
    }

    /**
     * user_ID getter. 
     * @return ID of user that created the appointment.
     */
    public Integer getUser_ID() {
        return user_ID;
    }

    /**
     * user_ID setter. 
     * @param user_ID new user_ID
     */
    public void setUser_ID(Integer user_ID) {
        this.user_ID = user_ID;
    }

    /**
     * appointment title setter. 
     * @param title new title for appointment
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * appointment_ID getter. 
     * @return ID of appointment
     */
    public Integer getAppointment_ID() {
        return appointment_ID;
    }

    /**
     * appointmentID setter. 
     * @param appointment_ID to set
     */
    public void setAppointment_ID(Integer appointment_ID) {
        this.appointment_ID = appointment_ID;
    }

    /**
     * description getter. 
     * @return description for appointment
     */
    public String getDescription() {
        return description;
    }

    /**
     * description setter. 
     * @param description new description for appointment
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * appointment location getter. 
     * @return location for appointment
     */
    public String getLocation() {
        return location;
    }

    /**
     * location setter. 
     * @param location new location for appointment
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * contact getter.
     * @return contact assigned to appointment
     */
    public Integer getContact() {
        return contact;
    }

    /**
     * contact setter. 
     * @param contact new contact for appointment
     */
    public void setContact(Integer contact) {
        this.contact = contact;
    }

    /**
     * type getter. 
     * @return type assigned to appointment
     */
    public String getType() {
        return type;
    }

    /**
     * type setter. 
     * @param type new type for appointment
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * startTime getter.
     * @return startTime assigned to appointment.
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }
    
    /**
     * formatted startTime getter. 
     * @return ISO_DATE formatted startTime
     */
    public String getStartTimeFormatted() {
//        return startTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a");
        return formatter.format(startTime);
    }

    /**
     * startTime setter. 
     * @param startTime new startTime for appointment
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    
    /**
     * endTime getter. 
     * @return appointment end datetime
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }
    
    /**
     * formatted endTime getter. 
     * @return ISO_DATE formatted endTime
     */
    public String getEndTimeFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a");
        return formatter.format(endTime);
    }

    /**
     * endTime setter.
     * @param endTime new end datetime for appointment
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * customer_ID getter. 
     * @return ID of customer assigned to appointment
     */
    public Integer getCustomer_ID() {
        return customer_ID;
    }

    /**
     * customer_ID setter. 
     * @param customer_ID new customer ID to assign to appointment
     */
    public void setCustomer_ID(Integer customer_ID) {
        this.customer_ID = customer_ID;
    }
    
}
