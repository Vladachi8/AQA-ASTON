package lesson2_6.Students;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> marks1 = new ArrayList<>(List.of(6,7,9,10,5,7));
        ArrayList<Integer> marks2 = new ArrayList<>(List.of(4,5,4,5,6,7));
        ArrayList<Integer> marks3 = new ArrayList<>(List.of(4,7,9,4,4,4));
        ArrayList<Integer> marks4 = new ArrayList<>(List.of(6,8,9,10,6,7));
        ArrayList<Integer> marks5 = new ArrayList<>(List.of(8,9,9,10,8,9));
        Students student1 = new Students("Артем", 22, 4, "35pm", marks1);
        Students student2 = new Students("Ирина", 20, 2, "12pk", marks2);
        Students student3 = new Students("Сергей", 23, 3, "25pm", marks3);
        Students student4 = new Students("Иван", 22, 4, "35pm", marks4);
        Students student5 = new Students("Екатерина", 25, 5, "65pm", marks5);
    }
}
