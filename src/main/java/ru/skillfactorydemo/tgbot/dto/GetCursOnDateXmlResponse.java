package ru.skillfactorydemo.tgbot.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// Аннотация JAXB, указывающая, что данный класс может быть корневым элементом XML
@XmlRootElement(name = "GetCursOnDateXMLResponse", namespace = "http://web.cbr.ru/")
// Аннотация JAXB, определяющая стратегию доступа к полям класса
@XmlAccessorType(XmlAccessType.FIELD)
// Аннотация Lombok, которая автоматически генерирует геттеры, сеттеры, equals, hashCode и toString методы
@Data
public class GetCursOnDateXmlResponse {

    // Поле, которое будет элементом XML, содержащим результат запроса курсов на дату
    @XmlElement(name = "GetCursOnDateXMLResult", namespace = "http://web.cbr.ru/")
    private GetCursOnDateXmlResult getCursOnDateXmlResult;
}