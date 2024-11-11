package com.inditex.price.api.core.usecase;

import com.inditex.price.api.BaseTest;
import com.inditex.price.api.core.exception.NotFoundException;
import com.inditex.price.api.core.gateway.ISearchPrice;
import com.inditex.price.api.core.gateway.ISearchPriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;
import java.util.List;

import static com.inditex.price.api.core.usecase.SearchPrice.SEARCH_PRICE_NOT_FOUND_RESULTS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SearchPriceTest extends BaseTest {

    ISearchPrice searchPrice;

    @MockBean
    ISearchPriceRepository priceRepository;

    @BeforeEach
    public void setup() {
        searchPrice = new SearchPrice(priceRepository);
    }

    @Test
    @DisplayName("given brand_id, product_id and query date when found price results in database repository then return price result")
    void givenBrandIdAndProductIdAndQueryDateThenReturnPriceResult() {
        //given
        var queryDateTime = OffsetDateTime.now();
        var productId = 35455L;
        var brandId = 1L;
        var expectedResult = mockPriceSearchResult();
        //when
        when(priceRepository.execute(any(OffsetDateTime.class), anyLong(), anyLong()))
                .thenReturn(List.of(mockPrice()));
        //then
        var result = searchPrice.execute(queryDateTime, productId, brandId);
        assertThat(result).usingRecursiveComparison().ignoringFieldsOfTypes(OffsetDateTime.class).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("given brand_id, product_id and query date when does not found price results in database repository then return exception")
    void givenBrandIdAndProductIdAndQueryDateThenReturnException() {
        //given
        var queryDateTime = OffsetDateTime.now();
        var productId = 35455L;
        var brandId = 1L;
        //when
        when(priceRepository.execute(any(OffsetDateTime.class), anyLong(), anyLong()))
                .thenReturn(List.of());
        //then
        assertEquals(String.format(SEARCH_PRICE_NOT_FOUND_RESULTS, brandId, productId, queryDateTime),
                assertThrows(NotFoundException.class, () -> searchPrice.execute(queryDateTime, productId, brandId)).getMessage());
    }

}
