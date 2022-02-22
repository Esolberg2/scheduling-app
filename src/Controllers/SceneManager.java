/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Models.Appointment;
import Models.Customer;
import c195.C195;
import java.util.ArrayList;

/**
 * Class to handle scene navigation. 
 * @author eric
 */
public class SceneManager {
    
    private final Stage rootStage;
   
    public final Map<String, SceneBundle> scenes = new HashMap<>();
    
    private ArrayList<String> memoizeExemtion = new ArrayList<String>();

    /**
     * Constructor for SceneManager
     * @param rootStage
     */
    public SceneManager(Stage rootStage) {
        memoizeExemtion.add("Home.fxml");
        memoizeExemtion.add("MonthlyAppointmentSummary.fxml");
        memoizeExemtion.add("CustomerAppointmentReport.fxml");
        memoizeExemtion.add("ContactSchedule.fxml");
        memoizeExemtion.add("AppointmentForm.fxml");
        memoizeExemtion.add("CustomerForm.fxml");

        if (rootStage == null) {
            throw new IllegalArgumentException();
        }
        this.rootStage = rootStage;
    }
    
    /**
     * Wrapper for executing a simple scene switch without passing and object between 
     * controllers. This class uses a list of exemptions to know when to persist 
     * a scene between navigations vs re initializing the scene each time it is set.
     * @param url
     */
    public void switchScene(String url) {
        if (url == "Customers.fxml") {
            C195.customerRoster.refreshRoster();
        } else if (url == "Appointments.fxml") {
            C195.appointmentRoster.refreshAppointments();
        }
        
        if (memoizeExemtion.contains(url)) {
            directSwitch(url);
        } else {
            memoize(url);
        }
    }
    
    /**
     * Logic for switchScene() that saves a reference to a scene so it can be navigated 
     * to again later, with all content persisting, contains Lambda. Lambda
     * that is passed to the Map.computeIfAbsent() function.  The lambda determines 
     * what new object is stored in the map if the requested key does not exist.
     * @param url
     */
    private void memoize(String url) {
        SceneBundle sceneBundle = scenes.computeIfAbsent(url, u -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/" + u));
            try {
                Pane p = loader.load();
                BaseController controller = loader.getController();
                controller.setSceneManager(this);
                Scene s = new Scene(p);
                s.getStylesheets().add(getClass().getResource("FontFixer.css").toExternalForm());
                return new SceneBundle(s, controller);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        rootStage.setScene(sceneBundle.scene);
    }
    
    
    /**
     * Logic for switchScene() that navigates to a scene without saving a reference. 
     * @param url
     */
    private void directSwitch(String url) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/" + url));
        try {
            Pane p = loader.load();
            BaseController controller = loader.getController();
            controller.setSceneManager(this);
            Scene s = new Scene(p);
            s.getStylesheets().add(getClass().getResource("FontFixer.css").toExternalForm());
            SceneBundle sceneBundle = new SceneBundle(s, controller);
            rootStage.setScene(sceneBundle.scene);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
    }
    
    /**
     * Overloaded switchScene that accepts an Appointment Object to pass to the 
     new scene controller, contains Lambda. Lambda to the loader.setControllerFactory() function.
     * @param url
     * @param appointment
     */
    public void switchScene(String url, Appointment appointment) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/" + url));
            loader.setControllerFactory(c -> {
                return new AppointmentFormController(appointment);
             });
            try {
                Pane p = loader.load();
                BaseController controller = loader.getController();
                controller.setSceneManager(this); 
                Scene s = new Scene(p);
                s.getStylesheets().add(getClass().getResource("FontFixer.css").toExternalForm());
                rootStage.setScene(s);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
    }
    
    /**
     * Overloaded switchScene that accepts an Customer Object to pass to the 
     new scene controller, contains Lambda. Lambda to the loader.setControllerFactory() function.
     * @param url
     * @param customer
     */
    public void switchScene(String url, Customer customer) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/" + url));
            loader.setControllerFactory(c -> {
                return new CustomerFormController(customer);
             });
            try {
                Pane p = loader.load();
                BaseController controller = loader.getController();
                controller.setSceneManager(this); 
                Scene s = new Scene(p);
                s.getStylesheets().add(getClass().getResource("FontFixer.css").toExternalForm());
                rootStage.setScene(s);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
    }
}
