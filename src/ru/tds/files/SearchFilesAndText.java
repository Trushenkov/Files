package ru.tds.files;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Потоковый класс для поиска заданного текста в найденных с помощью
 * шаблона регулярного выражения файлах файловой системы, начиная с указанной директории.
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class SearchFilesAndText extends Thread {

    private File file;

    private String message;

    private static volatile BufferedWriter result;

    private static ArrayList<SearchFilesAndText> arrayListOfThreads;

    private static int numberOfThread = 0;

    private SearchFilesAndText(File file, String message) {
        this.file = file;
        this.message = message;
    }

    /**
     * Метод для записи информации о найденной строчке в текстовый файл.
     * Информация в виде:
     * 1. путь к файлу; 2.сообщение, которое нужно было найти; 3. номер строки, в которой найдено сообщение.
     *
     * @param str информация, которую нужно записать в текстовый файл
     */
    private synchronized void writeFoundedMessage(String str) {
        try {
            result.write(str + "\n");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Метод для поиска текстовых файлов в файловой системе,
     * согласно шаблону регулярного выражения, начиная
     * с заданного каталога и запуска потока для поиска
     * заданного текста в найденных файлах.
     *
     * @param pathDirectory    путь начальной директории
     * @param messageForSearch сообщение для поиска
     */
    private static void fileExplorer(File pathDirectory, String messageForSearch) {
        try {
            File[] files = pathDirectory.listFiles();
            assert files != null;
            for (File file : files) {
                if (file.isFile() && file.getName().matches(".*\\.txt$")) {

                    arrayListOfThreads.add(new SearchFilesAndText(file, messageForSearch));
                    arrayListOfThreads.get(numberOfThread).start();
                    numberOfThread++;

                } else if (file.isDirectory()) {
                    fileExplorer(file, messageForSearch);
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        String string;
        Pattern searchStr = Pattern.compile(message);
        Matcher mSearchStr;

        try (BufferedReader inFile = new BufferedReader(new FileReader(file))) {
            for (int i = 1; (string = inFile.readLine()) != null; i++) {

                mSearchStr = searchStr.matcher(string);

                if (mSearchStr.find()) {
                    writeFoundedMessage("Path: " + file.getPath() + "; Message for search: " + mSearchStr.group() +
                            "; Line : " + i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод, в котором запускается fileExplorer() - метод для поиска файлов.
     *
     * @param path    путь начальной директории
     * @param message сообщение для поиска
     */
    public static void main(File path, String message) throws IOException {

        result = new BufferedWriter(new FileWriter("src\\ru\\tds\\result.txt"));

        arrayListOfThreads = new ArrayList<>();

        fileExplorer(path, message);

        waitForDieThreads(arrayListOfThreads);

        result.close();
    }

    /**
     * Метод для ожидания, пока потоки, которые ищут сообщение в текстовых файлах, завершат свое выполнение.
     *
     * @param searchMessagesList arraylist, содерщащий потоки для поиска текста в файлах файловой системы.
     */
    private static void waitForDieThreads(ArrayList<SearchFilesAndText> searchMessagesList) {
        try {
            for (SearchFilesAndText thread : searchMessagesList) {
                if (thread.isAlive()) {
                    thread.join();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
