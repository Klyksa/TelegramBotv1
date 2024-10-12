package ru.skillfactorydemo.tgbot.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// Указание эффективности доступа к полям посредством полей (а не методов)
@XmlAccessorType(XmlAccessType.FIELD)
// Аннотация JAXB для указания корневого элемента XML, соответствующего этому классу
@XmlRootElement(name = "ValuteCursOnDate")
// Аннотация Lombok, автоматически генерирующая методы, такие как геттеры и сеттеры
@Data
public class ValuteCursOnDate {

    // Поле для хранения названия валюты, соответствующего элементу XML "<Vname>"
    @XmlElement(name = "Vname")
    private String name;

    // Поле для хранения номинала валюты, соответствующего элементу XML "<Vnom>"
    @XmlElement(name = "Vnom")
    private int nominal;

    // Поле для хранения значения курса валюты, соответствующего элементу XML "<Vcurs>"
    @XmlElement(name = "Vcurs")
    private double course;

    // Поле для хранения кода валюты, соответствующего элементу XML "<Vcode>"
    @XmlElement(name = "Vcode")
    private String code;

    // Поле для хранения буквенного кода валюты, соответствующего элементу XML "<VchCode>"
    @XmlElement(name = "VchCode")
    private String chCode;
}
