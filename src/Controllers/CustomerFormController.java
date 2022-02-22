/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Models.Country;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import Models.Customer;
import Models.Division;
import c195.C195;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

/**
 * FXML Controller class
 *
 * @author eric
 */
public class CustomerFormController extends BaseController implements Initializable {

    
    private Customer customer;
    
    /**
     * Constructor to accept ref to existing customer object for editing.
     * @param customer
     */
    public CustomerFormController(Customer customer) {
        this.customer = customer;
    }
    
    /**
     * Constructor for creating a new customer. 
     */
    public CustomerFormController() {
        this.customer = customer;
    }
    
    @FXML
    private TextField customer_ID_Field;
    
    
    @FXML
    private TextField name_Field;
    
    @FXML
    private TextField phone_Field;
    
    @FXML
    private ComboBox country_region_Field;
    
    @FXML
    private TextField street_address_Field;
    
    @FXML
    private ComboBox state_province_Field;
    
    @FXML
    private TextField zip_postal_Field;
    
    
    /**
     * Navigates back to Customers view.
     * @param event
     */
    @FXML
    private void cancel(ActionEvent event) {
        C195.sceneManager.switchScene("Customers.fxml");
    }
    
    /**
     * Saves updates to existing customer, or saves the newly created customer object. 
     Additionally, calls associated functions to update the Customers controller 
     TableView and its associated ObservableList.
     * @param event
     */
    @FXML
    private void saveCustomer(ActionEvent event) {
        
        try{
            Division div = (Division) this.state_province_Field.getValue();

            ArrayList<String> fields = new ArrayList<String>();
            fields.add(div.getDivision_ID().trim());
            fields.add(name_Field.getText().trim());
            fields.add(street_address_Field.getText().trim());
            fields.add(zip_postal_Field.getText().trim());
            fields.add(phone_Field.getText().trim());

            if (fields.contains(null) || fields.contains("") ){
                throw new Exception("No fields can be left empty.");
            }
            else if (this.customer != null){
                C195.serviceFactory.saveCustomer(
                    this.name_Field.getText(),
                    this.street_address_Field.getText(),
                    this.zip_postal_Field.getText(),
                    this.phone_Field.getText(),
                    Integer.valueOf(div.getDivision_ID()),
                    this.customer.getCustomerID()
                );

                C195.customerRoster.refreshRoster();
                C195.sceneManager.switchScene("Customers.fxml");
            } else {

                C195.serviceFactory.saveCustomer(
                    this.name_Field.getText(),
                    this.street_address_Field.getText(),
                    this.zip_postal_Field.getText(),
                    this.phone_Field.getText(),
                    Integer.valueOf(div.getDivision_ID())
                );
                
                CustomersController cont = (CustomersController) C195.sceneManager.scenes.get("Customers.fxml").controller;
                cont.refreshTable();
//                C195.customerRoster.refreshRoster();
                C195.sceneManager.switchScene("Customers.fxml");
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("FontFixer.css").toExternalForm());

            alert.setTitle("");
            alert.setHeaderText("");
            ButtonType type = new ButtonType("Done", ButtonBar.ButtonData.OK_DONE);
            alert.setContentText("No fields can be left empty.");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);
        }
    }
    
    
    /**
     * Provides a means to trigger custom event defined during initialization when
     the ComboBox value is changed. Used to initiate the filtering of State / Region 
     values in second ComboBox, depending on the selection of the initial ComboBox 
     where this function is set.
     * @param event
     */
    @FXML
    private void comboAction(ActionEvent event) {
    }
    
    /**
     * Initializes the controller, contains Lambda. Lambda used to set an
     * action for the country_region_Field ComboBox.  This action filters the sate / province
     * options in the state_province_Field ComboBox when the country in the country_region_Field
     * is changed. Additional lambda functions are used to create the Predicates used to accomplish
     * the filtering of state / province options.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.country_region_Field.getItems().addAll(C195.countries.getCountries());
        
        FilteredList<Division> items = new FilteredList<>(C195.divisions.getDivisions());
        this.state_province_Field.getItems().addAll(items);

        Predicate<Division> US = i -> i.getCountry_ID().equals("1");
        Predicate<Division> UK = i -> i.getCountry_ID().equals("2");
        Predicate<Division> Canada = i -> i.getCountry_ID().equals("3");
        
        this.country_region_Field.setOnAction((e) -> {
            Predicate<Division> filter;
            String currVal = this.country_region_Field.getValue().toString();
            
            if (currVal.equals("U.S")) {
                filter = US;
            }
            else if (currVal.equals("UK")) {
                filter = UK;
            }
            else if (currVal.equals("Canada")) {
                filter = Canada;
            } else {
                filter = null;
            }
            
            this.state_province_Field.setValue(null);
            items.setPredicate(filter);
            this.state_province_Field.setItems(items);
        });
        
        
        if (this.customer != null) {
            
            Country country = C195.divisions.getCountryByDivisionByID(customer.getDivisionID());
            Division division = C195.divisions.getDivisionByID(customer.getDivisionID());
            
            this.customer_ID_Field.setText(this.customer.getCustomerID().toString());
            this.name_Field.setText(this.customer.getCustomerName());
            this.phone_Field.setText(this.customer.getCustomerPhone());
            this.country_region_Field.setValue(country.getCountryName());
            this.state_province_Field.setValue(division);
            this.street_address_Field.setText(this.customer.getCustomerAddress());
            this.zip_postal_Field.setText(this.customer.getCustomerPostalCode());
            
        }
    }    
    
}
