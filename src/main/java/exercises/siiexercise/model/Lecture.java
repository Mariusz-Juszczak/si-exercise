package exercises.siiexercise.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Lecture {

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;
    @Id
    private String title;

    private String topic;


    protected Lecture() {}

    public Lecture(LocalDateTime startDateTime, LocalDateTime endDateTime, String title, String topic) {
        this.endDateTime = endDateTime;
        this.startDateTime = startDateTime;
        this.title = title;
        this.topic = topic;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
