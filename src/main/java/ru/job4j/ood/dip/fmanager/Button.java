package ru.job4j.ood.dip.fmanager;

public class Button {
    private FileManager fileManager;

    public Button() {

        fileManager = new FileManager();
    }

    public void onClick() {

        fileManager.saveFile("Button clicked");

    }
}
