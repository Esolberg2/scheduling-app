/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DTOs.Quartet;
import c195.C195;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author eric
 */
public class MonthlyAppointmentSummaryController extends BaseController implements Initializable {
    
    private ObservableList<Quartet> data = FXCollections.observableArrayList();
    
    @FXML
    private TableView table;
    
    @FXML
    private void back(ActionEvent e) {
        C195.sceneManager.switchScene("Home.fxml");
    }
    
    /**
     * Initializes controller. 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.data = C195.appointmentRoster.getAppointmentCountTypeMonth();
  
        TableColumn Year = new TableColumn("Year");
        TableColumn Month = new TableColumn("Month");
        TableColumn Type = new TableColumn("Type");
        TableColumn Count = new TableColumn("Count");
  
        
        table.getColumns().addAll(
                Year,
                Month,
                Type,
                Count
        );

        Year.setCellValueFactory(
            new PropertyValueFactory<Quartet,String>("Year")
        );
        
        Month.setCellValueFactory(
            new PropertyValueFactory<Quartet,String>("Month")
        );
        Type.setCellValueFactory(
            new PropertyValueFactory<Quartet,String>("Type")
        );
        Count.setCellValueFactory(
            new PropertyValueFactory<Quartet,String>("Count")
        );
        
        table.setItems(this.data);
        
    }    
    
}
