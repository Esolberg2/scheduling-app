/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Appointment;
import c195.C195;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author eric
 */
public class HomeController extends BaseController implements Initializable {
    
    private ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    
    @FXML
    private TableView table;
    
    /**
     * Updates the TableView for this class, as well as its underlying ObservableList. 
     * @param e
     */
    @FXML
    private void refresh(ActionEvent e) {
        this.appointments = C195.appointmentRoster.getAppointmentsInFifteen();
        this.table.setItems(this.appointments);
        this.table.refresh();
    }
    
    /**
     * Navigates to Customers view. 
     * @param event
     */
    @FXML
    private void manageCustomers (ActionEvent event) throws IOException {
        C195.sceneManager.switchScene("Customers.fxml");
    }
    
    /**
     * Navigates to Appointments view. 
     * @param event
     */
    @FXML
    private void manageAppointments (ActionEvent event) throws IOException {
        C195.sceneManager.switchScene("Appointments.fxml");
    }
    
    /**
     * Navigates to ContactSchedule view. 
     * @param event
     */
    @FXML
    private void contactSchedule (ActionEvent event) throws IOException {
        C195.sceneManager.switchScene("ContactSchedule.fxml");
    }
    
    /**
     * Navigates to MonthlyAppointmentSummary view. 
     * @param event
     */
    @FXML
    private void mothlyAppointmentSummary (ActionEvent event) throws IOException {
        C195.sceneManager.switchScene("MonthlyAppointmentSummary.fxml");
    }
    
    /**
     * Navigates to CustomerAppointmentReport view. 
     * @param event
     */
    @FXML
    private void customerSchedule (ActionEvent event) throws IOException {
        C195.sceneManager.switchScene("CustomerAppointmentReport.fxml");
    }
    
    /**
     * Initializes controller. 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.appointments = C195.appointmentRoster.getAppointmentsInFifteen();
        
        this.table.setPlaceholder(new Label("No Upcoming Appointments"));
        
        TableColumn Appointment_ID = new TableColumn("Appointment_ID");
        TableColumn Title = new TableColumn("Title");
        TableColumn Description = new TableColumn("Description");
        TableColumn Location = new TableColumn("Location");
        TableColumn Contact = new TableColumn("Contact");
        TableColumn Type = new TableColumn("Type");
        TableColumn StartTime = new TableColumn("Start Date and Time");
        TableColumn EndTime = new TableColumn("End Date and Time");
        TableColumn Customer_ID = new TableColumn("Customer_ID");
        TableColumn User_ID = new TableColumn("User_ID");
        
        table.getColumns().addAll(
                Appointment_ID,
                Title,
                Description,
                Location,
                Contact,
                Type,
                StartTime,
                EndTime,
                Customer_ID,
                User_ID
        );

        Appointment_ID.setCellValueFactory(
            new PropertyValueFactory<Appointment,String>("Appointment_ID")
        );
        Title.setCellValueFactory(
            new PropertyValueFactory<Appointment,String>("Title")
        );
        Description.setCellValueFactory(
            new PropertyValueFactory<Appointment,String>("Description")
        );
        Location.setCellValueFactory(
            new PropertyValueFactory<Appointment,String>("Location")
        );
        Contact.setCellValueFactory(
            new PropertyValueFactory<Appointment,String>("Contact")
        );
        Type.setCellValueFactory(
            new PropertyValueFactory<Appointment,String>("Type")
        );
        StartTime.setCellValueFactory(
            new PropertyValueFactory<Appointment,LocalDateTime>("StartTime")
        );
        StartTime.setCellFactory(ColumnTimeFormatter.getFormattedDate());

        
        EndTime.setCellValueFactory(
            new PropertyValueFactory<Appointment,String>("EndTime")
        );
        EndTime.setCellFactory(ColumnTimeFormatter.getFormattedDate());
        
        Customer_ID.setCellValueFactory(
            new PropertyValueFactory<Appointment,String>("Customer_ID")
        );
        User_ID.setCellValueFactory(
            new PropertyValueFactory<Appointment,String>("User_ID")
        );

        table.setItems(this.appointments);
    }    
    
}
