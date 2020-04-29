package newvivo.code;

import java.io.*;
import java.util.ArrayList;
import newvivo.Screens.Main;
import org.apache.commons.io.FileUtils;
import newvivo.code.XMLParse;
import org.apache.commons.lang3.StringUtils;

public class Project {

    private ArrayList<Document> textFiles;
    private ArrayList<Tags> tags;
    private String projectPath;
    private File projectFolder;

    //new project still needs save path from user or default path set up on first run
    //Project(){
    //this.textFiles = new ArrayList<Document>();
    //this.tags = new ArrayList<Tags>();
    //this.projectPath = TBD;
    //}
    /**
     * @param path a path to an existing folder
     * @output a Project object that will allow you to access the files inside
     * of the given folder
     */
    public Project(String path) {
        this.projectFolder = new File(path);
        if (!this.projectFolder.exists()) {
            this.projectFolder.mkdir();
        }
        this.projectPath = path;
        // populate(path);
        this.textFiles = new ArrayList<Document>();
        this.tags = new ArrayList<Tags>();
        //adds all of the paths to textfiles to textfile, this is prolly not what we want
        //https://docs.oracle.com/javase/7/docs/api/java/io/File.html
        String[] contents = this.projectFolder.list();
        if (contents.length > 0) {
            for (String content : contents) {
                try {
                    //if its an xml add it to tags, if it isnt add it to documents
                    if (content.contains(".xml")) {
                        System.out.println(path + "\\" + content);
                        XMLParse p = new XMLParse();
                        this.tags.add(p.Parse(path + "\\" + content));
                    } else if (content.contains(".docx")) {
                        this.textFiles.add(new Document(projectPath, content));

                    } else {
                        System.out.println("unsupported file");
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println("File Not found exception when initializing project\n" + ex);
                } catch (IOException exept) {
                    System.out.println("IO Exception when initializing a previousl created project\n" + exept);
                }
            }
        }
    }

    public boolean addDocument(File srcFile) {
        //parses doc name from path for DOC
        //String[] arrpath = path.split("\\\\", 0);

        //String filename = arrpath[arrpath.length - 1];
        File destFile = new File(Main.mainObj.projectObj.projectPath + "/" + srcFile.getName());
        try {
            FileUtils.copyFile(srcFile, destFile);
            Main.mainObj.projectObj.textFiles.add(new Document(this.projectPath, srcFile.getName()));
        } catch (FileNotFoundException ex) {
            System.out.println("File Not found exception when initializing project\n" + ex);
        } catch (IOException exept) {
            System.out.println("IO Exception when initializing a previousl created project\n" + exept);
        }
        return true;

    }

    public boolean RemoveDocument(String title) {

        int index = 0;
        int toRemove = -1;
        for (Document d : textFiles) {
            if (d.getFileTitle().equals(title)) {
                toRemove = index;
            }
            index++;
        }
        if (toRemove != -1) {
            tags.remove(toRemove);
            return true;
        }
        return false;

    }

    public boolean addTag(String tagName, String tagContent) {
        Tags newTag = new Tags(tagName, tagContent);
        Main.mainObj.projectObj.tags.add(newTag);
        Tags.writeTagToFile("./", tagName, tagContent);
        return true;
    }

    public boolean removeTag(String tagContent) {

        int index = 0;
        int toRemove = -1;
        for (Tags t : tags) {
            if (t.getContent().equals(tagContent)) {
                toRemove = index;
            }
            index++;
        }
        if (toRemove != -1) {
            tags.remove(toRemove);
            return true;
        }
        return false;
    }

    /**
     * searches each doc for tag content and prints doc title
     * @return 
     * @param tag the tag that you are searching for?
     * @author Will panack
     */
    public String seachTag(Tags tag) {
        String tagC = tag.getContent();

        boolean match;
        String[] hitCount = new String[(Main.mainObj.projectObj.textFiles.size() * 2)];
        int hitIndex = 0;
        String returnString = tag.getName()+": "+tag.getContent()+"\n";
        
        for (Document d : Main.mainObj.projectObj.textFiles) {
            match = false;
            //track file name
            returnString+= d.getFileTitle()+": ";
            //track file's hit rate
            String doc = d.getContent().toLowerCase();
            String useTag = tagC.toLowerCase();
            int temp = StringUtils.countMatches(doc, useTag);
            returnString += temp+" --- ";
            returnString += Stats.findStats(doc,useTag)+ "% of Document\n";
            hitCount[hitIndex] = String.valueOf(temp);
            hitIndex++;

        }
        return returnString+"\n";

    }

    public boolean populate(String path) { //reads projectData.txt to fill saved project docs and tags

        String fileName = "projectData.txt";
        String line = null;
        boolean tf = true;

        try {

            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {

                String[] obj = line.split("---");
                if (obj[0].equals("+++")) {
                    tf = false;
                }
                if (tf) {
                    Document temp = new Document(obj[0], obj[1]);
                    temp.setContent(obj[2]);
                    this.textFiles.add(temp);
                } else if (obj.length < 2) {//in case it reads blank file somehow

                } else {
                    Tags tempt = new Tags(obj[0], obj[1]);
                    this.tags.add(tempt);
                }

            }

            bufferedReader.close();

        } catch (FileNotFoundException ex) {//creates projectData.txt if it doesn't already exist

            String fn = "projectData.txt";
            try {
                FileWriter fileWriter = new FileWriter(fn);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("\n");
                bufferedWriter.close();
            } catch (IOException exe) {
                System.out.println("Error writing to file");
                return false;
            }
        } catch (IOException ex) {
            System.out.println("Error reading file");
            return false;
        }
        return true;

    }

    public boolean saveProject() {
        //on save populate project textpaths and tags sepearted by line with only '+++'
        String fileName = "projectData.txt";

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Document tf : textFiles) {
                bufferedWriter.write(tf.getPath() + "---" + tf.getFileTitle() + "---" + tf.getContent() + "\n");
            }
            bufferedWriter.write("+++\n");
            for (Tags t : tags) {
                bufferedWriter.write(t.getName() + "---" + t.getContent() + "---" + t.getColor() + "\n");
            }
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file");
            return false;
        }
        return true;
    }

    public void listFiles() {
        for (Document a : this.textFiles) {
            System.out.println(a.getFileTitle());

        }
    }

    public String getPath() {
        //To change body of generated methods, choose Tools | Templates.
        return this.projectPath;
    }

    public ArrayList<Tags> getTags() {
        return this.tags;
    }
}
