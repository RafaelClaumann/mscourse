package com.devsuperior.hrpayroll.services;

import com.devsuperior.hrpayroll.dto.response.WorkerDTO;
import com.devsuperior.hrpayroll.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PaymentService {

    private final RestTemplate restTemplate;
    private final String workerMicroserviceURL;

    public PaymentService(
            RestTemplate restTemplate,
            @Value("${hr-worker.host.url}") String workerMicroserviceURL
    ) {
        this.restTemplate = restTemplate;
        this.workerMicroserviceURL = workerMicroserviceURL;
    }

    public Payment generatePayment(final Long workerId, final Integer days) {
        final String URL = UriComponentsBuilder
                .fromHttpUrl(this.workerMicroserviceURL)
                .pathSegment("workers", workerId.toString())
                .build()
                .toString();

        final WorkerDTO restResponse = this.restTemplate.getForObject(URL, WorkerDTO.class);

        return Payment.builder()
                .name(restResponse.getName())
                .dailyIncome(restResponse.getDailyIncome())
                .days(days)
                .build();
    }

}
