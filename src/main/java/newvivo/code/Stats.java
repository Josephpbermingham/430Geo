import java.util.*;

public class Stats{

   //finds and prints word stats in order of most duplicates
   public static void findStats(ArrayList<String> words, ArrayList<Integer> wcount, String text){
      int index = 0;
      boolean found = false;
      String[] arrWords = text.replaceAll("[^a-zA-Z ]", "").toLowerCase().split(" ", 0);
      
      for(String word : arrWords){
         index = 0;
         found = false;
         for(String w : words){
            if(word.equals(w)){
               found = true;
               wcount.set(index,wcount.get(index)+1);
            }
            index = index+1;
            
         }
         if(!found){
            words.add(word);
            wcount.add(1);
         }
      }
      
      index=0;
      int max = 0;
      for(Integer num: wcount){
         if(num>max){max = num;}
      }
      while(max>0){
         index=0;
         for(Integer num: wcount){
            if(num==max){System.out.print(words.get(index) + " - " + num + "\n");}
            index=index+1;
         }
         max=max-1;
      }
      
      
      
      
   
   }
   

   public static void main(String[] args){
      
      String testString = new String("I wonder how well this Will test work text and test test I test work works need to make sure this will have multiple usages of I and will to make sure it works.");
      ArrayList<String> words = new ArrayList<String>();
      ArrayList<Integer> wcount = new ArrayList<Integer>();
      
      findStats(words, wcount,testString);
      
   
   
   }

}