package ru.skillfactorydemo.tgbot.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Аннотация показывает, что этот класс является тестовым и будет запускаться с контекстом Spring Boot
@SpringBootTest
// Аннотация для автоматической настройки MockMvc, чтобы можно было отправлять запросы к контроллерам
@AutoConfigureMockMvc
class CurrencyControllerTest {

    // Внедряем MockMvc, чтобы использовать его в тестах
    @Autowired
    private MockMvc mockMvc;

    // Тестовый метод для проверки эндпоинта, возвращающего все валюты
    @Test
    public void testWhenAskAboutAllCurrencies() throws Exception {
        // Выполняем GET-запрос к /getCurrencies и ожидаем, что статус ответа будет 200 OK
        mockMvc.perform(get("/getCurrencies"))
                .andExpect(status().isOk())
                .andDo(print()); // Печатаем детали запроса и ответа в консоль для отладки
    }

    // Тестовый метод для проверки эндпоинта, возвращающего конкретную валюту, например, USD
    @Test
    public void testWhenAskAboutOneCurrency() throws Exception {
        // Выполняем GET-запрос к /getCurrency/USD и ожидаем, что статус ответа будет 200 OK
        mockMvc.perform(get("/getCurrency/USD"))
                .andExpect(status().isOk())
                .andDo(print()); // Печатаем детали запроса и ответа в консоль для отладки
    }

}