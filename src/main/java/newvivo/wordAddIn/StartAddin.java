package newvivo.wordAddIn;

import java.util.*;
import java.io.*;
import java.lang.Runtime;

public class StartAddin {

    public static void main(String[] Args) {
        try {
            System.out.println("Started");
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "cd \"My Office Add-in\" && npm start");
            Process p = pb.start();
            System.out.println("middle");

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Throwable t) {
            t.printStackTrace();
        }
        return;
    }

    public static void start() {
        try {
            System.out.println("Started");
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "cd \"My Office Add-in\" && npm start");
            Process p = pb.start();
            System.out.println("middle");

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Throwable t) {
            t.printStackTrace();
        }
        return;
    }
}
