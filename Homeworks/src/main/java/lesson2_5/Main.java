package lesson2_5;

public class Main {
    public static void main(String[] args) {
        try {
            String[][] correctArray = {
                    {"1", "2", "3", "4"},
                    {"5", "6", "7", "8"},
                    {"9", "10", "11", "12"},
                    {"13", "14", "15", "16"}
            };
            ArrayChecker.checkArraySize(correctArray);
            System.out.println("Массив правильного размера");

            String[][] wrongArray = {
                    {"1", "2", "3", "4"},
                    {"5", "6", "7", "8"},
                    {"9", "10", "11", "12"}
            };
            ArrayChecker.checkArraySize(wrongArray);
        } catch (ArrayChecker.MyArraySizeException e) {
            System.err.println("Ошибка размера массива: " + e.getMessage());
        }
    }
}
