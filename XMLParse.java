/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newvivo;
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.DocumentBuilder;  
import org.w3c.dom.Document;  
import org.w3c.dom.NodeList;  
import org.w3c.dom.Node;  
import org.w3c.dom.Element;  
import java.io.File; 
/**
 *
 * @author Jazeb R
 */
public class XMLParse {
    public void Parse(){  
        try   {  
            //creating a constructor of file class and parsing an XML file  
            File file = new File(Main.mainObject.projectObj.getPath());  
            //an instance of factory that gives a document builder  
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
            //an instance of builder to parse the specified xml file  
            DocumentBuilder db = dbf.newDocumentBuilder();  
            Document doc = db.parse(file);  
            doc.getDocumentElement().normalize();  
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());  
            NodeList nodeList = doc.getElementsByTagName("TagData");  
            // nodeList is not iterable, so we are using for loop  
            for (int itr = 0; itr < nodeList.getLength(); itr++)   {  
                Node node = nodeList.item(itr);  
                System.out.println("\nNode Name :" + node.getNodeName());  
                if (node.getNodeType() == Node.ELEMENT_NODE)   {  
                Element eElement = (Element) node;  
                System.out.println("Tag Name: "+ eElement.getElementsByTagName("TagName").item(0).getTextContent());  
                System.out.println("Tag Text: "+ eElement.getElementsByTagName("TaggedText").item(0).getTextContent());  
                //System.out.println("TagColor: "+ eElement.getElementsByTagName("TagColor").item(0).getTextContent()); 
                }  
            }  
        }   
        catch (Exception e)   {  
            e.printStackTrace();  
        }  
    }  
}
