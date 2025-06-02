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