/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.MySql;

import Models.Appointment;
import Services.TimeConverter;
import c195.C195;
import c195.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * AppointmentServices Class. 
 * @author eric
 */
public class AppointmentServices {
    
    /**
     * Inserts a new appointment record into DB, or updates if the saved appointment ID already exists. 
     * SQL transaction is canceled if the record to be inserted overlaps and existing appointment for the customerID.
     * @param candidateAppointment updated appointment object that is not yet recorded on the database
     * @param appointment existing appointment that reflects a DB record, or null if creating a new appointment
     * @throws SQLException
     * @throws Exception
     */
    public static void saveAppointment(Appointment candidateAppointment, Appointment appointment) throws SQLException, Exception {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            System.out.println(appointment);
            String apt_ID = Objects.isNull(appointment) ? "-1" : appointment.getAppointment_ID().toString();
            String sdt = formatter.format(TimeConverter.toUTC(candidateAppointment.getStartTime()));
            String edt = formatter.format(TimeConverter.toUTC(candidateAppointment.getEndTime()));
            String sql1 =  String.format(
                "select * from appointments where Customer_ID = %s and Appointment_ID != %s and ("
                + "('%s' <= Start and '%s' >= Start) or" // new apt overlaps front of existing
                + "('%s' >= Start and '%s' < End) or" // new apt starts during existing apt
                + "('%s' < End and '%s' >= End) or " // new apt overlaps end of existing apt
                + "('%s' <= Start and '%s' >= End))" // new apt encompasses existing apt
               , candidateAppointment.getCustomer_ID(), apt_ID, sdt, edt, sdt, sdt, sdt, edt, sdt, edt);
            
            System.out.print(sql1);
            
                String sql2 = "INSERT INTO `WJ05wZb`.`appointments`\n" +
                "(Appointment_ID,\n" +
                "Title,\n" +
                "Description,\n" +
                "Location,\n" +
                "Type,\n" +
                "Start,\n" +
                "End,\n" +
                "Create_Date,\n" +
                "Created_By,\n" +
                "Last_Update,\n" +
                "Last_Updated_By,\n" +
                "Customer_ID,\n" +
                "User_ID,\n" +
                "Contact_ID)\n" +

                "VALUES\n" +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n" +

                "ON DUPLICATE KEY UPDATE\n" +
                "Title = ?, \n" +
                "Description = ?, \n" +
                "Location = ?, \n" +
                "Type = ?, \n" +
                "Start = ?, \n" +
                "End = ?, \n" +
                "Last_Update = ?, \n" +
                "Last_Updated_By = ?, \n" +
                "Customer_ID = ?, \n" +
                "User_ID = ?, \n" +
                "Contact_ID = ?";

                Connection connection = DBConnection.getConnection();
                connection.setAutoCommit(false);
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql1);


                PreparedStatement ps = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);

                if (appointment != null) {
                    ps.setInt(1, appointment.getAppointment_ID());
                } else {
                    ps.setNull(1, java.sql.Types.INTEGER);
                }
                ps.setString(2, candidateAppointment.getTitle());
                ps.setString(3, candidateAppointment.getDescription());
                ps.setString(4, candidateAppointment.getLocation());
                ps.setString(5, candidateAppointment.getType());
                ps.setString(6, TimeConverter.toUTC(candidateAppointment.getStartTime()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                ps.setString(7, TimeConverter.toUTC(candidateAppointment.getEndTime()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));        
                ps.setString(8, TimeConverter.toUTC(LocalDateTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))); // create date
                ps.setString(9, "application"); 
                ps.setString(10, TimeConverter.toUTC(LocalDateTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))); // last update date
                ps.setString(11, "application"); 
                ps.setInt(12, candidateAppointment.getCustomer_ID());
                ps.setInt(13, candidateAppointment.getUser_ID());
                ps.setInt(14, candidateAppointment.getContact());

        //        update
                ps.setString(15, candidateAppointment.getTitle());
                ps.setString(16, candidateAppointment.getDescription());
                ps.setString(17, candidateAppointment.getLocation());
                ps.setString(18, candidateAppointment.getType());

                ps.setString(19, TimeConverter.toUTC(candidateAppointment.getStartTime()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                ps.setString(20, TimeConverter.toUTC(candidateAppointment.getEndTime()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))); 

                ps.setString(21, TimeConverter.toUTC(LocalDateTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))); // last update date
                ps.setString(22, "application"); 
                ps.setInt(23, candidateAppointment.getCustomer_ID());
                ps.setInt(24, candidateAppointment.getUser_ID());
                ps.setInt(25, candidateAppointment.getContact());

                if (rs.next()) {
                    throw new Exception("overlapping appointment error");
                    
                } else {
                    try {

                    int rs2 = ps.executeUpdate();
                    ResultSet keys = ps.getGeneratedKeys();    
                    keys.next();  

                    if (appointment != null) {
                        appointment.Update(candidateAppointment);
                    } else {
                        candidateAppointment.setAppointment_ID(keys.getInt(1));
                        C195.appointmentRoster.addAppointment(candidateAppointment);
                    }

                    connection.commit();
                    } catch (Exception e) {
                        throw new Exception("Failed to save appointment.");
                    }  finally {
                connection.setAutoCommit(true);
                DBConnection.closeConnection();
                }
            }  
    }
    

    /**
     * Remove appointment from database. 
     * @param ID ID of appointment to be removed
     */
    public static void deleteAppointment(Integer ID) {
        String sql =  "delete from appointments where Appointment_ID = ?";
      
        PreparedStatement ps;
           try {
               ps = DBConnection.getConnection().prepareStatement(sql);
               ps.setString(1, String.valueOf(ID));
               int rs = ps.executeUpdate();
               
               DBConnection.closeConnection();
           } catch (SQLException ex) {
        }
    }
    
}
