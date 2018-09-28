package ru.zhigunov.study.organiser;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        File file = new File("");
        System.out.println(file.getAbsolutePath());
        //Files.walk();
        ApplicationContextProvider applicationContextProvider = new ApplicationContextProvider();
//        ApplicationContext applicationContext =
//                new ClassPathXmlApplicationContext("application-config.xml");
        new Organiser().run();
    }
}
