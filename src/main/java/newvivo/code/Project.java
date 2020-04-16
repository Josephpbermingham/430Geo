package newvivo.code;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import newvivo.code.Tags;
//Tag OBJ needs to be written

public class Project {

    private ArrayList<Document> textFiles;
    private ArrayList<Tags> tags;//todo make tag work
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
        this.textFiles = new ArrayList<Document>();
        //adds all of the paths to textfiles to textfile, this is prolly not what we want
        //https://docs.oracle.com/javase/7/docs/api/java/io/File.html
        String[] contents = this.projectFolder.list();
        if (contents.length > 0) {
            for (String content : contents) {
                try {
                    this.textFiles.add(new Document(projectPath, content));
                } catch (FileNotFoundException ex) {
                    System.out.println("File Not found exception when initializing project\n" + ex);
                } catch (IOException exept) {
                    System.out.println("IO Exception when initializing a previousl created project\n" + exept);
                }
            }
        }
    }

    public boolean addDocument(String path) {
        //parse path to get name (after last /)
        //create copy to projectpath(newPath) of path file into project directory
        //TODO make sure this is what we want it to do.
        try {
            textFiles.add(new Document(this.projectPath, path));
        } catch (FileNotFoundException ex) {
            System.out.println("File Not found exception when initializing project\n" + ex);
        } catch (IOException exept) {
            System.out.println("IO Exception when initializing a previousl created project\n" + exept);
        }
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
        Tags newTag;
        newTag = new Tags("TEMP", "TEMP", "TEMP");
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
        /*for (String a : this.textFiles) {
            System.out.println(a);
        }*/
    }
}
