package lesson2_6.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> grades1 = Map.of(
                "Математика", 7,
                "Русский язык", 6,
                "Физика", 9,
                "История", 10,
                "Физкультура", 8,
                "Английский язык", 6
        );
        Map<String, Integer> grades2 = Map.of(
                "Математика", 4,
                "Русский язык", 5,
                "Физика", 4,
                "История", 5,
                "Физкультура", 6,
                "Английский язык", 7
        );
        Map<String, Integer> grades3 = Map.of(
                "Математика", 4,
                "Русский язык", 7,
                "Физика", 9,
                "История", 4,
                "Физкультура", 4,
                "Английский язык", 4
        );
        Map<String, Integer> grades4 = Map.of(
                "Математика", 6,
                "Русский язык", 8,
                "Физика", 9,
                "История", 10,
                "Физкультура", 6,
                "Английский язык", 7
        );
        Map<String, Integer> grades5 = Map.of(
                "Математика", 8,
                "Русский язык", 9,
                "Физика", 9,
                "История", 10,
                "Физкультура", 8,
                "Английский язык", 9
        );

        List<Student> students = new ArrayList<>();

        Student student1 = new Student("Артем", 22, 4, "35pm", grades1);
        Student student2 = new Student("Ирина", 20, 2, "12pk", grades2);
        Student student3 = new Student("Сергей", 23, 3, "25pm", grades3);
        Student student4 = new Student("Иван", 22, 4, "35pm", grades4);
        Student student5 = new Student("Екатерина", 25, 5, "65pm", grades5);

        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);

        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println(student1.calculateAverageGrade());
    }
}
