package team15.homelessapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team15.homelessapp.model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
}
