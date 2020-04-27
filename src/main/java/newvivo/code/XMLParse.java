/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newvivo.code;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import newvivo.Screens.Main;

/**
 *
 * @author Jazeb R
 */
public class XMLParse {

    /**
     * @param arg the string name of the file.
     * @author Jazeb R
     * @return an array list of tags
     */
    public void Parse(String arg) {
        try {
            //creating a constructor of file class and parsing an XML file  
            File file = new File("./" + arg);
            //an instance of factory that gives a document builder  
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //an instance of builder to parse the specified xml file  
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("TagData");
            // nodeList is not iterable, so we are using for loop  

            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    Tags tag = new Tags(eElement.getElementsByTagName("TagName").item(0).getTextContent(),
                            eElement.getElementsByTagName("TagName").item(0).getTextContent());
                    
                    Main.mainObj.projectObj.addTag(eElement.getElementsByTagName("TagName").item(0).getTextContent(),
                            eElement.getElementsByTagName("TagName").item(0).getTextContent());
                    
                    System.out.println("Tag Name: " + eElement.getElementsByTagName("TagName").item(0).getTextContent());
                    System.out.println("Tag Text: " + eElement.getElementsByTagName("TaggedText").item(0).getTextContent());
                    //System.out.println("TagColor: "+ eElement.getElementsByTagName("TagColor").item(0).getTextContent()); 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
