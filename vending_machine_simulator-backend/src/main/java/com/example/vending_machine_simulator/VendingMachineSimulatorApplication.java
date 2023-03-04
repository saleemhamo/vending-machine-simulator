package com.example.vending_machine_simulator;

import com.example.vending_machine_simulator.services.SnacksVendingMachineInitializerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class VendingMachineSimulatorApplication {

    private final SnacksVendingMachineInitializerService snacksVendingMachineInitializerService;


    public static void main(String[] args) {
        SpringApplication.run(VendingMachineSimulatorApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    private void initializeSnackVendingMachine() {
        snacksVendingMachineInitializerService.initializeSnacksVendingMachine();
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOriginPatterns(List.of("*"));
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);

        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}
