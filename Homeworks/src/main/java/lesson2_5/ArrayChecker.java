package lesson2_5;

public class ArrayChecker {

    public static class MyArraySizeException extends Exception {
        public MyArraySizeException(String message) {
            super(message);
        }
    }

    public static class MyArrayDataException extends Exception {
        public MyArrayDataException(int row, int col, String value) {
            super(String.format("Некорректные данные в ячейке [%d][%d]: '%s'", row, col, value));
        }
    }

    public static int sumMatrixEl(String[][] matrix) throws MyArraySizeException, MyArrayDataException {
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

        int sum = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                try {
                    sum += Integer.parseInt(matrix[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j, matrix[i][j]);
                }
            }
        }

        return sum;
    }
}