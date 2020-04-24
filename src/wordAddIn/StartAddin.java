import java.util.*;
import java.io.*;
import java.lang.Runtime;

class StartAddin{
   public static void main(String[] Args) 
   {
      try 
      {
		   ProcessBuilder	pb	= new	ProcessBuilder("cmd.exe", "/c", "cd \"My Office Add-in\" && npm start");
		   Process p =	pb.start();
         
         BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
         
      }
      catch(Throwable t)
      {
         t.printStackTrace();
      }
      return;
   }
}