package ecom.dto;

import java.util.List;

public record TopProductsResponse(
        List<ProductResponse> topProducts
) {}
