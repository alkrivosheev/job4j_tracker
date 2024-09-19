package ru.job4j.ood.ocp;

public class MainLogger {

    public static void main(String[] args) {
        Logger logger = new Logger();
        logger.log("This is an info message", "INFO");
        logger.log("This is an error message", "ERROR");
        logger.log("This is a debug message", "DEBUG");
    }
}
