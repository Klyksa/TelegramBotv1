package ru.skillfactorydemo.tgbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillfactorydemo.tgbot.entity.Income;

// Аннотация Spring, указывающая, что интерфейс является репозиторием для доступа к данным
@Repository

public interface IncomeRepository extends JpaRepository<Income, Long> {

}