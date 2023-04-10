package com.devsuperior.hrworker.resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RefreshScope
@RestController
@RequestMapping(value = "/remote_properties")
public class RemotePropertiesEndpoint {

    private final String remoteProperty00;
    private final String remoteProperty01;
    private final String remoteProperty02;

    public RemotePropertiesEndpoint(
            @Value("${remote.property.00}") String remoteProperty00,
            @Value("${remote.property.01}") String remoteProperty01,
            @Value("${remote.property.02}") String remoteProperty02
    ) {
        this.remoteProperty00 = remoteProperty00;
        this.remoteProperty01 = remoteProperty01;
        this.remoteProperty02 = remoteProperty02;
    }

    @GetMapping
    public ResponseEntity<?> fetchRemoteProperties() {
        final List<String> remoteProperties = Arrays.asList(
                this.remoteProperty00,
                this.remoteProperty01,
                this.remoteProperty02
        );

        return ResponseEntity.ok(remoteProperties);
    }

}
