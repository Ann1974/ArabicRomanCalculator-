package lesson;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("\nВведите целое число от 1 до 10 включительно, затем требуемую операцию " + "(+, -, /, *)"
                + " и далее второе целое число от 1 до 10 включительно:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));


    }

    public static String calc(String input) throws Exception {

        HashMap<String, Integer> romKey = new HashMap<>();
        romKey.put("I", 1);
        romKey.put("II", 2);
        romKey.put("III", 3);
        romKey.put("IV", 4);
        romKey.put("V", 5);
        romKey.put("VI", 6);
        romKey.put("VII", 7);
        romKey.put("VIII", 8);
        romKey.put("IX", 9);
        romKey.put("X", 10);

        String[] incomingStringToArray = input.replaceAll("\\s+", "").split("\\b");
        // Получить массив строк, убрав пробелы и разделить по границе  каждой строки
        if (romKey.containsKey(incomingStringToArray[0]) == romKey.containsKey(incomingStringToArray[2])) {
            int operandByIndexZero;
            int operandByIndexTwo;
            if (incomingStringToArray.length != 3) { // Проверка длины массива
                throw new Exception("Введите не  более двух операторов");
            }
            if (romKey.containsKey(incomingStringToArray[0]) && romKey.containsKey(incomingStringToArray[2])) {
                operandByIndexZero = romKey.get(incomingStringToArray[0]);
                operandByIndexTwo = romKey.get(incomingStringToArray[2]);

            } else { // Иначе перевод строки "7"...  в int
                operandByIndexZero = Integer.parseInt(incomingStringToArray[0]);
                operandByIndexTwo = Integer.parseInt(incomingStringToArray[2]);
            }
            if ((operandByIndexZero < 0 || operandByIndexZero > 10) || (operandByIndexTwo < 0 || operandByIndexTwo > 10)) {
                throw new Exception("Введите число от 1 до 10 включительно"); // ограничения
            }
            int result;// Выбор и выполнение мат. операции ->
            result = switch (incomingStringToArray[1]) {
                case "+" -> operandByIndexZero + operandByIndexTwo;
                case "-" -> operandByIndexZero - operandByIndexTwo;
                case "*" -> operandByIndexZero * operandByIndexTwo;
                case "/" -> operandByIndexZero / operandByIndexTwo;
                default -> throw new Exception("Введите нужную операцию +, -, /, *");
            };
            if (romKey.containsKey(incomingStringToArray[0]) && romKey.containsKey(incomingStringToArray[2])) {
                if (result <= 0) { // для римских чисел != 0 или 0
                    throw new Exception("Результат вычисления меньше или равен 0");
                }
            }
            if (romKey.containsKey(incomingStringToArray[0]) && romKey.containsKey(incomingStringToArray[2])) {
                return String.valueOf((FinishArabicToRoman.getFinish(String.valueOf(result))));
                // результат для римских переводим в римскую систему счисления
            } else {
                String total = Integer.toString(result);// Перевод int в String для арабских
                return total;
            }

        } else {
            throw new Exception("Неверные форматы чисел");
        }
    }
}
