package ecom.dto;

import java.math.BigDecimal;

public record PaymentRequest( Long orderId,
        BigDecimal amount,
        String method) {

}
