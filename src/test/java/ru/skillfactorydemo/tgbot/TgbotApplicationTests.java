package ru.skillfactorydemo.tgbot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;


@SpringBootTest
@AutoConfigureTestDatabase // Эта аннотация позволяет использовать встроенную H2 базу для тестов
class TgbotApplicationTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void contextLoads() {
		// Этот тест проверяет, что контекст приложения загружается без ошибок
	}
}
