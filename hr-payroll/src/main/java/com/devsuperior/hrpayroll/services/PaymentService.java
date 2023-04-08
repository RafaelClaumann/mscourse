package com.devsuperior.hrpayroll.services;

import com.devsuperior.hrpayroll.dto.response.WorkerDTO;
import com.devsuperior.hrpayroll.entities.Payment;
import com.devsuperior.hrpayroll.feign.WorkerFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

    private final WorkerFeignClient workerFeignClient;

    public PaymentService(WorkerFeignClient workerFeignClient) {
        this.workerFeignClient = workerFeignClient;
    }

    @CircuitBreaker(name = "hr-worker-get-worker", fallbackMethod = "fallback")
    public Payment generatePayment(final Long workerId, final Integer days) {
        final ResponseEntity<WorkerDTO> responseEntity = this.workerFeignClient.getWorkers(workerId);
        final WorkerDTO worker = responseEntity.getBody();

        return Payment.builder()
                .name(worker.getName())
                .dailyIncome(worker.getDailyIncome())
                .days(days)
                .build();
    }

    private Payment fallback(final Long workerId, final Integer days, Throwable throwable) {
        LOGGER.error("error detail message: {} ", throwable.getMessage());
        return Payment.builder()
                .name("FALLBACK-WORKER-NAME")
                .dailyIncome(0.00)
                .days(days)
                .build();
    }

}
