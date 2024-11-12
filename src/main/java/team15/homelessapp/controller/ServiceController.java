package team15.homelessapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team15.homelessapp.model.AppService;
import team15.homelessapp.repos.ServiceRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping
    public List<AppService> getAllServices() {
        return serviceRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppService> getServiceById(@PathVariable Integer id) {
        Optional<AppService> service = serviceRepository.findById(id);
        return service.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public AppService createService(@RequestBody AppService service) {
        return serviceRepository.save(service);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppService> updateService(@PathVariable Integer id, @RequestBody AppService updatedService) {
        return serviceRepository.findById(id)
                .map(serv -> {
                    serv.setName(updatedService.getName());
                    serv.setAddress(updatedService.getAddress());
                    serv.setContact_number(updatedService.getContact_number());
                    serv.setOperating_hours(updatedService.getOperating_hours());
                    serv.setCity(updatedService.getCity());
                    serv.setCategory(updatedService.getCategory());
                    return ResponseEntity.ok(serviceRepository.save(serv));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Integer id) {
        if (serviceRepository.existsById(id)) {
            serviceRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
