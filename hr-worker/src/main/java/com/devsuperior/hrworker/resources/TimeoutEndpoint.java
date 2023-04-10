package com.devsuperior.hrworker.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api_gateway_timeout")
public class TimeoutEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeoutEndpoint.class);

    @GetMapping
    public ResponseEntity<String> apiGatewayTimeout() {
        LOGGER.info("forcing Spring Cloud Gateway Timeout! see `spring.cloud.gateway.httpclient.response-timeout` in hr-api-gateway project");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok("timeout :)");
    }

}
