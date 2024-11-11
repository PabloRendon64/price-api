package com.inditex.price.api.core.gateway;

import com.inditex.price.api.core.model.Price;

import java.time.OffsetDateTime;
import java.util.List;

public interface ISearchPriceRepository {

    List<Price> execute(OffsetDateTime queryDateTime, Long productId, Long brandId);

}
