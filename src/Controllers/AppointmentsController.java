/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import Models.Appointment;
import c195.C195;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

/**
 * FXML Controller class. Controls the Appointments view.
 *
 * @author eric
 */


public class AppointmentsController extends BaseController implements Initializable {
    
    private LocalDate date = LocalDate.now();
    
    private Integer year;
    private Integer month;
    private Integer day;
    
    @FXML
    private TableView table;
    
    @FXML
    private Text datePickerLabel;
    
    @FXML
    private DatePicker datePicker;
    
    @FXML
    private TableColumn test;
    
    @FXML
    private RadioButton monthButton;
    
    @FXML
    private RadioButton weekButton;
    
    @FXML
    private RadioButton allButton;
    
    /**
     * Public function for updating TableView within this controller. Used by other
     controllers to update instances of this class when changes are made to an appointment
     or the underlying ObservableList used by the TableView within an instance of 
     this class.
     */
    public void refreshTable() {
        System.out.println(C195.appointmentRoster.appointments);
        this.table.setItems(C195.appointmentRoster.appointments);
        this.table.refresh();
    }
    
    /**
     * Initiates a search for appointments that meet search criteria. Uses 
     Month and Week radio button selection, along with selected date to return 
     appointments for the month or week for which the selected date is a part of.
     */
    private void searchDate() {
        
        LocalDate pickerDateValue = datePicker.getValue();
        Integer pickerMonth = pickerDateValue.getMonthValue();
        Integer pickerYear = pickerDateValue.getYear();
                
        if (monthButton.isSelected()) {
            datePickerLabel.setText("Month of");
            C195.appointmentRoster.getMonthlyAppointments(pickerYear, pickerMonth);
        } else if (allButton.isSelected()) {
            C195.appointmentRoster.getAllAppointments();
        }
        else {
            datePickerLabel.setText("Week of");
            C195.appointmentRoster.getWeeklyAppointments(pickerDateValue);
        }
    }
    
    
    /**
     * Navigates back to Appointments view. 
     */
    @FXML
    private void back(ActionEvent event) {
        C195.sceneManager.switchScene("Home.fxml");
    }
    
    /**
     * FXML decorated wrapper function to run searchDate(). Adds logic to update TableView
     once searchDate() has completed.
     * @param event
     */
    @FXML
    private void searchDate(ActionEvent event) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        searchDate();
//        table.setItems(C195.appointmentRoster.appointments);
//        table.refresh();
        refreshTable();
        
    }
    
    
    /**
     * Controls navigation to AppointmentForm view. 
     * @param event
     */
    @FXML
    private void addAppointment (ActionEvent event) throws IOException {
        C195.sceneManager.switchScene("AppointmentForm.fxml");
    }
    
    
    /**
     * Controls navigation to AppointmentForm view with error checking.  Includes
     additional logic that will not allow the user to edit an appointment that has
     already been completed.
     * @param event
     */
    @FXML
    private void editAppointment (ActionEvent event) throws IOException {
        Appointment appointment = (Appointment) table.getSelectionModel().getSelectedItem();
        if (appointment.getEndTime().isAfter(LocalDateTime.now())) {
            C195.sceneManager.switchScene("AppointmentForm.fxml", appointment);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("FontFixer.css").toExternalForm());
                    
            alert.setTitle("");
            alert.setHeaderText("Past Appointment Error");
            ButtonType type = new ButtonType("Done", ButtonBar.ButtonData.OK_DONE);
            alert.setContentText("Cannot edit appointments that have already been completed.");

            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);  
        }
    }
    
    /**
     * Handles the removal of an appointment from the underlying ObservableList 
     used by the TableView.  
     */
    private void delete() {
        try {
            Appointment aptForDelete = (Appointment) table.getSelectionModel().getSelectedItem();
            C195.appointmentRoster.removeAppointment(aptForDelete);
            refreshTable();
            
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    /**
     * Produces a confirmation dialog box with information about the deleted appointment. 
     * @param deletedApt
     */
    private void confirmation(Appointment deletedApt) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("FontFixer.css").toExternalForm());
                    
        alert.setTitle("");
        alert.setHeaderText("Successfully Deleted");
        ButtonType type = new ButtonType("Done", ButtonBar.ButtonData.OK_DONE);
        alert.setContentText("The following appointment has been Deleted: \n" +  
                "Appointment ID:         " + deletedApt.getAppointment_ID().toString() + "\n" +
                "Appointment Type:    " + deletedApt.getType());

        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);  
    }
    
    /**
     * Produces a failure dialog box should deletion of an appointment fail. 
     */
    private void failed() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("FontFixer.css").toExternalForm());
                    
        alert.setTitle("");
        alert.setHeaderText("Failed to Delete");
        ButtonType type = new ButtonType("Done", ButtonBar.ButtonData.OK_DONE);
        alert.setContentText("Failed to delete appointment.  Please contact Technical Support.");
        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);
    }
    
    /**
     * Produces a dialog box to ensure users wants to delete an appointment. Dialog 
     box provides additional information about deletions, and calls appropriate 
     functions if the user confirms they wish to delete.
     * @param event
     */
    @FXML
    public void deleteAppointment(ActionEvent event) {
        Appointment aptForDelete = (Appointment) table.getSelectionModel().getSelectedItem();  
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("FontFixer.css").toExternalForm());
                    
        alert.setTitle("");
        alert.setHeaderText("Warning!");
        ButtonType type = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        alert.setContentText("Deletions are permanent. \n" +  
                "Do you wish to continue?");
        
        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);
        
        if (button == ButtonType.OK) {
            try {
            this.delete();
            this.confirmation(aptForDelete);
            } catch (Exception e) {
                this.failed();
            }
        } else {
        }
    }

    
//    @Override;

    /**
     * Initializes the AppointmentsController. 
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {
                  
        datePicker.setValue(date);
        datePickerLabel.setText("Month of");
        searchDate();
        
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
            new PropertyValueFactory<Appointment,LocalDateTime>("StartTimeFormatted")
        );
//        StartTime.setCellFactory(ColumnTimeFormatter.getFormattedDate());

        
        EndTime.setCellValueFactory(
            new PropertyValueFactory<Appointment,String>("EndTimeFormatted")
        );
//        EndTime.setCellFactory(ColumnTimeFormatter.getFormattedDate());
        
        Customer_ID.setCellValueFactory(
            new PropertyValueFactory<Appointment,String>("Customer_ID")
        );
        User_ID.setCellValueFactory(
            new PropertyValueFactory<Appointment,String>("User_ID")
        );

        table.setItems(C195.appointmentRoster.appointments);
        
    }    
    
}
