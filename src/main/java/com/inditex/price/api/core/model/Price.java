package com.inditex.price.api.core.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@Data
@Accessors(chain = true)
public class Price {

    private Long id;
    private Long brandId;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private Long priceList;
    private Long productId;
    private Long priority;
    private Double price;
    private String currency;

}
