package ru.skillfactorydemo.tgbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.WebServiceTemplate;
import ru.skillfactorydemo.tgbot.dto.GetCursOnDateXML;
import ru.skillfactorydemo.tgbot.dto.GetCursOnDateXmlResponse;
import ru.skillfactorydemo.tgbot.dto.ValuteCursOnDate;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class CentralRussianBankService extends WebServiceTemplate {

    // URL для доступа к API ЦБР, который будет загружен из конфигурационного файла
    @Value("${cbr.api.url}")
    private String cbrApiUrl;

    // Метод для получения списка текущих курсов валют от ЦБР
    public List<ValuteCursOnDate> getCurrenciesFromCbr() throws DatatypeConfigurationException {
        // Создаем запрос для получения курсов валют на текущую дату
        final GetCursOnDateXML getCursOnDateXML = new GetCursOnDateXML();
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());

        // Преобразование текущей даты в формат XMLGregorianCalendar, необходимый для запроса
        XMLGregorianCalendar xmlGregCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        getCursOnDateXML.setOnDate(xmlGregCal);

        // Логирование запроса на консоль (только для отладки)
        System.out.println(getCursOnDateXML);

        // Отправка запроса и получение ответа от SOAP-сервиса ЦБР
        GetCursOnDateXmlResponse response = (GetCursOnDateXmlResponse) marshalSendAndReceive(cbrApiUrl, getCursOnDateXML);

        // Если ответ от сервиса отсутствует, выбрасывается исключение
        if (response == null) {
            throw new IllegalStateException("Не удалось получить данные от ЦБ РФ");
        }

        // Получение списка валют из ответа
        final List<ValuteCursOnDate> courses = response.getGetCursOnDateXmlResult().getValuteData();

        // Удаление лишних пробелов из названия курса каждой валюты
        courses.forEach(course -> course.setName(course.getName().trim()));

        // Возвращаем список с курсами валют
        return courses;
    }

    // Метод для получения курса конкретной валюты по ее коду
    public ValuteCursOnDate getCourseForCurrency(String code) throws DatatypeConfigurationException {
        // Фильтрация списка валют для поиска нужной по коду и возврат результата
        return getCurrenciesFromCbr().stream().filter(currency -> code.equals(currency.getChCode())).findFirst().get();
    }

}
