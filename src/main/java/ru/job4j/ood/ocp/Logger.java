package ru.job4j.ood.ocp;

class Logger {

    public void log(String message, String level) {
        if (level.equals("INFO")) {
            System.out.println("INFO: " + message);
        } else if (level.equals("ERROR")) {
            System.err.println("ERROR: " + message);
        } else if (level.equals("DEBUG")) {
            System.out.println("DEBUG: " + message);
        }
    }
}

