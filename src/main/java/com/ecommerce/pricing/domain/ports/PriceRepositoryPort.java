package com.ecommerce.pricing.domain.ports;

import com.ecommerce.pricing.domain.PriceDomain;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryPort {
    Optional<PriceDomain> findApplicablePrice(Long brandId, Long productId, LocalDateTime applicationDate);
}
