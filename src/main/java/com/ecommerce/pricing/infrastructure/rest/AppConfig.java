package com.ecommerce.pricing.infrastructure.config;

import com.ecommerce.pricing.domain.ports.PriceRepositoryPort;
import com.ecommerce.pricing.domain.service.PriceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public PriceService priceService(PriceRepositoryPort priceRepositoryPort) {
        return new PriceService(priceRepositoryPort);
    }
}
