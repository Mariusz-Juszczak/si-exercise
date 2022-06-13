package exercises.siiexercise.model;

import javax.persistence.*;

@Entity
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Lecture lecture;


    protected Reservation() {}

    public Reservation(User user, Lecture lecture) {
        this.user = user;
        this.lecture = lecture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }
}
