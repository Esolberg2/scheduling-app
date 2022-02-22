/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Customer;
import c195.C195;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author eric
 */
public class CustomersController extends BaseController implements Initializable {

    @FXML
    private TableView table;
    
    
    /**
     * Runs underlying functions needed for Customer record deletion. 
     */
    private void delete() {
        Customer c = (Customer) table.getSelectionModel().getSelectedItem();
        try {
            C195.appointmentRoster.removeCustomerAppointment(c.getCustomerID());
            C195.customerRoster.removeCustomer(c);
            if (C195.sceneManager.scenes.containsKey("Appointments.fxml")) {
                AppointmentsController cont = (AppointmentsController) C195.sceneManager.scenes.get("Appointments.fxml").controller;
                cont.refreshTable();
            }
        }
        catch(Exception e) {
        }
    }
    
    /**
     * Wrapper for delete() function to control deletion confirmation dialog. 
     * @param event
     */
    @FXML
    public void deleteCustomer(ActionEvent event) {        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("FontFixer.css").toExternalForm());
        
        alert.setTitle("");
        alert.setHeaderText("Warning!");
        ButtonType type = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        alert.setContentText("Deletions are permanent. \n" + 
                "All appointments for this customer will be deleted. \n" + 
                "Do you wish to continue?");
        
        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);
        
        if (button == ButtonType.OK) {
            this.delete();
        } else {
        }
    }
    
    public void refreshTable() {
        this.table.setItems(C195.customerRoster.customers);
        this.table.refresh();
    }
    
    /**
     * Navigates back to the Home view. 
     * @param event
     */
    @FXML
    public void back(ActionEvent event) {
        C195.sceneManager.switchScene("Home.fxml");
    }
    
    /**
     * Navigates to CustomerForm view, passing null Customer object as the CustomerForm 
     Controller will need to create a new Customer.
     * @param event
     */
    @FXML
    public void newCustomer(ActionEvent event) {
        C195.sceneManager.switchScene("CustomerForm.fxml", (Customer) null);
    }
    
    /**
     * Navigates to the CustomerForm view and passes the selected Customer object 
     to the CustomerFormController. 
     * @param event
     */
    @FXML
    public void editCustomer(ActionEvent event) {
        C195.sceneManager.switchScene("CustomerForm.fxml", (Customer) table.getSelectionModel().getSelectedItem());
    }
    
    /**
     * Initializes the controller. 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        C195.customerRoster.refreshRoster();
        
        TableColumn Customer_ID = new TableColumn("Customer ID");
        TableColumn Customer_Name = new TableColumn("Name");
        TableColumn Address = new TableColumn("Address");
        TableColumn Postal_Code = new TableColumn("Postal Code");
        TableColumn Phone = new TableColumn("Phone");
        TableColumn Create_Date = new TableColumn("Create Date");
        TableColumn Created_By = new TableColumn("Created By");
        TableColumn Last_Updated = new TableColumn("Last Updated");
        TableColumn Last_Updated_By = new TableColumn("Last Updated By");
        TableColumn Division_ID = new TableColumn("Division ID");
        
        table.getColumns().addAll(
                Customer_ID,
                Customer_Name,
                Address,
                Postal_Code,
                Phone,
                Create_Date,
                Created_By,
                Last_Updated,
                Last_Updated_By,
                Division_ID);

        
        Customer_ID.setCellValueFactory(
            new PropertyValueFactory<Customer,String>("CustomerID")
        );
        Customer_Name.setCellValueFactory(
            new PropertyValueFactory<Customer,String>("CustomerName")
        );
        Address.setCellValueFactory(
            new PropertyValueFactory<Customer,String>("CustomerAddress")
        );
        Postal_Code.setCellValueFactory(
            new PropertyValueFactory<Customer,String>("CustomerPostalCode")
        );
        Phone.setCellValueFactory(
            new PropertyValueFactory<Customer,String>("CustomerPhone")
        );
        Create_Date.setCellValueFactory(
            new PropertyValueFactory<Customer,String>("CreateDateFormatted")
        );
                
        Created_By.setCellValueFactory(
            new PropertyValueFactory<Customer,String>("CreatedBy")
        );
        Last_Updated.setCellValueFactory(
            new PropertyValueFactory<Customer,String>("LastupdatedFormatted")
        );
                
        Last_Updated_By.setCellValueFactory(
            new PropertyValueFactory<Customer,String>("LastUpdatedBy")
        );
        Division_ID.setCellValueFactory(
            new PropertyValueFactory<Customer,String>("DivisionID")
        );

        table.setItems(C195.customerRoster.getCustomers());
    }    
    
}
