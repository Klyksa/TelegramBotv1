package ru.skillfactorydemo.tgbot.entity;

import lombok.Data;

import javax.persistence.*;

// Аннотация Lombok, автоматически генерирующая методы, такие как геттеры и сеттеры
@Data
// Аннотация JPA, указывающая, что данный класс является сущностью
@Entity
// Указание JPA для имени таблицы в БД, с которой связана эта сущность
@Table(name = "ACTIVE_CHAT")
public class ActiveChat {

    // Указание, что это поле является первичным ключом
    @Id
    // Автоматическая генерация значения первичного ключа (например, автоинкрементное поле)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Указание столбца таблицы БД, соответствующего этому полю
    @Column(name = "CHAT_ID")
    private Long chatId;
}
