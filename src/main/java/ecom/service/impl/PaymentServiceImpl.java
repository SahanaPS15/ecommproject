package ecom.service.impl;

import ecom.dto.PaymentRequest;
import ecom.dto.PaymentResponse;
import ecom.service.PaymentService;
import ecom.service.payment.*;

import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        PaymentGateway gateway;

        switch (request.method().toUpperCase()) {
            case "CREDIT_CARD" -> gateway = new CreditCardPaymentGateway();
            case "PAYPAL" -> gateway = new PayPalPaymentGateway();
            case "COD", "CASH_ON_DELIVERY" -> gateway = new CashOnDeliveryPaymentGateway();
            default -> throw new IllegalArgumentException("Unsupported payment method: " + request.method());
        }

        return gateway.pay(request);
    }
}

