package ru.job4j.pojo;

import java.time.LocalDate;
import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFio("Александров Александр Александрович");
        student.setGroup("Физмат");
        student.setDate(LocalDate.now());
        System.out.println("ФИО Студента: " + student.getFio());
        System.out.println("Группа: " + student.getGroup());
        System.out.println("Дата поступления: " + student.getDate());
    }
}
