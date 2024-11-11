package com.inditex.price.api;

import com.inditex.price.api.core.model.Price;
import com.inditex.price.api.core.model.PriceSearchResult;
import com.inditex.price.api.infrastructure.persistence.entity.PriceEntity;

import java.time.OffsetDateTime;

public class BaseTest {

    public static Long BRAND_ID = 1L;
    public static Long PRODUCT_ID = 35455L;
    public static Long PRICE_LIST = 1L;
    public static Double PRICE = 35.50;

    public static PriceSearchResult mockPriceSearchResult() {
        return new PriceSearchResult()
                .setProductId(PRODUCT_ID)
                .setBrandId(BRAND_ID)
                .setPriceList(PRICE_LIST)
                .setPriceListStartDate(OffsetDateTime.now())
                .setPriceListEndDate(OffsetDateTime.now())
                .setPrice(PRICE);
    }

    public static Price mockPrice() {
        return new Price()
                .setProductId(PRODUCT_ID)
                .setBrandId(BRAND_ID)
                .setPriceList(PRICE_LIST)
                .setStartDate(OffsetDateTime.now())
                .setEndDate(OffsetDateTime.now())
                .setPrice(PRICE);
    }

    public static PriceEntity mockPriceEntity() {
        var priceEntity = new PriceEntity();
        priceEntity.setProductId(PRODUCT_ID);
        priceEntity.setBrandId(BRAND_ID);
        priceEntity.setPriceList(PRICE_LIST);
        priceEntity.setStartDate(OffsetDateTime.now());
        priceEntity.setEndDate(OffsetDateTime.now());
        priceEntity.setPrice(PRICE);
        return priceEntity;
    }

}
