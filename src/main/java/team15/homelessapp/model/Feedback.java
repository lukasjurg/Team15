package team15.homelessapp.model;

import javax.persistence.*;

@Entity
@Table(name = "Feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feedback_ID;

    @ManyToOne
    @JoinColumn(name = "user_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "service_ID", nullable = false)
    private Service service;

    private int rating;

    // Getters and Setters

    public int getFeedback_ID() {
        return feedback_ID;
    }

    public void setFeedback_ID(int feedback_ID) {
        this.feedback_ID = feedback_ID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
