package com.ecommerce.pricing.infrastructure.persistence;

import com.ecommerce.pricing.domain.PriceDomain;
import com.ecommerce.pricing.domain.ports.PriceRepositoryPort;
import com.ecommerce.pricing.infrastructure.persistence.entity.PriceEntity;
import com.ecommerce.pricing.infrastructure.persistence.repository.SpringDataPriceRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final SpringDataPriceRepository repository;

    public PriceRepositoryAdapter(SpringDataPriceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<PriceDomain> findApplicablePrice(Long brandId, Long productId, LocalDateTime applicationDate) {
        List<PriceEntity> entities = repository.findApplicablePrices(brandId, productId, applicationDate);
        if (entities.isEmpty()) {
            return Optional.empty();
        }
        PriceEntity topPriorityEntity = entities.get(0);
        return Optional.of(toDomain(topPriorityEntity));
    }

    private PriceDomain toDomain(PriceEntity entity) {
        return PriceDomain.builder()
                .brandId(entity.getBrandId())
                .productId(entity.getProductId())
                .priceList(entity.getPriceList())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .finalPrice(entity.getPrice())
                .currency(entity.getCurrency())
                .build();
    }
}
