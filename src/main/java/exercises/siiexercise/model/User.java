package exercises.siiexercise.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String login;

    private String email;

    @ManyToMany
    private List<Lecture> lecturesList;

    protected User() {};

    public User(String login, String email, List<Lecture> lecturesList) {
        this.login = login;
        this.email = email;
        this.lecturesList = getLecturesList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Lecture> getLecturesList() {
        return lecturesList;
    }

    public void setLecturesList(List<Lecture> lecturesList) {
        this.lecturesList = lecturesList;
    }
}
