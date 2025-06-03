package lesson2_6.Students;

import java.util.ArrayList;

public class Students {
    private String name;
    private int age;
    private int course;
    private String group;
    private ArrayList<Integer> marks;

    public Students(String name, int age, int course, String group, ArrayList<Integer> marks) {
        this.name = name;
        this.age = age;
        this.course = course;
        this.group = group;
        this.marks = marks;
    }
}
