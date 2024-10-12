package ru.skillfactorydemo.tgbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skillfactorydemo.tgbot.entity.Income;
import ru.skillfactorydemo.tgbot.entity.Spend;
import ru.skillfactorydemo.tgbot.repository.IncomeRepository;
import ru.skillfactorydemo.tgbot.repository.SpendRepository;

import java.math.BigDecimal;

// Аннотация, указывающая, что класс является сервисом в архитектуре Spring
@Service
// Аннотация Lombok, автоматически генерирующая конструктор с обязательными (final) полями
@RequiredArgsConstructor
public class FinanceService {

    // Константа, определяющая команду для добавления дохода
    private static final String ADD_INCOME = "/addincome";

    // Объявление полей для взаимодействия с репозиториями доходов и расходов
    private final IncomeRepository incomeRepository;
    private final SpendRepository spendRepository;

    /**
     * Метод для обработки финансовых операций (добавление дохода или расхода).
     *
     * @param operationType Тип операции (доход или расход).
     * @param price Сумма операции.
     * @param chatId Идентификатор чата, связанный с операцией.
     * @return Сообщение о результате операции.
     */
    public String addFinanceOperation(String operationType, String price, Long chatId) {
        String message; // Переменная для хранения сообщения о результатах операции
        if (ADD_INCOME.equalsIgnoreCase(operationType)) {
            // Создание и сохранение объекта дохода, если тип операции - добавление дохода
            Income income = new Income();
            income.setChatId(chatId);
            income.setIncome(new BigDecimal(price)); // Устанавливаем сумму дохода
            incomeRepository.save(income); // Сохраняем объект дохода
            message = "Доход в размере " + price + " был успешно добавлен";
        } else {
            // Создание и сохранение объекта расхода, если тип операции - другой (расход)
            Spend spend = new Spend();
            spend.setChatId(chatId);
            spend.setSpend(new BigDecimal(price)); // Устанавливаем сумму расхода
            spendRepository.save(spend); // Сохраняем объект расхода
            message = "Расход в размере " + price + " был успешно добавлен";
        }
        return message; // Возвращаем сообщение о результате операции
    }
}