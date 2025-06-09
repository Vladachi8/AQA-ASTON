package lesson2_6.Student;

import java.util.*;

public class Student {
    private String name;
    private int age;
    private int course;
    private String group;
    private Map<String, Integer> grades;

    public Student(String name, int age, int course, String group, Map<String, Integer> grades) {
        this.name = name;
        this.age = age;
        this.course = course;
        this.group = group;
        this.grades = new HashMap<>(grades);
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getName() {
        return name;
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
        int count = 0;

        for (int grade : grades.values()) {
            sum += grade;
            count++;
        }
        double average = (double) sum / count;

        return Math.round(average * 10) / 10.0;
    }

    //Удаление студента со средним баллом ниже 5, взята 10и бальная система
    public static void deleteBadGradesStudent(Set<Student> students) {
        students.removeIf(student -> student.calculateAverageGrade() < 5);
    }

    public static void increaseCourseGoodGradesStudent(Set<Student> students) {
        for (Student student : students) {
            if (student.calculateAverageGrade() >= 5) {
                student.setCourse(student.getCourse() + 1);
            }
        }
    }
}
