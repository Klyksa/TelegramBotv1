package ru.skillfactorydemo.tgbot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import ru.skillfactorydemo.tgbot.dto.GetCursOnDateXML;
import ru.skillfactorydemo.tgbot.dto.GetCursOnDateXmlResponse;
import ru.skillfactorydemo.tgbot.dto.GetCursOnDateXmlResult;
import ru.skillfactorydemo.tgbot.dto.ValuteCursOnDate;
import ru.skillfactorydemo.tgbot.service.CentralRussianBankService;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import java.nio.charset.StandardCharsets;

@Configuration
public class AppConfig {

    // Создаем бин для службы Центрального российского банка
    @Bean
    public CentralRussianBankService cbrService() throws SOAPException {
        // Создаем новый экземпляр службы
        CentralRussianBankService cbrService = new CentralRussianBankService();

        // Создаем маппер JAXB для конвертации объектов в XML и наоборот
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();

        // Создаем SOAP сообщение с использованием протокола SOAP 1.2
        MessageFactory msgFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
        SaajSoapMessageFactory newSoapMessageFactory = new SaajSoapMessageFactory(msgFactory);

        // Устанавливаем фабрику сообщений для службы
        cbrService.setMessageFactory(newSoapMessageFactory);

        // Настраиваем JAXB маппер для работы с указанными классами
        jaxb2Marshaller.setClassesToBeBound(
                GetCursOnDateXML.class,
                GetCursOnDateXmlResponse.class,
                GetCursOnDateXmlResult.class,
                ValuteCursOnDate.class);

        // Устанавливаем маппер как для маршалинга, так и для анмаршалинга
        cbrService.setMarshaller(jaxb2Marshaller);
        cbrService.setUnmarshaller(jaxb2Marshaller);

        // Возвращаем настроенный бин
        return cbrService;
    }

    // Создаем бин для фильтра кодировки символов
    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        // Создаем фильтр кодировки символов
        CharacterEncodingFilter filter = new CharacterEncodingFilter();

        // Устанавливаем кодировку UTF-8
        filter.setEncoding(StandardCharsets.UTF_8.name());

        // Принуждаем использование установленной кодировки во всех запросах
        filter.setForceEncoding(true);

        // Возвращаем настроенный фильтр
        return filter;
    }
}
