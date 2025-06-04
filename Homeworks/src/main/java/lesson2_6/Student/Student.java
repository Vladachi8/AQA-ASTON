package lesson2_6.Student;

import java.util.Map;

public class Student {
    private String name;
    private int age;
    private int course;
    private String group;
    private Map<String,Integer> grades;

    public Student(String name, int age, int course, String group, Map<String,Integer> grades) {
        this.name = name;
        this.age = age;
        this.course = course;
        this.group = group;
        this.grades = grades;
    }

    @Override
    public String toString() {
        return String.format(
                "Студент: %s, Возраст: %d, Курс: %s, Группа: %s, Оценки: %s",
                name, age, course, group, grades
        );
    }

    public double calculateAverageGrade() {
        if (grades.isEmpty()) {
            return 0.0;
        }

        int sum = 0;
        double averrage;
        for (int grade : grades.values()) {
            sum += grade;
        }
        averrage = sum / grades.size();

        return (double) Math.round(averrage * 10) / 10.0;
    }
}
