package com.inditex.price.api.infrastructure.persistence.repository;

import com.inditex.price.api.core.gateway.ISearchPriceRepository;
import com.inditex.price.api.core.model.Price;
import com.inditex.price.api.infrastructure.persistence.mapper.IPriceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchPriceRepository implements ISearchPriceRepository {

    private final IPriceJpaRepository priceJpaRepository;
    private final IPriceMapper mapper;

    @Override
    public List<Price> execute(OffsetDateTime queryDateTime, Long productId, Long brandId) {
        return mapper.toPrices(priceJpaRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                brandId, productId, queryDateTime, queryDateTime));
    }
}
