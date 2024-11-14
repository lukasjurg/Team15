package team15.homelessapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team15.homelessapp.exceptions.ResourceNotFoundException;
import team15.homelessapp.exceptions.DatabaseException;
import team15.homelessapp.model.AppService;
import team15.homelessapp.repos.ServiceRepository;

import java.util.List;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    public List<AppService> getAllServices() {
        try {
            return serviceRepository.findAll();
        } catch (Exception e) {
            throw new DatabaseException("Failed to retrieve services", e);
        }
    }

    public AppService getServiceById(int id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service with ID " + id + " not found"));
    }

    public AppService createService(AppService service) {
        try {
            return serviceRepository.save(service);
        } catch (Exception e) {
            throw new DatabaseException("Failed to create service", e);
        }
    }

    public AppService updateService(int id, AppService updatedService) {
        return serviceRepository.findById(id).map(service -> {
            service.setName(updatedService.getName());
            service.setAddress(updatedService.getAddress());
            service.setContact_number(updatedService.getContact_number());
            service.setOperating_hours(updatedService.getOperating_hours());
            service.setCity(updatedService.getCity());
            service.setCategory(updatedService.getCategory());
            try {
                return serviceRepository.save(service);
            } catch (Exception e) {
                throw new DatabaseException("Failed to update service", e);
            }
        }).orElseThrow(() -> new ResourceNotFoundException("Service with ID " + id + " not found"));
    }

    public void deleteService(int id) {
        if (!serviceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Service with ID " + id + " not found");
        }
        try {
            serviceRepository.deleteById(id);
        } catch (Exception e) {
            throw new DatabaseException("Failed to delete service", e);
        }
    }
}
