/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

/**
 * BaseController class. Parent class from which all other controllers are derived. 
 Allows every child controller to have access to setSceneManager.
 * @author eric
 */
public class BaseController {
    
    /**
     *
     */
    protected SceneManager sceneManager;
    
    /**
     * Constructor for BaseController class. 
     */
    public BaseController() {
        
    }
    
    /**
     * Allows all child controllers to the sceneManager variable. By allowing each 
     child controller to set BaseController.sceneManager to itself, the context of 
     the current scene can be accurately changed, depending on the controller being 
     utilized.
     */
    void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
    
}
