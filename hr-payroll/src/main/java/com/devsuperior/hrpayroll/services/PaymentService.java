package com.devsuperior.hrpayroll.services;

import com.devsuperior.hrpayroll.entities.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public Payment generatePayment(final Long workerId, final Integer days) {
        return Payment.builder()
                .name("mock-name")
                .dailyIncome(175.95)
                .days(days)
                .build();
    }

}
