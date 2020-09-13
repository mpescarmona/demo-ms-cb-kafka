package com.mpescarmona.demo.configuration;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FakerConfiguration {
    @Bean
    Faker getFaker() {
        return new Faker();
    }
}
