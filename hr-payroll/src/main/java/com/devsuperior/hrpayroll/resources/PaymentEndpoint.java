package com.devsuperior.hrpayroll.resources;

import com.devsuperior.hrpayroll.entities.Payment;
import com.devsuperior.hrpayroll.services.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payments")
public class PaymentEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentEndpoint.class);

    final PaymentService paymentService;

    public PaymentEndpoint(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping(value = "/{worker_id}/days/{worked_days}")
    public ResponseEntity<Payment> generatePayment(
            @PathVariable("worker_id") Long workerId,
            @PathVariable("worked_days") Integer days
    ) {
        LOGGER.info("[{}] - hr-payroll receiving request", PaymentEndpoint.class.getCanonicalName());
        Payment payment = this.paymentService.generatePayment(workerId, days);
        return ResponseEntity.ok(payment);
    }

}
