package team15.homelessapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team15.homelessapp.model.AppService;
import team15.homelessapp.repos.ServiceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    public List<AppService> getAllServices() {
        return serviceRepository.findAll();
    }

    public Optional<AppService> getServiceById(int id) {
        return serviceRepository.findById(id);
    }

    public AppService createService(AppService service) {
        return serviceRepository.save(service);
    }

    public Optional<AppService> updateService(int id, AppService updatedService) {
        return serviceRepository.findById(id).map(service -> {
            service.setName(updatedService.getName());
            service.setAddress(updatedService.getAddress());
            service.setContact_number(updatedService.getContact_number());
            service.setOperating_hours(updatedService.getOperating_hours());
            service.setCity(updatedService.getCity());
            service.setCategory(updatedService.getCategory());
            return serviceRepository.save(service);
        });
    }

    public boolean deleteService(int id) {
        if (serviceRepository.existsById(id)) {
            serviceRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
