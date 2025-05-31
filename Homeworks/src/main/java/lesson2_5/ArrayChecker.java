package lesson2_5;

public class ArrayChecker {

    public static class MyArraySizeException extends Exception {
        public MyArraySizeException(String message) {
            super(message);
        }
    }

    public static void checkArraySize(String[][] matrix) throws MyArraySizeException {
        if (matrix == null) {
            throw new MyArraySizeException("Массив не может быть null");
        }

        if (matrix.length != 4) {
            throw new MyArraySizeException("Массив не может иметь " + matrix.length + " строку(ки)");
        }

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] == null || matrix[i].length != 4) {
                throw new MyArraySizeException("Массив не может содержать " + matrix[i].length + " столбец(ца)");
            }
        }
    }
}
//Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4. При подаче массива другого размера необходимо бросить исключение MyArraySizeException.
//
//Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать. Если в каком-то элементе массива преобразование не удалось
//        (например, в ячейке лежит символ или текст вместо числа), должно быть брошено исключение MyArrayDataException с детализацией, в какой именно ячейке лежат неверные данные.
//
//В методе main() вызвать полученный метод, обработать возможные исключения MyArraySizeException и MyArrayDataException и вывести результат расчета.
//
//Напишите код для генерации и поимки ArrayIndexOutOfBoundsException.