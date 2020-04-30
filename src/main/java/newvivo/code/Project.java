package newvivo.code;

import java.io.*;
import java.util.ArrayList;
import newvivo.Screens.Main;
import org.apache.commons.io.FileUtils;
import newvivo.code.XMLParse;
import org.apache.commons.lang3.StringUtils;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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
                        //this.tags.add(p.Parse(path + "\\" + content));
                        addTag(p.Parse(path + "\\" + content).getName(), p.Parse(path + "\\" + content).getContent());
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

    //unused function
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
        int tagIndex = 1;
        boolean duplicateTag = false;
        for(int i = 0; i < tags.size(); i++)
        {
            if(tags.get(i).tagName.equals(tagName))
            {
                tagIndex++;
                if(tags.get(i).tagContent.equals(tagContent))
                {
                    duplicateTag = true;
                }
            }
        }
   
        if(!duplicateTag && !tagName.equals("") && !tagContent.equals(""))
        {
            Tags newTag = new Tags(tagName, tagContent);
            this.tags.add(newTag);
            Tags.writeTagToFile(this.projectPath, tagName, tagContent, tagIndex);
        }
        return true;
    }

    //unused function
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

    //unused function
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

    //unused function
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
        updateTagsFromWordDocs();
        return this.tags;
    }
    
    public void updateTagsFromWordDocs() {
        String destDir = projectPath;
        String zipFilePath;
        for(int i = 0; i < textFiles.size(); i++)
        {
            zipFilePath = textFiles.get(i).getPath();
            unzip(zipFilePath, destDir);
            
            File dir = new File(destDir + "/customXml");
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {
              XMLParse p = new XMLParse();
              boolean duplicateTag;
              String tagName;
              String tagContent;
              for (File child : directoryListing) {
                // Do something with child
                if(child.isFile())
                {
                    tagName = p.Parse(child.getAbsolutePath()).getName();
                    tagContent = p.Parse(child.getAbsolutePath()).getContent();
                    duplicateTag = false;
                    for(int j = 0; j < tags.size(); j++)
                    {
                        if(tags.get(j).tagName.equals(tagName))
                        {
                            if(tags.get(j).tagContent.equals(tagContent))
                            {
                                duplicateTag = true;
                            }
                        }
                    }
                    if(!duplicateTag)
                    {
                        addTag(tagName, tagContent);
                    }
                }
              }
            } else {
              // Handle the case where dir is not really a directory.
              // Checking dir.isDirectory() above would not be sufficient
              // to avoid race conditions with another process that deletes
              // directories.
            }
            
        }
        //read in content from each XML
    }
    
    private static void unzip(String zipFilePath, String destDir) {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if(!dir.exists()) dir.mkdirs();
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            String[] arrcus;
            String[] item;
            boolean check1 = false;
            boolean check2 = false;
            while(ze != null){
                check1 = false;
                check2 = false;
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                arrcus = fileName.split("/",0);
                item = fileName.split("item",0);
                if(item.length > 1){
                     if(item[1].charAt(0)=='1'||item[1].charAt(0)=='2'||item[1].charAt(0)=='3'||item[1].charAt(0)=='4'||item[1].charAt(0)=='5'||item[1].charAt(0)=='6'||item[1].charAt(0)=='7'||item[1].charAt(0)=='8'||item[1].charAt(0)=='9'){check1 = true;}
                }
                if(arrcus[0].equals("customXml")){check2 = true;}
                //create directories for sub directories in zip
                if(check1 && check2){
                      new File(newFile.getParent()).mkdirs();
                      FileOutputStream fos = new FileOutputStream(newFile);
                      int len;
                      
                      while ((len = zis.read(buffer)) > 0) {
                           
                           fos.write(buffer, 0, len);
                      }
                      
                      fos.close();
                }
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
