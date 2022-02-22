/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195;

import Controllers.SceneManager;
import Factory.Service_Factory_Interface;
import Models.AppointmentRoster;
import Models.ContactRoster;
import Models.CountryRoster;
import Models.CustomerRoster;
import Models.DivisionRoster;
import Models.User;
import Models.UserRoster;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

/**
 * Application Class. 
 * @author eric
 */
public class C195 extends Application {
    
    public static Service_Factory_Interface serviceFactory;
    public static SceneManager sceneManager;
    private static String languageCode;
    public static User currentUser;
    public static AppointmentRoster appointmentRoster;
    public static CustomerRoster customerRoster;
    public static ContactRoster contactRoster;
    public static DivisionRoster divisions;
    public static CountryRoster countries;
    public static UserRoster userRoster;
    public static String test = "test";
    
    private static Map<String, String> textTranslation = new HashMap<String, String>() {{
        put("en submit", "Submit");
        put("fr submit", "Entrer");
        
        put("en language", "English");
        put("fr language", "Française");
        
        put("en languageLabel", "Language");
        put("fr languageLabel", "Langue");
        
        put("en userNameLabel", "Username");
        put("fr userNameLabel", "Nom d'utilisateur");
        
        put("en passwordLabel", "Password");
        put("fr passwordLabel", "Mot de passe");
        
        put("en passwordError", "Incorrect username or password entered.");
        put("fr passwordError", "Nom d'utilisateur ou mot de passe incorrect saisi.");
        
        put("en zoneLabel", "Zone ID");
        put("fr zoneLabel", "identifiant de zone");
        
    }};
    
    /**
     * Gets translation text from textTranslation class variable. 
     * @param partialKey key to specify translated text
     * @return translated text message
     */
    public static String textGetter(String partialKey) {
        String message = textTranslation.get(languageCode + " " + partialKey);
        return message;
    }
    
    /**
     * Primary method for the application and serves as the entry point when running the program.
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        sceneManager = new SceneManager(stage);
        serviceFactory = Service_Factory_Interface.getFactory("MySQL");
        appointmentRoster = new AppointmentRoster();
        countries = new CountryRoster();
        customerRoster = new CustomerRoster();
        contactRoster = new ContactRoster();
        divisions = new DivisionRoster();
        userRoster = new UserRoster();
        sceneManager.switchScene("FXMLDocument.fxml");
        
        stage.show();

    }

    /**
     * 
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        
        languageCode = Locale.getDefault().getLanguage();
//        TimeZone.setDefault(TimeZone.getTimeZone("US/Mountain"));
        
        DBConnection.startConnection();
        launch(args);
        DBConnection.closeConnection();
    }
    
}