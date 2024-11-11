package com.inditex.price.api.core.gateway;

import com.inditex.price.api.core.model.PriceSearchResult;

import java.time.OffsetDateTime;

public interface ISearchPrice {

    PriceSearchResult execute(OffsetDateTime queryDateTime, Long productId, Long brandId);

}
