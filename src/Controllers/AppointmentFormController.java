/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import Models.Appointment;
import c195.C195;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

/**
 * FXML Controller class. Controls the AppointmentForm view.
 *
 * @author eric
 */
public class AppointmentFormController extends BaseController implements Initializable {
       
    private Appointment appointment;
    private Appointment candidateAppointment = new Appointment();
    
    @FXML
    private TextField appointment_ID_Field;
    
    @FXML
    private ComboBox customer_ID_Field;
    
    @FXML
    private TextField title_Field;
    
    @FXML
    private TextField appointment_type_Field;
    
    @FXML
    private TextField description_Field;
    
    @FXML
    private TextField location_Field;
    
    @FXML
    private DatePicker date_Field;
    
    @FXML
    private ComboBox<Integer> start_Time_Hour_Field;
    
    @FXML
    private ComboBox<Integer> start_Time_Minute_Field;
    
    @FXML
    private ComboBox<Integer> end_Time_Hour_Field;
    
    @FXML
    private ComboBox<Integer> end_Time_Minute_Field;
    
    @FXML
    private ComboBox contact_Field;
    
    @FXML
    private ComboBox user_ID_Field;
    
    /**
     * Constructor for creating a new appointment. 
     */
    public AppointmentFormController() {
        
    }
    
    /**
     * Constructor for receiving existing Appointment object when editing an appointment. 
     * @param appointment
     */
    public AppointmentFormController(Appointment appointment) {
        this.appointment = appointment;
    }
    
    
    
