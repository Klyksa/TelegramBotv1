package ru.skillfactorydemo.tgbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skillfactorydemo.tgbot.repository.StatsRepository;

import java.math.BigDecimal;

// Аннотация, обозначающая, что этот класс является Spring-сервисом
@Service
// Аннотация Lombok, автоматически генерирующая конструктор для обязательных (final) полей
@RequiredArgsConstructor
public class StatsService {

    // Поле для использования репозитория, управляющего статистическими данными
    private final StatsRepository statsRepository;

    /**
     * Метод для получения количества доходов, превышающих заданную сумму
     *
     * @param amount - сумма, с которой происходит сравнение
     * @return количество доходов, которые больше указанной суммы
     */
    public int getCountOfIncomesThatGreater(BigDecimal amount) {
        // Использует метод репозитория для получения количества доходов больше указанной суммы
        return statsRepository.getCountOfIncomesThatGreaterThan(amount);
    }
}