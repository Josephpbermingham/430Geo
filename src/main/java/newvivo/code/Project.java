package newvivo.code;

import java.io.*;
import java.util.ArrayList;
import newvivo.code.Tag;
//Tag OBJ needs to be written

public class Project {

    private ArrayList<String> textFiles;
    private ArrayList<Tag> tags;//todo make tag work
    private String projectPath;
    
    //New Project
    Project() {

    }
    //project with an already existing folder??? i dont know at time of writing
    Project(String path) {

    }
    

    public boolean addDocument(String path) {
        //parse path to get name (after last /)
        //create copy to projectpath(newPath) of path file into project directory
        String newPath = "Not currently working♥";
        textFiles.add(newPath);
        //check if file added
        return true;//if yes else no

    }

    public boolean RemoveDocument(String title) {
        //search for title in textfiles list and remove
        //delete from directory folder
        //check if removed
        //if yes, return true else false
        return true;
    }

    public boolean addTag(String tagContent) {
        //create newTag tag object
        Tag newTag;
        newTag = new Tag();
        tags.add(newTag);
        //check
        return true;

    }

    public boolean removeTag(String tagContent) {
        //search tags for content
        //remove tag from list
        return false;
    }

    public boolean saveProject() {
        //on save and at close, populate project textpaths file and tags file
        return false;
    }

}
