package ecom.service.payment;

import ecom.dto.PaymentRequest;
import ecom.dto.PaymentResponse;

public interface PaymentGateway {
    PaymentResponse pay(PaymentRequest request);
}
