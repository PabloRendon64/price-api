package com.inditex.price.api.entrypoint.controller;

import com.inditex.price.api.BaseTest;
import com.inditex.price.api.core.gateway.ISearchPrice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IPriceApi.class)
class WebMockTest extends BaseTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ISearchPrice useCase;

    @Test
    @DisplayName("given query params when call url /price/search then return http status ok")
    void searchPriceOperationTestWhenResponseOk() throws Exception {
        when(useCase.execute(any(), any(), any())).thenReturn(mockPriceSearchResult());

        var queryDate = "2020-06-14T10:00:00.000Z";
        var productId = "35455";
        var brandId = "1";

        this.mockMvc.perform(get("/price/search")
                        .queryParam("query_date_time", queryDate)
                        .queryParam("product_id", productId)
                        .queryParam("brand_id", brandId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price_list").value("1"))
                .andExpect(jsonPath("$.price").value("35.5"));
    }

    @Test
    @DisplayName("given incomplete query params when call url /price/search then return http status bad request")
    void searchPriceOperationTestWhenResponseBadRequest() throws Exception {
        when(useCase.execute(any(), any(), any())).thenReturn(mockPriceSearchResult());

        var queryDate = "2020-06-14T10:00:00.000Z";
        var brandId = "1";

        this.mockMvc.perform(get("/price/search")
                        .queryParam("query_date_time", queryDate)
                        .queryParam("brand_id", brandId))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("when call wrong url then return http status not found")
    void searchPriceOperationTestWhenResponseNotFound() throws Exception {
        when(useCase.execute(any(), any(), any())).thenReturn(mockPriceSearchResult());

        this.mockMvc.perform(get("/price/another-not-implemented-service"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
