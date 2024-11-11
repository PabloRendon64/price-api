package com.inditex.price.api.infrastructure.persistence.repository;

import com.inditex.price.api.core.gateway.ISearchPriceRepository;
import com.inditex.price.api.infrastructure.persistence.mapper.IPriceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;
import java.util.List;

import static com.inditex.price.api.BaseTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SearchPriceRepositoryTest {

    ISearchPriceRepository searchPriceRepository;

    @MockBean
    IPriceJpaRepository priceJpaRepository;
    @MockBean
    IPriceMapper mapper;

    @BeforeEach
    public void setup() {
        searchPriceRepository = new SearchPriceRepository(priceJpaRepository, mapper);
    }

    @Test
    @DisplayName("given brand_id, product_id and query date when jpa repository return result and mapper transform results then return list with price domain class")
    void givenBrandIdAndProductIdAndQueryDateAndDependenciesWorksThenReturnPriceList() {
        //given
        var queryDateTime = OffsetDateTime.now();
        var productId = 35455L;
        var brandId = 1L;
        var expectedResult = List.of(mockPrice());
        //when
        when(priceJpaRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                anyLong(), anyLong(), any(OffsetDateTime.class), any(OffsetDateTime.class))).thenReturn(List.of(mockPriceEntity()));
        when(mapper.toPrices(any())).thenReturn(expectedResult);
        //then
        var result = searchPriceRepository.execute(queryDateTime, productId, brandId);
        assertThat(result).usingRecursiveComparison().ignoringFieldsOfTypes(OffsetDateTime.class).isEqualTo(expectedResult);
    }

}
