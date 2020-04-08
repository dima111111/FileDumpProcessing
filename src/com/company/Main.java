package com.company;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    /**
     * Http/Https patern
     */
    public static String httpPatern = "http[s]?:/(?:/[^/\0]+){1,}(?:/[\\w.-]+)[/]{0,1}\0";

    /**
     * Process file dump and write links to result file
     * @param filepath
     */
    public static void readProcessFile(String filepath)
    {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filepath));
            PrintWriter writer = new PrintWriter("result.txt", "UTF-8");
            String currentLine = null;
            currentLine = reader.readLine();
            while (currentLine != null) {
                findHttpPattern(currentLine, writer);
                currentLine = reader.readLine();
            }

            writer.close();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Find http links in string and write to new file
     * @param text
     * @param writer
     */
    public static void findHttpPattern(String text, PrintWriter writer)
    {
        Pattern pattern = Pattern.compile(httpPatern);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            int start=matcher.start();
            int end=matcher.end() - 1; // without end symbol
            writer.println(text.substring(start, end));
        }
    }

    public static void main(String[] args) {
        String file = "src/com/company/chrome.DMP";
        readProcessFile(file);
    }
}
