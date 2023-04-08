package com.devsuperior.hrpayroll.services;

import com.devsuperior.hrpayroll.dto.response.WorkerDTO;
import com.devsuperior.hrpayroll.entities.Payment;
import com.devsuperior.hrpayroll.feign.WorkerFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final WorkerFeignClient workerFeignClient;

    public PaymentService(WorkerFeignClient workerFeignClient) {
        this.workerFeignClient = workerFeignClient;
    }

    public Payment generatePayment(final Long workerId, final Integer days) {
        final ResponseEntity<WorkerDTO> responseEntity = this.workerFeignClient.getWorkers(workerId);
        final WorkerDTO worker = responseEntity.getBody();

        return Payment.builder()
                .name(worker.getName())
                .dailyIncome(worker.getDailyIncome())
                .days(days)
                .build();
    }

}
