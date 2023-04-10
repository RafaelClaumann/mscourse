package com.devsuperior.hrpayroll.resources;

import com.devsuperior.hrpayroll.entities.Payment;
import com.devsuperior.hrpayroll.services.TimeoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/circuit_breaker_timeout")
public class TimeoutEndpoint {


    private final TimeoutService timeoutService;

    public TimeoutEndpoint(TimeoutService timeoutService) {
        this.timeoutService = timeoutService;
    }

    @GetMapping
    public ResponseEntity<Payment> circuitBreakerTimeout() throws ExecutionException, InterruptedException {
        final Payment payment = this.timeoutService.callExternalService().get();

        return ResponseEntity.ok(payment);
    }

}
