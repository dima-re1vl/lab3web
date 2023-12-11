package com.udhtu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileWriter;
import java.io.IOException;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
//        String content = "Это строка, которую вы хотите записать в файл.";
//
//        try (FileWriter writer = new FileWriter("data.txt")) {
//            writer.write(content);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        SpringApplication.run(Main.class, args);
    }

}