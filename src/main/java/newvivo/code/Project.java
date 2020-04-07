package newvivo.code;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import newvivo.code.Tag;
//Tag OBJ needs to be written

public class Project {

    private ArrayList<String> textFiles;
    private ArrayList<Tag> tags;//todo make tag work
    private String projectPath;
    private File projectFolder;

    //New Project
    Project() {

    }

    /**
     * @param path a path to an existing folder
     * @output a Project object that will allow you to access the files inside
     * of the given folder
     */
    public Project(String path) {
        System.out.println(path);
        this.projectFolder = new File(path);
        this.projectPath = path;
        //adds all of the paths to textfiles to textfile, this is prolly not what we want
        //https://docs.oracle.com/javase/7/docs/api/java/io/File.html
        Collections.addAll(this.textFiles, this.projectFolder.list());
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

    public void listFiles() {
        for (String a: this.textFiles) {
            System.out.println(a);
        }
    }
}
