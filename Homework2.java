/* Распарсить файл с логами из заданий, выполненных на семинаре, найти записи уровня INFO/DEBUG. 

// 1. В файле содержится строка с исходными данными в такой форме: 
// {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}
// Требуется на её основе построить и вывести на экран новую строку, в форме SQL запроса:
// SELECT * FROM students WHERE name = "Ivanov" AND country = "Russia" AND city = "Moscow";
// Для разбора строки используйте String.split. Сформируйте новую строку, используя StringBuilder. 
// Значения null не включаются в запрос.
import java.io.*;

public class Homework2 {

    public static void main(String[] args) {

        try (FileReader fr = new FileReader("Homework2/file_text.txt")) {
            StringBuilder str = new StringBuilder();
            int i;
            while ((i = fr.read()) != -1) {
                str.append((char) i);
            }
            str.delete(54, 72);
            str.deleteCharAt(1)
                    .deleteCharAt(5)
                    .deleteCharAt(16)
                    .deleteCharAt(23)
                    .deleteCharAt(34)
                    .deleteCharAt(38);
            String upgradeText = str.toString();

            upgradeText = upgradeText.replaceAll(",", "")
                    .replace("{", "")
                    .replace("}", "")
                    .replace(" ", " AND ")
                    .replace(":", " = ")
                    .replace(" \"age\" = \"null\"", "");
            try (FileWriter fw = new FileWriter("Homework2/new_file_text.txt", false)) {
                fw.write("SELECT * FROM students WHERE " + upgradeText + ";");
            }
        } catch (IOException ex) {
            System.out.println("erroreeee");
        }
    }
}  */



/* Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.


import java.util.logging.*;

public class Homework2 {
    private static final Logger logger = Logger.getLogger(Homework2.class.getName());

    public static void main(String[] args) throws Exception {
        Handler fileHandler = new FileHandler("Homework2/log.txt");
        SimpleFormatter simForm = new SimpleFormatter();
        fileHandler.setFormatter(simForm);
        // logger.log(Level.INFO, "ergerg");
        // logger.addHandler(fileHandler);
        // logger.info("Шаг выполнен!!!");

        int[] lineNumber = new int[] { 3, 18, 6, 5, 14, 7, 12, 1, 9 };

        for (int i = 0; i < lineNumber.length; i++) {
            for (int j = 0; j < lineNumber.length - 1; j++) {
                if (lineNumber[j] > lineNumber[j + 1]) {
                    int temp = lineNumber[j];
                    lineNumber[j] = lineNumber[j + 1];
                    lineNumber[j + 1] = temp;
                    logger.addHandler(fileHandler);
                    logger.info("Шаг выполнен!!!");
                    // logger.log(Level.INFO, "");
                } 
                else {
                    logger.addHandler(fileHandler);
                    logger.info("Шаг не выполнен!!!");
                    // logger.log(Level.INFO, "");
                }
            }
        }
        System.out.print("[ ");
        for (int i : lineNumber) {
            System.out.printf("%d ", i);

        }
        System.out.println("]");
    }
}  */


/* Дана json строка (можно сохранить в файл и читать из файла) [{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},
{"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}] 
Написать метод(ы), который распарсит json и, используя StringBuilder, создаст строки вида: Студент [фамилия] получил [оценка]
по предмету [предмет]. Пример вывода: Студент Иванов получил 5 по предмету Математика. Студент Петрова получил 4 по предмету Информатика.
Студент Краснов получил 5 по предмету Физика. 


import java.io.*;

public class Homework2 {
    public static void main(String[] args) { // throws Exception {

        try (BufferedReader BufReader = new BufferedReader(new FileReader("3_file_text.txt"))) {
            StringBuilder strBuild = new StringBuilder();
            String strRead;
            while ((strRead = BufReader.readLine()) != null) {
                strBuild.append(strRead);
            }
            strBuild.deleteCharAt(strBuild.length() - 1)
                    .deleteCharAt(strBuild.length() - 1)
                    .replace(0, 1, "  ");

            String upgradeText = strBuild.toString();

            upgradeText = upgradeText.replace("}", "\n").replace("{", "")
                    .replace("фамилия", "Студент")
                    .replace("оценка", "получил")
                    .replace("предмет", "по предмету")
                    .replace("\"", "")
                    .replace(":", " ")
                    .replace(",", " ");
            try (FileWriter fw = new FileWriter("3_new_file_text.txt", false)) {
                fw.write(upgradeText);
                System.out.println(upgradeText);
            }
        } catch (IOException ex) {
            System.out.println("erroreeee");
        }
    }
}  */