package ru.skillfactorydemo.tgbot.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skillfactorydemo.tgbot.dto.ValuteCursOnDate;
import ru.skillfactorydemo.tgbot.service.CentralRussianBankService;
import ru.skillfactorydemo.tgbot.service.StatsService;

import java.math.BigDecimal;
import java.util.List;

@RestController // Аннотация, указывающая, что этот класс является REST-контроллером
@RequiredArgsConstructor // Аннотация Lombok, генерирующая конструктор с обязательными аргументами для финальных полей
public class CurrencyController {

    // Объявление финальных полей для зависимостей, которые будут внедрены через конструктор
    private final CentralRussianBankService centralRussianBankService;
    private final StatsService statsService;

    // Метод обрабатывает GET-запрос на получение курса всех валют на текущий день
    @GetMapping("/getCurrencies")
    @ApiOperation(value = "Получение курса всех валют на текущий день") // Описание операции для Swagger
    public List<ValuteCursOnDate> getValuteCursOnDate() throws Exception {
        // Вызов сервиса для получения списка всех курсов валют
        return centralRussianBankService.getCurrenciesFromCbr();
    }

    // Метод обрабатывает GET-запрос на получение курса определенной валюты
    @GetMapping("/getCurrency/{code}") // {code} - это переменная пути
    @ApiOperation(value = "Получение курса определенной валюты на текущий день") // Описание операции для Swagger
    public ValuteCursOnDate getCourseForCurrency(@PathVariable String code) throws Exception {
        // Вызов сервиса для получения курса валюты по коду
        return centralRussianBankService.getCourseForCurrency(code);
    }

    // Метод обрабатывает GET-запрос для получения статистики по пополнениям, которые превышают заданную сумму
    @GetMapping("/getStats")
    @ApiOperation(value = "Получение количества пополнений, которые превышают определенную сумму") // Описание операции для Swagger
    public int getStatAboutIncomesThatGreater(@RequestParam(value = "amount") BigDecimal amount) {
        // Вызов сервиса для получения количества пополнений, превышающих заданную сумму
        return statsService.getCountOfIncomesThatGreater(amount);
    }
}
