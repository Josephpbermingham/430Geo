import java.io.*;
import java.util.ArrayList;
//needs access to tag object

public class Project {

    private ArrayList<String> textFiles;
    private ArrayList<Tag> tags;
    private String projectPath;


    Project(){

    }

    Project(String path){

    }

    public boolean addDocument(String path){
        //parse path to get name (after last /)
        //create copy to projectpath(newPath) of path file into project directory
        textFiles.add(newPath);
        //check if file added
        return true;//if yes else no

    }

    public boolean RemoveDocument(String title){
        //search for title in textfiles list and remove
        //delete from directory folder
        //check if removed
        //if yes, return true else false
        return true;
    }

    public boolean addTag(String tagContent){
        //create newTag tag object
        tags.add(newTag);
        //check
        return true;

    }

    public boolean removeTag(String tagContent){
        //search tags for content
        //remove tag from list

    }

    public boolean saveProject(){
        //on save and at close, populate project textpaths file and tags file
    }


}
