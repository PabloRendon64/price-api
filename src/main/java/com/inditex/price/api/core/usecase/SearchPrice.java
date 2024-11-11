package com.inditex.price.api.core.usecase;

import com.inditex.price.api.core.exception.NotFoundException;
import com.inditex.price.api.core.gateway.ISearchPriceRepository;
import com.inditex.price.api.core.gateway.ISearchPrice;
import com.inditex.price.api.core.model.PriceSearchResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class SearchPrice implements ISearchPrice {

    private ISearchPriceRepository searchPriceRepository;

    protected static String SEARCH_PRICE_NOT_FOUND_RESULTS = "not found price using brandId: %s, productId: %s, queryDateTime: %s.";

    @Override
    public PriceSearchResult execute(OffsetDateTime queryDateTime, Long productId, Long brandId) {
        var searchResults = searchPriceRepository.execute(queryDateTime, productId, brandId);
        if (searchResults.isEmpty()) {
            throw new NotFoundException(String.format(SEARCH_PRICE_NOT_FOUND_RESULTS, brandId, productId, queryDateTime));
        }

        var priceFound = searchResults.getFirst();
        return new PriceSearchResult()
                .setProductId(priceFound.getProductId())
                .setBrandId(priceFound.getBrandId())
                .setPriceList(priceFound.getPriceList())
                .setPriceListStartDate(priceFound.getStartDate())
                .setPriceListEndDate(priceFound.getEndDate())
                .setPrice(priceFound.getPrice());
    }

}
