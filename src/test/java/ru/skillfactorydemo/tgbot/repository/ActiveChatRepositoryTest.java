package ru.skillfactorydemo.tgbot.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.skillfactorydemo.tgbot.entity.ActiveChat;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// Аннотация указывает, что это тесты для JPA-репозиториев. Она включает конфигурацию
//, необходимую для работы с базой данных в тестовой среде.
@DataJpaTest
class ActiveChatRepositoryTest {

    // Внедряем ActiveChatRepository для использования в тестах
    @Autowired
    private ActiveChatRepository activeChatRepository;

    // Тест проверяет, что репозиторий может найти сохранённый объект ActiveChat
    @Test
    public void testRepo_found() {
        // Создаем новый объект ActiveChat и устанавливаем для него идентификатор
        final ActiveChat activeChat = new ActiveChat();
        activeChat.setChatId(12345L);

        // Сохраняем объект в репозиторий (памяти)
        activeChatRepository.save(activeChat);

        // Пытаемся найти объект по идентификатору
        Optional<ActiveChat> activeChatByChatId = activeChatRepository.findActiveChatByChatId(12345L);

        // Проверяем, что объект был найден
        assertTrue(activeChatByChatId.isPresent());
        // Убеждаемся, что идентификатор найденного объекта совпадает с ожидаемым
        assertEquals(12345L, activeChatByChatId.get().getChatId());
    }

    // Тест проверяет, что репозиторий не найдёт объект, если его нет в базе
    @Test
    public void testRepo_notFound() {
        // Создаем и сохраняем новый объект ActiveChat
        final ActiveChat activeChat = new ActiveChat();
        activeChat.setChatId(12345L);
        activeChatRepository.save(activeChat);

        // Пытаемся найти объект по другому (несуществующему) идентификатору
        Optional<ActiveChat> activeChatByChatId = activeChatRepository.findActiveChatByChatId(54321L);

        // Проверяем, что объект с таким идентификатором отсутствует
        assertFalse(activeChatByChatId.isPresent());
    }
}