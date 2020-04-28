/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newvivo.code;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public Document(String projectPath, String fileTitle) throws FileNotFoundException, IOException {
        this.fileTitle = fileTitle;
        path = projectPath + "\\" + fileTitle;

        XWPFDocument docx = new XWPFDocument(new FileInputStream(projectPath + "\\" + fileTitle));

        //using XWPFWordExtractor Class
        XWPFWordExtractor we = new XWPFWordExtractor(docx);
        content = we.getText();
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

    public static void main(String args[]) {
        String path = System.getProperty("user.dir") + "\\Projects\\temp";
        try {
            Document test = new Document(path, "TestResume.docx");
            System.out.println(test.getContent());
        } catch (IOException ex) {
            Logger.getLogger(Document.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
