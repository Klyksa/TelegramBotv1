package ru.skillfactorydemo.tgbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// Аннотация для включения функциональности планировщика задач
@EnableScheduling
// Аннотация, обозначающая, что это главный класс приложения Spring Boot
@SpringBootApplication
public class TgbotApplication {

    /**
     * Главный метод, служащий входной точкой для приложения.
     *
     * @param args Аргументы командной строки, переданные при запуске приложения
     */
    public static void main(String[] args) {
        // Метод, запускающий приложение Spring Boot
        SpringApplication.run(TgbotApplication.class, args);
    }

}