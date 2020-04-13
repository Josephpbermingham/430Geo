/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newvivo.code;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;



/**
 *
 * @author Joseph Bermingham
 */
public class Document {

    private String content;
    private String path;
    private String fileTitle;

   
        public Document(String projectPath,String fileTitle) throws FileNotFoundException, IOException{

            XWPFDocument docx = new XWPFDocument(new FileInputStream("create_paragraph.docx"));

            //using XWPFWordExtractor Class
            XWPFWordExtractor we = new XWPFWordExtractor(docx);
            System.out.println(we.getText());
        }
         public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }
    
    }
