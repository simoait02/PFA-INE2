package step.Gateway_1.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateFactory {
    @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
