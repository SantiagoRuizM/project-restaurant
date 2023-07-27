package com.dish.servicedish.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    protected void configure(HttpSecurity http) throws Exception {
        final var expressionInterceptUrlRegistry = http.authorizeRequests().anyRequest().permitAll();
    }

}

