package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {

        String filePath = ".//.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        retriveFileName("basejava/src/ru/javawebinar/basejava/storage");

    }

    private static void retriveFileName(String root) {
        File dir = new File("../" + root);
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println("  " + name);
                retriveFileName("../" + name);
            }
        }
    }

}
