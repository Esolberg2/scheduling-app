/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import javafx.scene.Scene;

/**
 * Custom object for storing persistent scenes in and their associated controller. 
 * @author eric
 */
public class SceneBundle {

  
    public Scene scene;

    public BaseController controller;
    
    /**
     * Constructor for populating instance variables on creation. 
     * @param scene
     * @param controller
     */
    public SceneBundle(Scene scene, BaseController controller) {
        this.scene = scene;
        this.controller = controller;
    }
}
