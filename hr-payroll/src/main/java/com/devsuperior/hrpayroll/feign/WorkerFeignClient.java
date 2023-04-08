package com.devsuperior.hrpayroll.feign;

import com.devsuperior.hrpayroll.dto.response.WorkerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "hr-worker", url = "http://localhost:8001", path = "/workers")
public interface WorkerFeignClient {

    @GetMapping(value = "/{id_worker}")
    ResponseEntity<WorkerDTO> getWorkers(@PathVariable("id_worker") Long id);

}
