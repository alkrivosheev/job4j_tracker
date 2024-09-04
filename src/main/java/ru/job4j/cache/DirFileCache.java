package ru.job4j.cache;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        Path filePath = Paths.get(cachingDir, "//", key);
        String text = null;
        if (ifFileExist(filePath)) {
            try {
                text = Files.readString(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return text;
    }

    private boolean ifFileExist(Path filePath) {
        if (!filePath.toFile().exists()) {
            System.out.println("Файл не найден");
            return false;
        }
        try {
            if (!filePath.toFile().getCanonicalPath().equals(filePath.toString())) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}