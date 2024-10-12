package ru.skillfactorydemo.tgbot.service;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.skillfactorydemo.tgbot.repository.IncomeRepository;
import ru.skillfactorydemo.tgbot.repository.SpendRepository;

/**
 * Класс-тест для тестирования FinanceService
 */
// Аннотация указывает, что это тестовый класс Spring Boot
@SpringBootTest
// Указывает, что для всех тестов этого класса будет использоваться один и тот же экземпляр класса
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FinanceServiceTest {

    // Аннотация @InjectMocks сообщает Mockito, что нужно создать экземпляр
    // класса и внедрить в него зависимости, указанные с помощью @Mock
    @InjectMocks
    private FinanceService financeService;

    // Аннотация @Mock сообщает Mockito, что необходимо имитировать работу класса
    // SpendRepository, так как он используется в FinanceService
    @Mock
    private SpendRepository spendRepository;

    // Аннотация @Mock для имитации класса IncomeRepository, который также используется в FinanceService
    @Mock
    private IncomeRepository incomeRepository;

    // Метод, который выполняется перед каждым тестом. Используется для фиксации времени начала теста
    @BeforeEach
    public void beforeAll() {
        System.out.println(System.currentTimeMillis());
    }

    // Метод, который выполняется после каждого теста. Используется для фиксации времени завершения теста
    @AfterEach
    public void afterEach() {
        System.out.println(System.currentTimeMillis());
    }

    // Тестовый метод, проверяющий поведение addFinanceOperation при добавлении дохода
    @DisplayName("ADD_INCOME_test")
    @Test
    public void addFinanceOperationAddIncomeTest() {
        // Устанавливаем произвольное значение переменной для теста
        String price = "150.0";
        // Вызываем метод addFinanceOperation с параметрами для добавления дохода
        String message = financeService.addFinanceOperation("/addincome", price, 500L);
        // Проверяем, что метод возвращает ожидаемое сообщение об успешном добавлении дохода
        Assertions.assertEquals("Доход в размере " + price + " был успешно добавлен", message);
    }

    // Тестовый метод для проверки поведения addFinanceOperation при добавлении расхода
    @DisplayName("non_ADD_INCOME_test")
    @Test
    public void addFinanceOperationElseBranchTest() {
        // Устанавливаем произвольное значение переменной для теста
        String price = "200";
        // Вызываем метод addFinanceOperation с параметрами для добавления расхода
        String message = financeService.addFinanceOperation("/nan", price, 250L);
        // Проверяем, что метод возвращает ожидаемое сообщение об успешном добавлении расхода
        Assertions.assertEquals("Расход в размере " + price + " был успешно добавлен", message);
    }
}