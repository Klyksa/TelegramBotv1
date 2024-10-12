package ru.skillfactorydemo.tgbot.config;

// Импорт необходимых классов для создания конфигурации Swagger
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// Аннотация @Configuration указывает, что этот класс содержит определения бинов
@Configuration
// Аннотация @EnableSwagger2 включает поддержку Swagger в приложении
@EnableSwagger2
public class SwaggerConfig {

    // Метод botApi() создает бин Docket, который является главным объектом конфигурации Swagger
    @Bean
    public Docket botApi() {
        return new Docket(DocumentationType.SWAGGER_2) // Указываем, что используем Swagger 2
                .select() // Начинаем построение документации
                .apis(RequestHandlerSelectors.any()) // Выбираем любые контроллеры для документации
                .paths(PathSelectors.any()) // Выбираем любые пути для документирования
                .build(); // Завершаем построение бина Docket
    }
}
