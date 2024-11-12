package team15.homelessapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team15.homelessapp.model.ServiceAvailability;
import team15.homelessapp.repos.ServiceAvailabilityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceAvailabilityService {

    @Autowired
    private ServiceAvailabilityRepository serviceAvailabilityRepository;

    public List<ServiceAvailability> getAllServiceAvailabilities() {
        return serviceAvailabilityRepository.findAll();
    }

    public Optional<ServiceAvailability> getServiceAvailabilityById(int id) {
        return serviceAvailabilityRepository.findById(id);
    }

    public ServiceAvailability createServiceAvailability(ServiceAvailability availability) {
        return serviceAvailabilityRepository.save(availability);
    }

    public Optional<ServiceAvailability> updateServiceAvailability(int id, ServiceAvailability updatedAvailability) {
        return serviceAvailabilityRepository.findById(id).map(availability -> {
            availability.setService(updatedAvailability.getService());
            availability.setAvailable_slots(updatedAvailability.getAvailable_slots());
            availability.setLast_updated(updatedAvailability.getLast_updated());
            return serviceAvailabilityRepository.save(availability);
        });
    }

    public boolean deleteServiceAvailability(int id) {
        if (serviceAvailabilityRepository.existsById(id)) {
            serviceAvailabilityRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
