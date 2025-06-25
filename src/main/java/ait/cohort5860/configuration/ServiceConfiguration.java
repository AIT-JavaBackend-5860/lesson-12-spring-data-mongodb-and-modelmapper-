package ait.cohort5860.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class  ServiceConfiguration {

    @Bean
    ModelMapper getModelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setFieldMatchingEnabled(true) // разрешаем работать с полями минуя сеттеры
                .setFieldAccessLevel(AccessLevel.PRIVATE) // разрешаем работать c приватными полями
                .setMatchingStrategy(MatchingStrategies.STRICT); // как идут в порядке так и пусть идут поля в этом же порядке
        return mapper;
    }
}
