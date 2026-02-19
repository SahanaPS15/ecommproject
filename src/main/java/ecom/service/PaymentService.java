package ecom.service;

import ecom.dto.PaymentRequest;
import ecom.dto.PaymentResponse;

public interface PaymentService {
    PaymentResponse processPayment(PaymentRequest request);
}
