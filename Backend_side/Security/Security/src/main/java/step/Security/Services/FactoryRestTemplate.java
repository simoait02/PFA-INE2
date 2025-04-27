package step.Security.Services;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class FactoryRestTemplate {
    @Bean
    public RestTemplate createRest(){
        return new RestTemplate();
    }
}
