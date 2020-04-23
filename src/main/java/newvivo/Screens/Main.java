/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newvivo.Screens;

import java.io.File;
import newvivo.code.Project;

/**
 * This is the main class, the mainObj is the way that we are going to access 
 * our screens, the visibility
 * 
 * this should essentially act as a singleton, storing the things that we
 * will need to repeatedly access, such as the screens and project.
 * @author Joseph Bermingham, Matt, Adam
 */
public class Main {
    //container object should be a thread protected singleton. but meh
    public static Main mainObj = new Main(); 
    
    //class objects
    NewProject newProject;
    StartScreen startScreen;
    ProjectLoad projectLoad;
    CreateTag createTag;
    public Project projectObj;//Created when you select a project path
   
   

    public static void main(String args[]) {
        //create everyting and set start to true
        mainObj.start();
        mainObj.startScreenVis(true);
        
    }

    public void start() {
        newProject = new NewProject();
        startScreen = new StartScreen();
        projectLoad = new ProjectLoad();
        createTag = new CreateTag();     
        
        
    }

    public void newProjectVis(boolean visible) {
        this.newProject.setVisible(visible);
    }
    public void startScreenVis(boolean visible) {
        this.startScreen.setVisible(visible);
    }
    public void projectLoadVis(boolean visible) {
        this.projectLoad.setVisible(visible);
    }
     public Project getProjectObj() {
        return projectObj;
    }

    public void setProjectObj(Project projectObj) {
        this.projectObj = projectObj;
    }
}
