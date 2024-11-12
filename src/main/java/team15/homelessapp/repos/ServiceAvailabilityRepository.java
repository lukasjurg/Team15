package team15.homelessapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import team15.homelessapp.model.ServiceAvailability;

public interface ServiceAvailabilityRepository extends JpaRepository<ServiceAvailability, Integer> {
}
