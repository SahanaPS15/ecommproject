package ecom.service.payment;

import ecom.dto.PaymentRequest;
import ecom.dto.PaymentResponse;
import ecom.entity.PaymentStatus;

import java.util.UUID;

public class CreditCardPaymentGateway implements PaymentGateway {
    @Override
    public PaymentResponse pay(PaymentRequest request) {
        // Simulate credit card payment success
        return new PaymentResponse(
                UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE,
                request.orderId(),
                request.amount(),
                "CREDIT_CARD",
                PaymentStatus.SUCCESS.name()
        );
    }
}
