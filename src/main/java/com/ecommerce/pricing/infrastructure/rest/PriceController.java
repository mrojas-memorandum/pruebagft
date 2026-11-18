package com.ecommerce.pricing.infrastructure.rest;

import com.ecommerce.pricing.domain.PriceDomain;
import com.ecommerce.pricing.domain.service.PriceService;
import com.ecommerce.pricing.infrastructure.rest.dto.PriceResponseDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public ResponseEntity<PriceResponseDto> getPrice(
            @RequestParam("brandId") Long brandId,
            @RequestParam("productId") Long productId,
            @RequestParam("applicationDate") @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss") LocalDateTime applicationDate) {

        PriceDomain priceDomain = priceService.getApplicablePrice(brandId, productId, applicationDate)
                .orElseThrow(() -> new RuntimeException("Price not found for given parameters"));

        PriceResponseDto response = PriceResponseDto.builder()
                .productId(priceDomain.getProductId())
                .brandId(priceDomain.getBrandId())
                .priceList(priceDomain.getPriceList())
                .startDate(priceDomain.getStartDate())
                .endDate(priceDomain.getEndDate())
                .finalPrice(priceDomain.getFinalPrice())
                .currency(priceDomain.getCurrency())
                .build();

        return ResponseEntity.ok(response);
    }
}
