package com.devsuperior.hrworker.resources;

import com.devsuperior.hrworker.entities.Worker;
import com.devsuperior.hrworker.repositories.WorkerRepository;
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

    private final WorkerRepository workerRepository;

    public WorkerEndpoint(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @GetMapping
    public ResponseEntity<List<Worker>> getWorkers() {
        final List<Worker> workerList = this.workerRepository.findAll();
        return ResponseEntity.ok(workerList);
    }

    @GetMapping(value = "/{id_worker}")
    public ResponseEntity<Worker> getWorkers(@PathVariable("id_worker") Long id) {
        final Optional<Worker> optionalWorker = this.workerRepository.findById(id);
        final Worker worker = optionalWorker.orElseThrow(() -> new EntityNotFoundException("Trabalhador não encontrado"));
        return ResponseEntity.ok(worker);
    }

}
