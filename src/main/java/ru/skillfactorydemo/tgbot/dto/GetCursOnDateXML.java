package ru.skillfactorydemo.tgbot.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.XMLGregorianCalendar;

// Аннотация JAXB, указывающая, что данный класс может быть корневым элементом XML
@XmlRootElement(name = "GetCursOnDateXML", namespace = "http://web.cbr.ru/")
// Аннотация JAXB, определяющая стратегию доступа к полям класса
@XmlAccessorType(XmlAccessType.FIELD)
// Аннотация Lombok, которая автоматически генерирует геттеры, сеттеры, equals, hashCode и toString методы
@Data
public class GetCursOnDateXML {

    // Поле, которое будет элементом XML. Оно содержит дату, по которой запрашиваются курсы
    @XmlElement(name = "On_date", required = true, namespace = "http://web.cbr.ru/")
    protected XMLGregorianCalendar onDate;

}