/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newvivo.code;
import java.util.*;
/**
 *
 * @author Jazeb R
 */
public class Tags {
    String tagContent;
    String tagName;
    String tagColor;
    
    public Tags(String name, String content, String color){
    tagName = name;
    tagContent = content;
    tagColor = color;
    }
    public String getName(){
     return tagName;   
    }
    public String getContent(){
        return tagContent;
    }
    public String getColor(){
        return tagColor;
    }
    public void setName(String newName){
        tagName = newName;
    }
    public void setContent(String newContent){
        tagContent = newContent;
    }
    public void setColor(String newColor){
        tagColor = newColor;
    }
}

