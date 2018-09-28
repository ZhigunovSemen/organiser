package ru.zhigunov.study.organiser;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.nio.file.Files;

public class Main {

    public static void main(String[] args) {
        File file = new File("");
        System.out.println(file.getAbsolutePath());
        //Files.walk();
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("application-config.xml");
        new AppContext().run();
    }
}
