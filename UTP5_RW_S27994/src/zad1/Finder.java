/**
 *
 *  @author Reguła Wojciech S27994
 *
 */

package zad1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finder {

    private String fileContent;

    public Finder(String fname) throws IOException {
        fileContent = "";
        BufferedReader reader = new BufferedReader(new FileReader(fname));
        fileContent = reader.lines().reduce("", String::concat).replaceAll("\\s", "").trim();
    }

    int getIfCount() {
        int result = 0;
        Pattern pattern = Pattern.compile("(?<!(/\\*)|\"|/{2})if\\(");
        Matcher matcher = pattern.matcher(fileContent);
        while (!matcher.hitEnd()) if (matcher.find()) result++;
        return result;
    }

    int getStringCount(String wordToMach) {
        int result = 0;
        System.out.println(fileContent);
        Pattern pattern = Pattern.compile(wordToMach);
        Matcher matcher = pattern.matcher(fileContent);
        while (!matcher.hitEnd()) if (matcher.find()) result++;
        return result;
    }
}
