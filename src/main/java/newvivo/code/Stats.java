package newvivo.code;

import java.text.DecimalFormat;
import java.util.*;
import org.apache.commons.lang3.StringUtils;

public class Stats {

    /**
     * takes in a string of text, Returns what percent of the file is the tag
     *
     * @param text the contents of the document
     * @param tagContent the contents of the tag. you are finding the hit rate
     * of this.
     * @return
     */
    public static String findStats(String text, String tagContent) {
        int index = 0;
        boolean found = false;
        int textCount = countWords(text);
        int tagCount = countWords(tagContent);

        text = text.toLowerCase();
        double matches = StringUtils.countMatches(text, tagContent);
        double ret = (matches * tagCount) / textCount;
        DecimalFormat df = new DecimalFormat("###.###");        
        return df.format(ret);
    }

    public static int countWords(String s) {
        int wordCount = 0;
        boolean word = false;
        int endOfLine = s.length() - 1;
        for (int i = 0; i < s.length(); i++) {
            // if the char is a letter, word = true.
            if (Character.isLetter(s.charAt(i)) && i != endOfLine) {
                word = true;
                // if char isn't a letter and there have been letters before,
                // counter goes up.
            } else if (!Character.isLetter(s.charAt(i)) && word) {
                wordCount++;
                word = false;
                // last word of String; if it doesn't end with a non letter, it
                // wouldn't count without this.
            } else if (Character.isLetter(s.charAt(i)) && i == endOfLine) {
                wordCount++;
            }
        }
        return wordCount;
    }

    public static void main(String[] args) {

        String testString = new String("I wonder how well this Will test work text and test test I test work works need to make sure this will have multiple usages of I and will to make sure it works.");
        ArrayList<String> words = new ArrayList<String>();
        ArrayList<Integer> wcount = new ArrayList<Integer>();

    }

}
