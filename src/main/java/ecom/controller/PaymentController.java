package ecom.controller;
import ecom.dto.PaymentRequest;
import ecom.dto.PaymentResponse;
import ecom.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/payments")
public class PaymentController {
private final PaymentService paymentService;
public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
@PostMapping("/process")
    public ResponseEntity<PaymentResponse> process(@RequestBody PaymentRequest request) {
        return ResponseEntity.ok(paymentService.processPayment(request));
    }
}

