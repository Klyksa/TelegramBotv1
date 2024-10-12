package ru.skillfactorydemo.tgbot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

// Аннотация Spring, указывающая на то, что класс является репозиторием
@Repository
// Аннотация Lombok, автоматически генерирующая конструктор с обязательными (final) полями
@RequiredArgsConstructor
public class StatsRepository {

    // Объявление полей для работы с базой данных через JDBC-шаблоны
    private final JdbcTemplate jdbcTemplate; // Шаблон для выполнения SQL-запросов без именованных параметров
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate; // Шаблон для выполнения SQL-запросов с именованными параметрами

    // Метод для получения количества доходов, превышающих заданную сумму
    public int getCountOfIncomesThatGreaterThan(BigDecimal amount) {
        // Создаём карту параметров, где храним значение для переменной :amount
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("amount", amount); // Устанавливаем значение параметра

        // Выполняем SQL-запрос с использованием именованных параметров
        return namedParameterJdbcTemplate.queryForObject(
                "SELECT count(*) FROM income_table WHERE INCOME > :amount",
                parameters,
                new StatsRowMapper()
        );
    }

    // Внутренний класс, реализующий интерфейс RowMapper для преобразования данных из ResultSet в Integer
    private static final class StatsRowMapper implements RowMapper<Integer> {
        @Override
        public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getInt("COUNT"); // Получаем целочисленное значение из результата
        }
    }
}