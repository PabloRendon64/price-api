package com.inditex.price.api.entrypoint.controller;

import com.inditex.price.api.core.model.PriceSearchResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;

public interface IPriceApi {

    @Tag(name = "search price", description = "GET methods for price operations")
    @Operation(summary = "Search price operation",
            description = "Search price operation using given query params")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PriceSearchResult.class)) }),
            @ApiResponse(responseCode = "400", description = "missing required query param", content = @Content),
            @ApiResponse(responseCode = "404", description = "product not found", content = @Content) })
    ResponseEntity<PriceSearchResult> searchPriceOperation(
            @Parameter(description = "date time for search price", required = true) OffsetDateTime queryDateTime,
            @Parameter(description = "product identification", required = true) Long productId,
            @Parameter(description = "brand identification", required = true) Long brandId);

}
