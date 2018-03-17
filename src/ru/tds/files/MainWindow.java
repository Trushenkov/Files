package ru.tds.files;

import javax.swing.*;

/**
 * Класс для запуска окна программы.
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class MainWindow extends JFrame {

    private MainWindow(){
        setTitle("Поиск текста в текстовых файла файловой системы");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(350,350);
        setLocation(400,400);
        add(new ReadDirectory());
        setVisible(true);
        setResizable(false);
    }


    public static void main(String[] args) {
        MainWindow mw = new MainWindow();
    }

}
