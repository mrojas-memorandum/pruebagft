package com.ecommerce.pricing.domain.service;

import com.ecommerce.pricing.domain.PriceDomain;
import com.ecommerce.pricing.domain.ports.PriceRepositoryPort;
import java.time.LocalDateTime;
import java.util.Optional;

public class PriceService {

    private final PriceRepositoryPort priceRepositoryPort;

    public PriceService(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    public Optional<PriceDomain> getApplicablePrice(Long brandId, Long productId, LocalDateTime applicationDate) {
        return priceRepositoryPort.findApplicablePrice(brandId, productId, applicationDate);
    }
}
