package ru.skillfactorydemo.tgbot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillfactorydemo.tgbot.entity.ActiveChat;

import java.util.Optional;

// Аннотация Spring, указывающая, что интерфейс является репозиторием для доступа к данным
@Repository

public interface ActiveChatRepository extends JpaRepository<ActiveChat, Long> {

    // Метод для поиска активного чата по chatId
    // Возвращает Optional, который либо содержит найденный объект, либо пуст
    Optional<ActiveChat> findActiveChatByChatId(Long chatId);
}