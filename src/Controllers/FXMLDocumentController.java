/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.FileWriter;
import java.io.IOException;
import Models.User;
import c195.C195;
import java.io.BufferedWriter;
import java.io.File;
import java.io.Writer;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller. 
 * @author eric
 */
public class FXMLDocumentController extends BaseController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private Label zoneLabel;
    
    @FXML
    private Label zone;
    
    @FXML
    private TextField username;
    
    @FXML
    private Button loginButton;
    
    @FXML
    private PasswordField password;
    
    @FXML
    private Label languageLabel;
    
    @FXML
    private Label errorMessage;
    
    @FXML
    private Label language;
    
    @FXML
    private Label userNameLabel;
    
    @FXML 
    private Label passwordLabel;
    
    
    /**
     * Function to write login information to activity_log.txt. 
     * @param username
     * @param status
     */
    private void logger(String username, String status) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a");
        String logTime = formatter.format(LocalDateTime.now());
        String filePath = new File("").getAbsolutePath();
        String path = filePath.concat("/src/c195/activity_log.txt");
        String u;
        if (username.trim().isEmpty()) {
            u = "None";
        } else {
            u = username;
        }
        
        File f = new File(path);
        Writer output = new BufferedWriter(new FileWriter(f, true));
        output.append(String.format("%s   %s   %s   \n", u, logTime, status));
        output.flush();
        output.close();
    }
    
    
    /**
     * Runs business logic for login actions. Checks if user with username exists 
     on DB, checks password against DB record, and provides error messaging or navigation 
     on a failed login or successful login respectively.
     * @param event
     */
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        try {
            C195.currentUser = new User(username.getText());

            if (password.getText().trim().isEmpty() ||
                    username.getText().trim().isEmpty() ||
                    !Objects.equals(password.getText(), C195.currentUser.getPassword())
                 ) {
                errorMessage.setText(C195.textGetter("passwordError"));
                logger(username.getText().trim(), "Failed Login");
            } else {
                errorMessage.setText("");
                C195.sceneManager.switchScene("Home.fxml");
                logger(username.getText().trim(), "Successful Login");
                
                if (C195.appointmentRoster.getAppointmentsInFifteen().size() > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.getStylesheets().add(getClass().getResource("FontFixer.css").toExternalForm());
                    
                    alert.setTitle("");
                    alert.setHeaderText("Upcoming Appointment");
                    ButtonType type = new ButtonType("Done", ButtonBar.ButtonData.OK_DONE);
                    alert.setContentText("You have appointments scheduled within the next 15 minutes.");
                    Optional<ButtonType> result = alert.showAndWait();
                    ButtonType button = result.orElse(ButtonType.CANCEL); 
                    
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    
                    DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.getStylesheets().add(getClass().getResource("FontFixer.css").toExternalForm()); 
        
                    alert.setTitle("");
                    alert.setHeaderText("No Upcoming Appointments");
                    ButtonType type = new ButtonType("Done", ButtonBar.ButtonData.OK_DONE);
                    alert.setContentText("No upcoming appointments.");
                    Optional<ButtonType> result = alert.showAndWait();
                    ButtonType button = result.orElse(ButtonType.CANCEL); 
                }
            }
        } catch(Exception e) {
            errorMessage.setText(C195.textGetter("passwordError"));
            logger(username.getText().trim(), "Failed Login");
        }
    }
    
    /**
     * Initializes the controller. 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginButton.setText(C195.textGetter("submit"));
        language.setText(C195.textGetter("language"));
        languageLabel.setText(C195.textGetter("languageLabel"));
        userNameLabel.setText(C195.textGetter("userNameLabel"));
        passwordLabel.setText(C195.textGetter("passwordLabel"));
        zoneLabel.setText(C195.textGetter("zoneLabel"));      
        zone.setText(ZoneId.systemDefault().toString());
        
    }    
    
}
