package team15.homelessapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import team15.homelessapp.model.City;

public interface CityRepository extends JpaRepository<City, Integer> {
}
