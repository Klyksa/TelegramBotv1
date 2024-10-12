package ru.skillfactorydemo.tgbot.dto;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

// Аннотация JAXB, которая определяет, как будут обрабатываться поля класса
@XmlAccessorType(XmlAccessType.FIELD)
// Аннотация JAXB, указывающая, что данный класс может быть корневым элементом XML
@XmlRootElement(name = "GetCursOnDateXmlResult")
// Аннотация Lombok, которая автоматически генерирует геттеры, сеттеры, equals, hashCode и toString методы
@Data
public class GetCursOnDateXmlResult {

    // Аннотация JAXB, задающая обертку для списка элементов "ValuteData"
    @XmlElementWrapper(name = "ValuteData", namespace = "")
    // Аннотация JAXB, указывающая, что каждый элемент списка будет элементом XML с именем "ValuteCursOnDate"
    @XmlElement(name = "ValuteCursOnDate", namespace = "")
    // Поле списка, которое будет содержать данные о курсах валют на дату. Инициализируется как пустой ArrayList
    private List<ValuteCursOnDate> valuteData = new ArrayList<>();
}