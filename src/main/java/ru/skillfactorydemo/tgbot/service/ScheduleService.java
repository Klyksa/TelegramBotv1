package ru.skillfactorydemo.tgbot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ru.skillfactorydemo.tgbot.dto.ValuteCursOnDate;
import ru.skillfactorydemo.tgbot.entity.ActiveChat;
import ru.skillfactorydemo.tgbot.repository.ActiveChatRepository;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// Аннотация, указывающая, что класс является компонентом приложения Spring
@Component
// Аннотация Lombok, автоматически генерирующая конструктор с обязательными (final) полями
@RequiredArgsConstructor
// Аннотация Lombok для предоставления логирования с использованием SLF4J
@Slf4j
public class ScheduleService {

    // Репозиторий для доступа к активным чатам
    private final ActiveChatRepository activeChatRepository;
    // Сервис для отправки уведомлений пользователям
    private final BotService botService;
    // Сервис для получения данных курсов валют от Центрального банка России
    private final CentralRussianBankService centralRussianBankService;
    // Список для хранения предыдущих курсов валют
    private final List<ValuteCursOnDate> previousRates = new ArrayList<>();

    /**
     * Метод, который выполняется по расписанию (каждые 3 часа)
     */
    @Scheduled(cron = "0 0 0/3 ? * *")
    public void notifyAboutChangesInCurrencyRate() {
        try {
            // Получение текущих курсов валют от Центробанка
            List<ValuteCursOnDate> currentRates = centralRussianBankService.getCurrenciesFromCbr();
            // Получаем все активные чаты
            Set<Long> activeChatIds = activeChatRepository.findAll().stream()
                    .map(ActiveChat::getChatId) // Извлечение идентификаторов чатов
                    .collect(Collectors.toSet());

            // Проверяем, есть ли предыдущие курсы валют
            if (!previousRates.isEmpty()) {
                // Сравниваем текущие курсы с предыдущими
                for (int index = 0; index < currentRates.size(); index++) {
                    // Если курс увеличился на 10 рублей или более
                    if (currentRates.get(index).getCourse() - previousRates.get(index).getCourse() >= 10.0) {
                        botService.sendNotificationToAllActiveChats(
                                "Курс " + currentRates.get(index).getName() + " увеличился на 10 рублей",
                                activeChatIds
                        );
                        // Если курс уменьшился на 10 рублей или более
                    } else if (currentRates.get(index).getCourse() - previousRates.get(index).getCourse() <= -10.0) {
                        botService.sendNotificationToAllActiveChats(
                                "Курс " + currentRates.get(index).getName() + " уменьшился на 10 рублей",
                                activeChatIds
                        );
                    }
                }
            } else {
                // Если предыдущие курсы отсутствуют, сохраняем текущие курсы
                previousRates.addAll(currentRates);
            }
        } catch (DatatypeConfigurationException e) {
            // Логируем ошибку, если возникла проблема при получении данных
            log.error("Возникла проблема при получении данных от сервисов ЦБ РФ", e);
        }
    }
}

