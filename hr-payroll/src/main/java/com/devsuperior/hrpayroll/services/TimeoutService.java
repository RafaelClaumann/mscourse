package com.devsuperior.hrpayroll.services;

import com.devsuperior.hrpayroll.entities.Payment;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

@Service
public class TimeoutService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeoutService.class);

    private final PaymentService paymentService;

    public TimeoutService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @TimeLimiter(name = "circuit-breaker-timeout", fallbackMethod = "fallback")
    public CompletableFuture<Payment> callExternalService() {
        LOGGER.info("forcing Spring Circuit Breaker Timeout! see `resilience4j.timelimiter.instances.<id>.timeoutDuration` in hr-payroll project");

        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return paymentService.generatePayment(1L, 5);
        });
    }

    private CompletableFuture<Payment> fallback(TimeoutException throwable) {
        LOGGER.error("error detail message: {} ", throwable.getMessage());
        return CompletableFuture.completedFuture(
                Payment.builder()
                        .name("fallback-circuit-breaker-timeout")
                        .dailyIncome(Double.MIN_VALUE)
                        .days(Integer.MAX_VALUE)
                        .build()
        );
    }

}
