package uz.pdp.property;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:application.properties")
@Component
@Getter
public class ApplicationProps {

    @Value("${spring.datasource.url}")
    private String databaseUrl;
}
