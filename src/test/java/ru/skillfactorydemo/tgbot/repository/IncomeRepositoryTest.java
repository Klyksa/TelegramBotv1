package ru.skillfactorydemo.tgbot.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.skillfactorydemo.tgbot.entity.Income;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

// Аннотация указывает, что это тесты для JPA-репозиториев.
// Она настраивает окружение для тестирования сохранения и извлечения данных из базы данных
@DataJpaTest
class IncomeRepositoryTest {

    // Внедряем репозиторий для тестирования
    @Autowired
    private IncomeRepository incomeRepository;

    // Тест проверяет корректность сохранения и извлечения десяти записей
    @Test
    public void testRepo() {
        //noinspection StatementWithEmptyBody
        // Цикл for создаёт и сохраняет 10 новых объектов Income
        for (int i = 0; i < 10; i++, incomeRepository.save(new Income()));

        // Извлекаем все записи из базы данных
        final List<Income> found = incomeRepository.findAll();

        // Проверяем, что в репозитории находится 11 записей (включая возможную заранее существующую запись)
        assertEquals(11, found.size());
    }

    // Тест проверяет наличие записи с определённым идентификатором и её значения
    @Test
    public void testDataScripts() {
        // Ищем запись в базе данных по идентификатору 12345L
        Optional<Income> income = incomeRepository.findById(12345L);

        // Проверяем, что запись найдена
        assertTrue(income.isPresent());

        // Проверяем, что значение поля `income` совпадает с ожидаемым значением
        assertEquals(new BigDecimal("3000.00"), income.get().getIncome());
    }
}