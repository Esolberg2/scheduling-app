/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Appointment;
import Models.Customer;
import c195.C195;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author eric
 */
public class CustomerAppointmentReportController extends BaseController implements Initializable {
    
    @FXML
    private TableView table;
    
    @FXML
    private ComboBox customer_Field;
    
    private ObservableList<Appointment> appointments = FXCollections.observableArrayList();

    /**
     * Navigates back to Home view. 
     * @param e
     */
    @FXML
    private void back(ActionEvent e) {
        C195.sceneManager.switchScene("Home.fxml");
    }
    
    /**
     * Initializes the controller class, contains Lambda. Lambda used to set an
     * action for the customer_Field ComboBox.  This action runs a new appointment search 
     * each time the customer in the ComboBox is changed.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.customer_Field.getItems().addAll(C195.customerRoster.getCustomers());
        
        this.customer_Field.setOnAction((e) -> {
//            String customer = customer_Field.getValue().toString();
            Customer customer = (Customer) customer_Field.getSelectionModel().getSelectedItem();
            String customerID = customer.getCustomerID().toString();
            System.out.println("customerID: " + customerID);
            this.table.setPlaceholder(new Label(String.format("No Appointments for Customer ID %s", customerID)));
            this.appointments = C195.appointmentRoster.getAppointmentsByCustomer(customerID);
            table.setItems(this.appointments);
            table.refresh();
        });
        
        this.table.setPlaceholder(new Label("Please Select Customer ID"));
        
        TableColumn Appointment_ID = new TableColumn("Appointment_ID");
        TableColumn Title = new TableColumn("Title");
        TableColumn Description = new TableColumn("Description");
        TableColumn Type = new TableColumn("Type");
        TableColumn StartTime = new TableColumn("Start Date and Time");
        TableColumn EndTime = new TableColumn("End Date and Time");
        TableColumn Customer_ID = new TableColumn("Customer_ID");
        
        table.getColumns().addAll(
                Appointment_ID,
                Title,
                Description,
                Type,
                StartTime,
                EndTime,
                Customer_ID
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


        Type.setCellValueFactory(
            new PropertyValueFactory<Appointment,String>("Type")
        );
        
        StartTime.setCellValueFactory(
            new PropertyValueFactory<Appointment,LocalDateTime>("StartTime")
        );
        StartTime.setCellFactory(ColumnTimeFormatter.getFormattedDate());
        StartTime.setResizable(true);
        
        EndTime.setCellValueFactory(
            new PropertyValueFactory<Appointment,String>("EndTime")
        );
        EndTime.setCellFactory(ColumnTimeFormatter.getFormattedDate());
        
        Customer_ID.setCellValueFactory(
            new PropertyValueFactory<Appointment,String>("Customer_ID")
        );
        
        
    }    
    
}
