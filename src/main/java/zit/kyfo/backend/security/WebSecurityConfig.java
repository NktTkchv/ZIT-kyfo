package zit.kyfo.backend.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    //TODO: написать нормальную конфигурацию безопасности, т.к. API должно быть защищено
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain prometheusFilterChain(HttpSecurity security) throws Exception {
        log.info("prometheusFilterChain registered in component scan");
        return security
                .authorizeHttpRequests(authz -> {
                    authz.requestMatchers("/actuator/**").permitAll();
                    authz.anyRequest().permitAll();
                })
                .formLogin(AbstractAuthenticationFilterConfigurer::disable)
                .logout(LogoutConfigurer::disable)
                .build();
    }

}
