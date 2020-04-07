/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newvivo.Screens;

/**
 *
 * @author Joseph Bermingham
 */
public class Main {

    NewProject newProject;
    StartScreen startScreen;
    ProjectLoad projectLoad;
    static Main mainObj = new Main();

    public static void main(String args[]) {
        //create everyting and set start to true
        mainObj.start();
        mainObj.startScreenVis(true);
        
    }

    public void start() {
        newProject = new NewProject();
        startScreen = new StartScreen();
        projectLoad = new ProjectLoad();
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
}
