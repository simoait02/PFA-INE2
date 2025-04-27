package step.Security.Config;

import jakarta.servlet.FilterChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import step.Security.Services.UserDetailsAuth;

@Configuration
@EnableWebSecurity
public class ConfigSec {
    @Autowired
    UserDetailsAuth userDetailsAuth;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity https) throws Exception {

        return https
                .csrf(request-> request.disable())
                .authorizeHttpRequests(request ->{
                    request.requestMatchers("/auth/users/validate/**","auth/users/login","auth/users/register","swagger-ui/**")
                            .permitAll()
                            .anyRequest().authenticated();


                })
                .httpBasic(Customizer.withDefaults())
                .build();

    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider =new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        provider.setUserDetailsService(userDetailsAuth);
        return provider;



    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }


}
