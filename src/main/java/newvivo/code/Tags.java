/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newvivo.code;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import newvivo.Screens.Main;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Jazeb R
 */
public class Tags {

    String tagContent;
    String tagName;
    String tagColor;//too much effort on the word add in side

    public Tags(String name, String content) {
        tagName = name;
        tagContent = content;
    }

    /**
     * @param projectPath the path into the project folder needs to be sent from
     * the GUI
     * @param tagName the name that this tag should go by.
     * @param taggedText the text content that this tag should be looking for
     * @author Joseph Bermingham
     */
    public static boolean writeTagToFile(String projectPath, String tagName, String taggedText) {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement("TagData");
            document.appendChild(root);

            // TagName Element
            Element XMLTagName = document.createElement("TagName");
            XMLTagName.appendChild(document.createTextNode(tagName));
            root.appendChild(XMLTagName);

            Element XMLTagData = document.createElement("TaggedText");
            XMLTagData.appendChild(document.createTextNode(taggedText));
            root.appendChild(XMLTagData);

            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            File file = new File(Main.mainObj.projectObj.getPath() + "/" + tagName + ".xml");
            boolean exists = file.exists();
            try {
                if (!exists) {
                    file.createNewFile();
                }
            }catch(IOException ex){
                System.out.println(ex);
                System.out.println("Shit broke yo");

            }
            StreamResult streamResult = new StreamResult(file);

            // If you use
            // StreamResult result = new StreamResult(System.out);
            // the output will be pushed to the standard output ...
            // You can use that for debugging 
            transformer.transform(domSource, streamResult);

            System.out.println("Done creating XML File");

        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
            return false;
        }
        return true;
    }

    public String getName() {
        return tagName;
    }

    public String getContent() {
        return tagContent;
    }

    public String getColor() {
        return tagColor;
    }

    public void setName(String newName) {
        tagName = newName;
    }

    public void setContent(String newContent) {
        tagContent = newContent;
    }

    public void setColor(String newColor) {
        tagColor = newColor;
    }

    public static void main(String args[]) {
       // Tags tags = new Tags("This", "This", "This");
        //tags.writeTagToFile("This", "This", "This");
    }
}
