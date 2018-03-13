package ru.tds.files;

import java.io.File;
import java.util.Scanner;

/**
 * Класс, в котором реализован поиск текстовых файлов в файловой системе, начиная с заданного каталога.
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class ReadDirectory {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File pathDirectory = new File("E:\\");
        //pathDirectory = new File("/home/ita/Dropbox");

        System.out.println("Что искать? :");
        String messageForSearch = sc.nextLine();
        System.out.println("Мы ищем: " + messageForSearch);

        fileExplorer(pathDirectory, messageForSearch);
    }

    private static void fileExplorer(File pathDirectory, String messageForSearch) {
        try {
            File[] files = pathDirectory.listFiles();
            assert files != null;
            for (File file : files) {
                if (file.isFile() && file.getName().matches(".*\\.txt$")) {
                    new searchMessage(file, messageForSearch).start();
                }
                else if (file.isDirectory()) {
                    fileExplorer(file, messageForSearch);
                }
            }
        } catch (NullPointerException e) {
            //e.printStackTrace();
        }
    }
}
