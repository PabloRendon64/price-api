package com.inditex.price.api.core.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@Data
@Accessors(chain = true)
public class PriceSearchResult {

    private Long productId;
    private Long brandId;
    private Long priceList;
    private OffsetDateTime priceListStartDate;
    private OffsetDateTime priceListEndDate;
    private Double price;

}
