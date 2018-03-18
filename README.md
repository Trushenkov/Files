# Поиск текста в текстовых файлах файловой системы
## Реализован 3 этап программы(Графический интерфейс):
1. Ввод сообщения, которое нужно найти.
2. Выбор начального каталога для поиска.
3. Поиск html-файлов в файловой системы согласно регулярному выражению, начиная с заданного каталога.
4. Запуск метода для поиска введенного сообщения в найденных html-файлах файловой системы.
5. Информирование пользователя о выполнении операции.
6. Запись результатов работы программы (путь к файлу, искомое сообщение, номер строки) в результирующий текстовый файл.
7. Возможность открыть результирующий файл result.txt в окне программы.

 ### <a href="https://github.com/Trushenkov/Files/blob/master/src/ru/tds/files/MainWindow.java"> Mainwindow</a> - главное окно программы <br>
 ### <a href="https://github.com/Trushenkov/Files/blob/master/src/ru/tds/files/GraphicsInterface.java"> GraphicsInterface </a> - класс, в котором реализован графический интерфейс главного окна программы
 
 ### <a href="https://github.com/Trushenkov/Files/blob/master/src/ru/tds/files/SearchFilesAndText.java"> SearchFilesAndText </a> - класс, в котором реализован поиск html-файлов, поиск заданного сообщения в найденных html-файлах, а также запись информации о найденных сообщениях в результирующий файл <a href="https://github.com/Trushenkov/Files/blob/master/src/ru/tds/result.txt"> result.txt </a> 
