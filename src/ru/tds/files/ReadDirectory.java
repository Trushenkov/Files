package ru.tds.files;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Класс, в котором с помощью графического интерфейса реализован ввод исходного сообщения,
 * которое нужно найти, выбор стартового каталога для поиска, и, соответственно,
 * поиск текстовых файлов в файловой системе, согласно шаблону регулярного выражения, начиная с заданного каталога.
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class ReadDirectory extends JPanel implements ActionListener {

    private JLabel labelMessage;

    private JTextField textField;

    private JButton buttonForDirectory;

    private JButton submit;

    private JFileChooser fileChooser;

    private String pathDirecory;

    private String input;

    private JLabel label;

    ReadDirectory() {
        labelMessage = new JLabel();
        labelMessage.setText("Введите слово для поиска:");
        add(labelMessage);

        textField = new JTextField(15);
        textField.addActionListener(this);
        add(textField);

        buttonForDirectory = new JButton();
        buttonForDirectory.setText("Выбрать начальную директорию");
        buttonForDirectory.addActionListener(this);
        add(buttonForDirectory);

        submit = new JButton();
        submit.setText("Выполнить");
        submit.addActionListener(this);
        add(submit);

        label = new JLabel();
        add(label);
    }

    /**
     * Метод для поиска файлов с помощью регулярного выражения
     * и запуска потока для поиска заданного текста в найденных файлах.
     *
     * @param pathDirectory директория
     * @param messageForSearch сообщение для поиска
     */
    private static void fileExplorer(File pathDirectory, String messageForSearch) {
        try {
            File[] files = pathDirectory.listFiles();
            assert files != null;
            for (File file : files) {
                if (file.isFile() && file.getName().matches(".*\\.html$")) {
                    new searchMessage(file, messageForSearch).start();
                } else if (file.isDirectory()) {
                    fileExplorer(file, messageForSearch);
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == textField) {
            input = textField.getText();
        }
        if (e.getSource() == buttonForDirectory) {
            fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int response = fileChooser.showOpenDialog(this);
            if (response == JFileChooser.APPROVE_OPTION) {
                pathDirecory = fileChooser.getSelectedFile().toString();
                label.setText(pathDirecory);
            }
        }
        if (e.getSource() == submit) {
            input = textField.getText();
//            label.setText(input);
            fileExplorer(new File(pathDirecory), input);

        }
    }
}
