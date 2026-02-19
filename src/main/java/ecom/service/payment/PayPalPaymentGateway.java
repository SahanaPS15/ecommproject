package ecom.service.payment;

import ecom.dto.PaymentRequest;
import ecom.dto.PaymentResponse;
import ecom.entity.PaymentStatus;

import java.util.UUID;

public class PayPalPaymentGateway implements PaymentGateway {
    @Override
    public PaymentResponse pay(PaymentRequest request) {
        // Simulate PayPal payment success
        return new PaymentResponse(
                UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE,
                request.orderId(),
                request.amount(),
                "PAYPAL",
                PaymentStatus.SUCCESS.name()
        );
    }
}
