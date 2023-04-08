package com.devsuperior.hrworker.resources;

import com.devsuperior.hrworker.entities.Worker;
import com.devsuperior.hrworker.repositories.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/workers")
public class WorkerEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerEndpoint.class);

    private final Environment applicationEnvironment;
    private final WorkerRepository workerRepository;

    public WorkerEndpoint(Environment applicationEnvironment, WorkerRepository workerRepository) {
        this.applicationEnvironment = applicationEnvironment;
        this.workerRepository = workerRepository;
    }

    @GetMapping
    public ResponseEntity<List<Worker>> getWorkers() {
        final List<Worker> workerList = this.workerRepository.findAll();
        return ResponseEntity.ok(workerList);
    }

    @GetMapping(value = "/{id_worker}")
    public ResponseEntity<Worker> getWorkers(@PathVariable("id_worker") Long id) {
        LOGGER.info("hr-worker serving on port: " + applicationEnvironment.getProperty("server.port"));

        final Optional<Worker> optionalWorker = this.workerRepository.findById(id);
        final Worker worker = optionalWorker.orElseThrow(() -> new EntityNotFoundException("Trabalhador não encontrado"));
        return ResponseEntity.ok(worker);
    }

}
