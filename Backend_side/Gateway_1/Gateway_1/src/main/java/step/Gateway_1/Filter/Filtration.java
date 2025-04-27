package step.Gateway_1.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Filtration extends AbstractGatewayFilterFactory<Filtration.Config> {
    @Autowired
    private RestTemplate client;
    @Autowired
    RoutesValidation routesValidation;
    public Filtration(){
        super(Config.class);
    }

    // Config is a class for holding the argument or the configuration sended by the gateway in application.yml
    //we must create this class evn in our case we wouldn't send any parameters
    @Override
    public GatewayFilter apply(Config config) {

        return ((exchange, chain) -> {
            if(routesValidation.isSecured.test(exchange.getRequest())){
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw  new RuntimeException("NO TOKEN ");
                }
                String header= exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if( header!=null && header.startsWith("Bearer ")){
                    String token = header.substring(7);

                    ResponseEntity<String> response=this.client.getForEntity("http://localhost:8080/auth/users/validate/"+token,String.class);
                    String body=response.getBody();

                    if(!body.equals("VALID")){
                        throw new RuntimeException(" NO VALID TOKEN");
                    }
                }



            }
            return chain.filter(exchange);
        });
    }

    public static class Config{

    }

}
