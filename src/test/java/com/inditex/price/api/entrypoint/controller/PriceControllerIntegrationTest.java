package com.inditex.price.api.entrypoint.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Prueba 1: Realizar una petición a las 10:00 del día 14 para el producto 35455 y la marca 1")
    void searchPriceOperationTest1() throws Exception {
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
    @DisplayName("Prueba 2: Realizar una petición a las 16:00 del día 14 para el producto 35455 y la marca 1")
    void searchPriceOperationTest2() throws Exception {
        var queryDate = "2020-06-14T16:00:00.000Z";
        var productId = "35455";
        var brandId = "1";

        this.mockMvc.perform(get("/price/search")
                        .queryParam("query_date_time", queryDate)
                        .queryParam("product_id", productId)
                        .queryParam("brand_id", brandId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price_list").value("2"))
                .andExpect(jsonPath("$.price").value("25.45"));
    }

    @Test
    @DisplayName("Prueba 3: Realizar una petición a las 21:00 del día 14 para el producto 35455 y la marca 1")
    void searchPriceOperationTest3() throws Exception {
        var queryDate = "2020-06-14T21:00:00.000Z";
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
    @DisplayName("Prueba 4: Realizar una petición a las 10:00 del día 15 para el producto 35455 y la marca 1")
    void searchPriceOperationTest4() throws Exception {
        var queryDate = "2020-06-15T10:00:00.000Z";
        var productId = "35455";
        var brandId = "1";

        this.mockMvc.perform(get("/price/search")
                        .queryParam("query_date_time", queryDate)
                        .queryParam("product_id", productId)
                        .queryParam("brand_id", brandId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price_list").value("3"))
                .andExpect(jsonPath("$.price").value("30.5"));
    }

    @Test
    @DisplayName("Prueba 5: Realizar una petición a las 21:00 del día 16 para el producto 35455 y la marca 1")
    void searchPriceOperationTest5() throws Exception {
        var queryDate = "2020-06-16T21:00:00.000Z";
        var productId = "35455";
        var brandId = "1";

        this.mockMvc.perform(get("/price/search")
                        .queryParam("query_date_time", queryDate)
                        .queryParam("product_id", productId)
                        .queryParam("brand_id", brandId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price_list").value("4"))
                .andExpect(jsonPath("$.price").value("38.95"));
    }

}
