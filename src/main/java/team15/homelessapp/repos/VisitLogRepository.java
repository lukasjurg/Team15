package team15.homelessapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import team15.homelessapp.model.VisitLog;

public interface VisitLogRepository extends JpaRepository<VisitLog, Integer> {
}
