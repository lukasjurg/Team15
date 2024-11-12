package team15.homelessapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team15.homelessapp.model.VisitLog;
import team15.homelessapp.repos.VisitLogRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/visitlogs")
public class VisitLogController {

    @Autowired
    private VisitLogRepository visitLogRepository;

    @GetMapping
    public List<VisitLog> getAllVisitLogs() {
        return visitLogRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitLog> getVisitLogById(@PathVariable Integer id) {
        Optional<VisitLog> visitLog = visitLogRepository.findById(id);
        return visitLog.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public VisitLog createVisitLog(@RequestBody VisitLog visitLog) {
        return visitLogRepository.save(visitLog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisitLog> updateVisitLog(@PathVariable Integer id, @RequestBody VisitLog updatedVisitLog) {
        return visitLogRepository.findById(id)
                .map(log -> {
                    log.setUser(updatedVisitLog.getUser());
                    log.setTimestamp(updatedVisitLog.getTimestamp());
                    return ResponseEntity.ok(visitLogRepository.save(log));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisitLog(@PathVariable Integer id) {
        if (visitLogRepository.existsById(id)) {
            visitLogRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