    /**
     * Acts as Back button. 
     Returns user to AppointmentForm.
     * @param event
     */
    @FXML
    private void cancel(ActionEvent event) {
        C195.sceneManager.switchScene("Appointments.fxml");
    }
    
    
    
    
    /**
     * Runs business logic for saving an appointment. 
     This function handles both updating existing appointments and saving new 
     appointments.
     * @param event
     */
    @FXML
    private void saveAppointment(ActionEvent event) throws Exception {
        System.out.println("Save appt run");
        try {
                LocalDateTime startTime = LocalDateTime.of(this.date_Field.getValue(), LocalTime.of(this.start_Time_Hour_Field.getValue(), this.start_Time_Minute_Field.getValue()));
                LocalDateTime endTime = LocalDateTime.of(this.date_Field.getValue(), LocalTime.of(this.end_Time_Hour_Field.getValue(), this.end_Time_Minute_Field.getValue()));
                
                LocalDateTime sod = LocalDateTime.of(this.date_Field.getValue(), LocalTime.of(8,0));
                LocalDateTime eod = LocalDateTime.of(this.date_Field.getValue(), LocalTime.of(22,0));
                
                
                ZonedDateTime ozdt = ZonedDateTime.of(startTime, ZoneId.systemDefault());
                ZonedDateTime startEastern = ozdt.withZoneSameInstant(ZoneId.of("US/Eastern"));

                ZonedDateTime czdt = ZonedDateTime.of(endTime, ZoneId.systemDefault());
                ZonedDateTime endEastern = czdt.withZoneSameInstant(ZoneId.of("US/Eastern"));
                
                
                
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a");
                System.out.println(" ");
                System.out.println(" ");
                
                System.out.println("Start Time Local: " + formatter.format(startTime));
                System.out.println("End Time Local: " + formatter.format(endTime));
                
                System.out.println("Start Time Eastern: " + formatter.format(startEastern.toLocalDateTime()));
                System.out.println("End Time Eastern: " + formatter.format(endEastern.toLocalDateTime()));
                
                System.out.println("sod: " + formatter.format(sod));
                System.out.println("eod: " + formatter.format(eod));
                                
                if (
                        startEastern.toLocalDateTime().isBefore(sod) ||
                        endEastern.toLocalDateTime().isAfter(eod)
//                        endEastern.toLocalTime().isAfter(LocalTime.of(22, 0))
//                        endEastern.toLocalTime().isBefore(startEastern.toLocalTime())
                        ) {
//                    System.out.println("Appointments cannot be scheduled outside business hours.");
                    throw new Exception("Appointments cannot be scheduled outside business hours.");
                }
                else if (customer_ID_Field.getValue() == null) {
//                    System.out.println("Customer ID cannot be empty");
                    throw new Exception("Customer ID cannot be empty.");
                }
                else if (contact_Field.getValue() == null) {
//                    System.out.println("Contact ID cannot be empty.");
                    throw new Exception("Contact ID cannot be empty.");
                }
                else if (user_ID_Field.getValue() == null) {
//                    System.out.println("User ID cannot be empty.");
                    throw new Exception("User ID cannot be empty.");
                }
                else if (startTime.isAfter(endTime)) {
//                    System.out.println("Appointment Start must be before meeting End");
                    throw new Exception("Appointment Start must be before meeting End");
                }
                else if (startTime.isBefore(LocalDateTime.now())) {
//                    System.out.println("Appointment Start cannot be in past");
                    throw new Exception("Appointment Start cannot be in past.");
                }
                else {
                    this.candidateAppointment.setTitle(this.title_Field.getText());
                    this.candidateAppointment.setDescription(this.description_Field.getText());
                    this.candidateAppointment.setLocation(this.location_Field.getText());
                    this.candidateAppointment.setType(this.appointment_type_Field.getText());
                    this.candidateAppointment.setStartTime(startTime);
                    this.candidateAppointment.setEndTime(endTime);
                    this.candidateAppointment.setContact(Integer.valueOf(this.contact_Field.getValue().toString()));
                    this.candidateAppointment.setUser_ID(Integer.valueOf(this.user_ID_Field.getValue().toString()));
                    this.candidateAppointment.setCustomer_ID(Integer.valueOf(this.customer_ID_Field.getValue().toString()));

                    C195.serviceFactory.saveAppointment(candidateAppointment, appointment);
                    AppointmentsController cont = (AppointmentsController) C195.sceneManager.scenes.get("Appointments.fxml").controller;
                    cont.refreshTable();
                    C195.sceneManager.switchScene("Appointments.fxml");
                }
                
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("FontFixer.css").toExternalForm());
                    
            alert.setTitle("");
            alert.setHeaderText("");
            ButtonType type = new ButtonType("Done", ButtonBar.ButtonData.OK_DONE);
            alert.setContentText(e.getMessage());
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);
        }
      }
    
    /**
     * Initializes the controller class. 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<Integer> hourOptions = FXCollections.observableArrayList();
        
        Integer i;
        for (i = 0; i <= 23; i++) {
            hourOptions.add(i);
        };
        
        ObservableList<Integer> minuteOptions = FXCollections.observableArrayList();
        for (i = 0; i <= 59; i++) {
            minuteOptions.add(i);
        };
        
        this.contact_Field.getItems().addAll(C195.contactRoster.getContacts());
        this.user_ID_Field.getItems().addAll(C195.userRoster.getUsers());
        this.customer_ID_Field.getItems().addAll(C195.customerRoster.getCustomers());
        
        this.start_Time_Hour_Field.getItems().addAll(hourOptions);
        this.start_Time_Minute_Field.getItems().addAll(minuteOptions);
        this.end_Time_Hour_Field.getItems().addAll(hourOptions);
        this.end_Time_Minute_Field.getItems().addAll(minuteOptions);
        
        if (this.appointment != null) {
            LocalDateTime lDate = this.appointment.getStartTime();
            this.appointment_ID_Field.setText(this.appointment.getAppointment_ID().toString());
            this.customer_ID_Field.setValue(this.appointment.getCustomer_ID().toString());
            this.title_Field.setText(this.appointment.getTitle());
            this.appointment_type_Field.setText(this.appointment.getType());
            this.description_Field.setText(this.appointment.getDescription());
            this.location_Field.setText(this.appointment.getLocation());
            
            this.date_Field.setValue(lDate.toLocalDate());

            
            this.start_Time_Hour_Field.setValue(lDate.toLocalTime().getHour());
            this.start_Time_Minute_Field.setValue(lDate.toLocalTime().getMinute());
            
            this.end_Time_Hour_Field.setValue(this.appointment.getEndTime().toLocalTime().getHour());
            this.end_Time_Minute_Field.setValue(this.appointment.getEndTime().toLocalTime().getMinute());

            this.contact_Field.setValue(this.appointment.getContact());
            
            this.user_ID_Field.setValue(this.appointment.getUser_ID().toString());
        } else 
            {
            this.candidateAppointment = new Appointment();
            this.start_Time_Hour_Field.setValue(0);
            this.start_Time_Minute_Field.setValue(0);
            this.end_Time_Hour_Field.setValue(0);
            this.end_Time_Minute_Field.setValue(0);
        }
    }    
    
}
