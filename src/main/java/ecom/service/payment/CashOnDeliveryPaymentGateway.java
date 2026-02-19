package ecom.service.payment;

import ecom.dto.PaymentRequest;
import ecom.dto.PaymentResponse;
import ecom.entity.PaymentStatus;

import java.util.UUID;

public class CashOnDeliveryPaymentGateway implements PaymentGateway {
    @Override
    public PaymentResponse pay(PaymentRequest request) {
        // COD does not require upfront payment, mark as "PENDING"
        return new PaymentResponse(
                UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE,
                request.orderId(),
                request.amount(),
                "CASH_ON_DELIVERY",
                PaymentStatus.PENDING.name()
        );
    }
}
