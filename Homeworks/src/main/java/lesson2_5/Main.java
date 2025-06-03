package lesson2_5;

public class Main {
    public static void main(String[] args) {
        String[][] correctMatrix = {
                {"1", "1", "1", "1"},
                {"2", "2", "2", "2"},
                {"5", "6", "7", "8"},
                {"22", "22", "22", "22"}
        };

        String[][] wrongRowsMatrix = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"}
        };

        String[][] wrongColumnsMatrix = {
                {"12", "21", "331"},
                {"52", "64", "74"},
                {"95", "10", "15"},
                {"14", "26", "35"}
        };

        String[][] invalidDataMatrix = {
                {"6", "4", "3", "4"},
                {"8", "7", "gg", "8"},
                {"88", "18", "bla", "12"},
                {"8", "9", "9", "199"}
        };

        doMatrix(correctMatrix);
        doMatrix(wrongRowsMatrix);
        doMatrix(wrongColumnsMatrix);
        doMatrix(invalidDataMatrix);
    }

    public static void doMatrix(String[][] matrix) {
        try {
            System.out.println("Сумма матрицы: " + ArrayChecker.sumMatrixEl(matrix));
        } catch (ArrayChecker.MyArraySizeException e) {
            System.err.println("Ошибка размера массива: " + e.getMessage());
        } catch (ArrayChecker.MyArrayDataException e) {
            System.err.println("Ошибка данных в массиве: " + e.getMessage());
        }
    }
}