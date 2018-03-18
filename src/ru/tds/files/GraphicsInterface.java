package ru.tds.files;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Класс, в котором с помощью графического интерфейса реализован
 * ввод исходного сообщения,которое нужно найти, выбор стартового
 * каталога для поиска, и запуск метода для поиска текста в html-файлах файловой системы.
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class GraphicsInterface extends JPanel implements ActionListener {

    private JLabel labelMessage;

    private JTextField textField;

    private JButton buttonForDirectory;

    private JButton submit;

    private JLabel labelSuccesful;

    private JButton openResultFile;

    private JTextArea textAreaResult;

    private String pathDirecory;

    private String input;

    GraphicsInterface() {

        labelMessage = new JLabel();
        labelMessage.setText("Введите слово для поиска:");
        add(labelMessage);

        textField = new JTextField(15);
        add(textField);

        buttonForDirectory = new JButton();
        buttonForDirectory.setText("Выбрать начальную директорию");
        buttonForDirectory.addActionListener(this);
        add(buttonForDirectory);

        submit = new JButton();
        submit.setText("Начать поиск");
        submit.addActionListener(this);
        add(submit);

        labelSuccesful = new JLabel();
        add(labelSuccesful);

        openResultFile = new JButton("Открыть result.txt");
        openResultFile.addActionListener(this);
        add(openResultFile);

        textAreaResult = new JTextArea();
        add(textAreaResult);
    }

    @Override
    public void actionPerformed(ActionEvent e) throws NullPointerException {

        if (e.getSource() == buttonForDirectory) {
            JFileChooser fileChooserDirectory = new JFileChooser();
            fileChooserDirectory.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int response = fileChooserDirectory.showOpenDialog(this);
            if (response == JFileChooser.APPROVE_OPTION) {
                pathDirecory = fileChooserDirectory.getSelectedFile().toString();
            }
        }

        if (e.getSource() == openResultFile) {
            JFileChooser fileChooserResult = new JFileChooser("C:\\Users\\dmitry\\IdeaProjects\\Files\\src\\ru\\tds\\");
            fileChooserResult.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            fileChooserResult.showOpenDialog(this);
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(fileChooserResult.getSelectedFile()));
                textAreaResult.read(bufferedReader, "");
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }

        if (e.getSource() == submit) {
            input = textField.getText();
            try {
                SearchFilesAndText.main(new File(pathDirecory), input);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            labelSuccesful.setText("Поиск успешно выполнен! Откройте файл result.txt");
        }
    }
}
