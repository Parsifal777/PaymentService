package com.example.config;

import com.example.model.Resident;
import com.example.model.Invoice;
import com.example.model.Payment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDate;

@Configuration
@ComponentScan(basePackages = "com.example")
public class AppConfig {

    @Bean
    @Scope("prototype")
    public Resident resident() {
        return new Resident();
    }

    @Bean
    @Scope("prototype")
    public Invoice invoice() {
        return new Invoice();
    }

    @Bean
    @Scope("prototype")
    public Payment payment() {
        return new Payment();
    }

    @Bean
    public Resident resident1() {
        return new Resident(1L, "Иванов Иван Иванович", "ул. Ленина, д. 10, кв. 5");
    }

    @Bean
    public Resident resident2() {
        return new Resident(2L, "Петрова Мария Сергеевна", "ул. Советская, д. 25, кв. 12");
    }

    @Bean
    public Invoice invoice1(Resident resident1) {
        return new Invoice(1L, resident1, LocalDate.of(2024, 1, 1), 2500.0);
    }

    @Bean
    public Payment payment1(Resident resident1) {
        return new Payment(1L, resident1, LocalDate.of(2024, 1, 15), 2500.0);
    }
}