package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class GradeFile {

    // Перевірюваний виняток обробляється в самому методі через try-catch
    public static void open(String fileName) {
        try {
            FileReader reader = new FileReader(fileName);
            System.out.println("    try: файл " + fileName + " відкрито");
            reader.close();
        } catch (IOException e) {
            System.out.println("    catch (IOException): " + e);
        } catch (Exception e) {
            System.out.println("    catch (Exception): " + e);
        } finally {
            System.out.println("    finally: виконано");
        }
    }

    // Перевірюваний виняток передається методу, який викликав openWithThrows()
    public static void openWithThrows(String fileName) throws IOException {
        FileReader reader = new FileReader(fileName);
        System.out.println("    файл " + fileName + " відкрито");
        reader.close();
    }

    public static void writeWithoutClose(String fileName, String[] lines) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, StandardCharsets.UTF_8));
        for (String line : lines) {
            writer.write(line);
            writer.newLine();
        }
        // close() не викликано, рядки залишилися в буфері writer
    }

    public static void writeWithResources(String fileName, String[] lines) throws IOException {
        // writer закривається автоматично після виходу з блоку try
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, StandardCharsets.UTF_8))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public static void read(String fileName, Charset charset) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName, charset))) {
            String line = reader.readLine();
            if (line == null) {
                System.out.println("    (файл порожній)");
            }
            while (line != null) {
                System.out.println("    " + line);
                line = reader.readLine();
            }
        }
    }
}
