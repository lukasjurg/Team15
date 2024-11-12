package team15.homelessapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import team15.homelessapp.model.AppService;

public interface ServiceRepository extends JpaRepository<AppService, Integer> {
}
