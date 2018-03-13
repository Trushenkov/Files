package ru.tds.files;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Потоковый класс для поиска заданного текста в текстовых файлах файловой системы.
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class searchMessage extends Thread {

    private File file;
    private String message;

    searchMessage(File file, String message) {
        this.file = file;
        this.message = message;
    }

    @Override
    public void run() {

        String string;

        Pattern searchStr = Pattern.compile(message);
        Matcher mSearchStr;

        try (BufferedReader inFile = new BufferedReader(new FileReader(file));
             BufferedWriter outFile = new BufferedWriter(new FileWriter("src\\ru\\tds\\files\\outFile.txt",false))) {
            for (int i = 1; (string = inFile.readLine()) != null;i++) {

                mSearchStr = searchStr.matcher(string);

                if (mSearchStr.find()) {
                    System.out.println("Path: " + file.getPath() + "; Message for search: " + mSearchStr.group() + "; Line : "+ i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
