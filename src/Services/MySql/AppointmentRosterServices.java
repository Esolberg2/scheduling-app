/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.MySql;

import DTOs.Quartet;
import DTOs.appointmentDTO;
import Services.TimeConverter;
import c195.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * AppointmentRosterServices Class. 
 * @author eric
 */

public class AppointmentRosterServices {
    
     /**
     * Get all appointments from database. 
     * @return ObservableList of all appointmentDTOs
     */
    public static ObservableList<appointmentDTO> getAllAppointments() {
        
        ObservableList<appointmentDTO> appointments = FXCollections.observableArrayList();
        try {
                String sql =  "select * from appointments";

                PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
                
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    appointments.add(new appointmentDTO(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        TimeConverter.toLocal(rs.getTimestamp("Start").toLocalDateTime()),
                        TimeConverter.toLocal(rs.getTimestamp("End").toLocalDateTime()),
                        TimeConverter.toLocal(rs.getTimestamp("Create_Date").toLocalDateTime()),
                        rs.getString("Created_By"),
                        TimeConverter.toLocal(rs.getTimestamp("Last_Update").toLocalDateTime()),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID")
                    ));
                }
                DBConnection.closeConnection();
            } catch(SQLException throwables) {
                throwables.printStackTrace();
            }
        return appointments;
    }
    
    /**
     * Get appointment records from DB for a specific customer. 
     * @param customerID ID of customer to retrieve appointments for
     * @return ObservableList of appointmentDTOs for the specified customer
     */
    public static ObservableList<appointmentDTO> getAppointmentsByCustomer(String customerID) {
        
        ObservableList<appointmentDTO> appointments = FXCollections.observableArrayList();
        try {
                String sql =  String.format("select * from appointments where "
                        + "Customer_ID ='%s' order by Start asc;", customerID);
                
                System.out.println(sql);
                PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
                
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    appointments.add(new appointmentDTO(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        TimeConverter.toLocal(rs.getTimestamp("Start").toLocalDateTime()),
                        TimeConverter.toLocal(rs.getTimestamp("End").toLocalDateTime()),
                        TimeConverter.toLocal(rs.getTimestamp("Create_Date").toLocalDateTime()),
                        rs.getString("Created_By"),
                        TimeConverter.toLocal(rs.getTimestamp("Last_Update").toLocalDateTime()),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID")
                    ));
                }
                DBConnection.closeConnection();
            } catch(SQLException throwables) {
                throwables.printStackTrace();
            }
        return appointments;
    }
    
    /**
     * Get count of appointments by type, by month from database. 
     * @return ObservableList of Quartet objects, containing data for MonthlyAppointmentSummary report
     */
    public static ObservableList<Quartet> getAppointmentCountTypeMonth() {
        
        ObservableList<Quartet> data = FXCollections.observableArrayList();
        
        try {
            String sql = "select "
                    + "Max(YEAR(Start)) as Year, "
                    + "Max(MONTH(Start)) as Month, "
                    + "Max(Type) as Type, "
                    + "count(Appointment_ID) as Count "
                    + "from appointments "
                    + "GROUP BY YEAR(Start), MONTH(Start), Type "
                    + "order by Year asc, month asc;";
            
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    data.add(new Quartet(
                        rs.getString("Year"),
                        rs.getString("Month"),
                        rs.getString("Type"),
                        rs.getString("Count")
                    ));
                }
                DBConnection.closeConnection();
            } catch(SQLException throwables) {
                throwables.printStackTrace();
            }
        return data;
        
        
    }
    
    /**
     * Get appointments for a given contact from DB. 
     * @param contactID ID of contact for which to retrieve appointments
     * @return ObservableList of appointmentDTOs for the provided contact ID
     */
    public static ObservableList<appointmentDTO> getAppointmentsByContact(String contactID) {
        
        ObservableList<appointmentDTO> appointments = FXCollections.observableArrayList();
        try {
                String sql =  String.format("select * from appointments where "
                        + "Contact_ID ='%s' order by Start asc;", contactID);

                PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
                
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    appointments.add(new appointmentDTO(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        TimeConverter.toLocal(rs.getTimestamp("Start").toLocalDateTime()),
                        TimeConverter.toLocal(rs.getTimestamp("End").toLocalDateTime()),
                        TimeConverter.toLocal(rs.getTimestamp("Create_Date").toLocalDateTime()),
                        rs.getString("Created_By"),
                        TimeConverter.toLocal(rs.getTimestamp("Last_Update").toLocalDateTime()),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID")
                    ));
                }
                DBConnection.closeConnection();
            } catch(SQLException throwables) {
                throwables.printStackTrace();
            }
        return appointments;
        
    }
    
    /**
     * Get all appointments that have a start date within 15 minutes of current datetime. 
     * @return ObservableList of appointmentDTOs for appointments in the next 15 minutes
     */
    public static ObservableList<appointmentDTO> getFifteenMinuteCheck() {
        
        ObservableList<appointmentDTO> appointments = FXCollections.observableArrayList();
        LocalDateTime currentTime = TimeConverter.toUTC(LocalDateTime.now());
        LocalDateTime fifteenOut = currentTime.plusMinutes(15);
        
        String s = currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String e = fifteenOut.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        try {
                String sql =  String.format("select * from appointments where "
                        + "Start between "
                        + "'%s' and "
                        + "'%s';", s, e);

                PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
                
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    appointments.add(new appointmentDTO(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        TimeConverter.toLocal(rs.getTimestamp("Start").toLocalDateTime()),
                        TimeConverter.toLocal(rs.getTimestamp("End").toLocalDateTime()),
                        TimeConverter.toLocal(rs.getTimestamp("Create_Date").toLocalDateTime()),
                        rs.getString("Created_By"),
                        TimeConverter.toLocal(rs.getTimestamp("Last_Update").toLocalDateTime()),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID")
                    ));
                }
                DBConnection.closeConnection();
            } catch(SQLException throwables) {
                throwables.printStackTrace();
            }
        return appointments;
        
    }
    
    /**
     * Get appointment records for a given week from the DB
     * @param date date that falls within a given week to be searched
     * @return ObservableList of appointmentDTOs for the week specified
     */
    public static ObservableList<appointmentDTO> getWeeklyAppointments(LocalDate date) {
        
        ObservableList<appointmentDTO> appointments = FXCollections.observableArrayList();
        
        try {
                String sql =  "SELECT *\n" +
                "FROM   appointments\n" +
                "WHERE  YEARWEEK(`Start`, 0) = YEARWEEK(?, 0)";

                PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
                
                ps.setString(1, date.toString());
                
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    appointments.add(new appointmentDTO(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        TimeConverter.toLocal(rs.getTimestamp("Start").toLocalDateTime()),
                        TimeConverter.toLocal(rs.getTimestamp("End").toLocalDateTime()),
                        TimeConverter.toLocal(rs.getTimestamp("Create_Date").toLocalDateTime()),
                        rs.getString("Created_By"),
                        TimeConverter.toLocal(rs.getTimestamp("Last_Update").toLocalDateTime()),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID")
                    ));
                }
                DBConnection.closeConnection();
            } catch(SQLException throwables) {
                throwables.printStackTrace();
            }
        return appointments;
        
    }
    
    /**
     * Get appointment records for a given week from the DB
     * @param year year to be searched
     * @param month month of year to search
     * @return ObservableList of appointmentDTOs for the month specified
     */
    public static ObservableList<appointmentDTO> getMonthlyAppointments(Integer year, Integer month) {
        ObservableList<appointmentDTO> appointments = FXCollections.observableArrayList();
        
        try {
                String sql =  "SELECT * FROM appointments WHERE YEAR(Start) = ? AND MONTH(Start) = ?";

                PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
                
                ps.setString(1, year.toString());
                ps.setString(2, month.toString());
                
                ResultSet rs = ps.executeQuery();

                
                while(rs.next()){
                    appointments.add(new appointmentDTO(
                        rs.getInt("Appointment_ID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Location"),
                        rs.getString("Type"),
                        TimeConverter.toLocal(rs.getTimestamp("Start").toLocalDateTime()),
                        TimeConverter.toLocal(rs.getTimestamp("End").toLocalDateTime()),
                        TimeConverter.toLocal(rs.getTimestamp("Create_Date").toLocalDateTime()),
                        rs.getString("Created_By"),
                        TimeConverter.toLocal(rs.getTimestamp("Last_Update").toLocalDateTime()),
                        rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("User_ID"),
                        rs.getInt("Contact_ID")
                    ));
                }
                DBConnection.closeConnection();
            } catch(SQLException throwables) {
                throwables.printStackTrace();
            }
        return appointments;
        
    }
    
    /**
     * Remove appointment record from DB. 
     * @param ID ID of appointment record to delete
     * @throws SQLException
     */
    public static void deleteCustomerAppointment(Integer ID) throws SQLException {
        String sql =  String.format("delete from appointments where Customer_ID = %s", ID.toString());
        Connection connection;
           try {
               connection = DBConnection.getConnection();
               try{
                    connection.setAutoCommit(false);
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate(sql);
                    connection.commit();
                } catch (SQLException e) {
                    connection.rollback();
                    throw e;
                } finally {
                    connection.setAutoCommit(true);
                    DBConnection.closeConnection();
                }
            } catch(Exception e) {
                throw e;
            }

    }
}
