package team15.homelessapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import team15.homelessapp.model.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer> {

}
