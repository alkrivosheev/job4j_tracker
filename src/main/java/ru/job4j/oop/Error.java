package ru.job4j.oop;

public class Error {

    private boolean active;
    private  int status;
    private String message;

    public Error() {

    }

    public void printInfo() {
        System.out.println("Active: " + active);
        System.out.println("Status: " + status);
        System.out.println("Message: " + message);
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public static void main(String[] args) {
        Error error1 = new Error();
        Error error2 = new Error(true, 5, "IO Error");
        Error error3 = new Error(false, 1, "Wrong received string");
        Error error4 = new Error(false, 0, "Buffer Empty");
        error1.printInfo();
        error2.printInfo();
        error3.printInfo();
        error4.printInfo();
    }
}
