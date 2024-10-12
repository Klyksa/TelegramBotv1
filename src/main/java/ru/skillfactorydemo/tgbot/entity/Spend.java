package ru.skillfactorydemo.tgbot.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

// Аннотация JPA, обозначающая, что данный класс является сущностью
@Entity
// Аннотация JPA, задающая имя таблицы, соответствующее данной сущности
@Table(name = "SPEND")
// Аннотация Lombok, автоматически генерирующая стандартные методы, такие как геттеры и сеттеры
@Data
public class Spend {

    // Указание, что это поле является первичным ключом
    @Id
    // Автоматическая генерация значения для первичного ключа, например с использованием автоинкремента
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Отображение этого поля на столбец "CHAT_ID" в таблице базы данных
    @Column(name = "CHAT_ID")
    private Long chatId;

    // Отображение этого поля на столбец "SPEND" в таблице базы данных
    // BigDecimal используется для представления денежных значений, чтобы избежать ошибок, связанных с точностью и округлением
    @Column(name = "SPEND")
    private BigDecimal spend;
}