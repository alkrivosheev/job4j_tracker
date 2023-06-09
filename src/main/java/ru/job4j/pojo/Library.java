package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book tmp;
        Book dataStruct = new Book("Data Structures in Java", 248);
        Book businessApp = new Book("Building Modern Business Applications", 185);
        Book filosofpyJava = new Book("Bryus_Ekkel_-_Filosofia_Java_4-e_polnoe_izdanie", 1170);
        Book cleanCode = new Book("Clean code", 872);
        Book[] books = new Book[4];
        books[0] = dataStruct;
        books[1] = businessApp;
        books[2] = filosofpyJava;
        books[3] = cleanCode;
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i].getName());
        }
        tmp = books[0];
        books[0] = books[3];
        books[3] = tmp;
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i].getName());
        }
        for (int i = 0; i < books.length; i++) {
            if ("Clean code".equals(books[i].getName())) {
                System.out.println(books[i].getName());
            }
        }

    }
}
