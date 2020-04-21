package newvivo.code;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import newvivo.code.Tags;


public class Project {

    private ArrayList<Document> textFiles;
    private ArrayList<Tags> tags;
    private String projectPath;
    private File projectFolder;

    Project(){
      this.textFiles = new ArrayList<Document>();
      this.tags = new ArrayList<Tags>();
      //this.projectPath = TBD;

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
        //parses doc name from path for DOC
        String[] arrpath = path.split("\\\\",0);
        String filename = arrpath[arrpath.length-1];
        
        try {
            textFiles.add(new Document(this.projectPath, filename));
        } catch (FileNotFoundException ex) {
            System.out.println("File Not found exception when initializing project\n" + ex);
        } catch (IOException exept) {
            System.out.println("IO Exception when initializing a previousl created project\n" + exept);
        }
        return true;

    }

    public boolean RemoveDocument(String title) {

        int index = 0;
        int toRemove= -1;
        for(Documents d: textFiles){
             if(d.getFileTitle().equals(title)){toRemove=index;}
             index++;
        }
        if(toRemove!=-1){
           tags.remove(toRemove); 
           return true;
        }
        return false;
   
    }

    public boolean addTag(String tagName, String tagContent, String tagColor) {
        Tags newTag = new Tags(tagName, tagContent, tagColor);
        tags.add(newTag);
        return true;

    }

    public boolean removeTag(String tagContent) {
        
        int index = 0;
        int toRemove= -1;
        for(Tags t: tags){
             if(t.getContent().equals(tagContent)){toRemove=index;}
             index++;
        }
        if(toRemove!=-1){
           tags.remove(toRemove); 
           return true;
        }
        return false;
    }

    public boolean saveProject() {
        //on save populate project textpaths and tags sepearted by line with only '.'
        String fileName = "projectData.txt";

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(Document tf: textFiles){ bufferedWriter.write(tf.getpath() + "---" + tf.getFileTitle() + "---"tf.getpath() + "---" + tf.getContent() + "\n"; }
            bufferedWriter.write(".\n");
            for(Tags t: tags){ bufferedWriter.write(t.getName() + "---" + t.getContent() + "---" + t.getColor() + "\n"); }
            bufferedWriter.close();
        }
        catch (IOException ex) { System.out.println("Error writing to file"); }
        return true;
    }

    public void listFiles() {
        for (String a : this.textFiles){System.out.println(a);}     
    }
}
