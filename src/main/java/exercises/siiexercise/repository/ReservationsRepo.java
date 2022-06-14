package exercises.siiexercise.repository;

import exercises.siiexercise.model.Lecture;
import exercises.siiexercise.model.Reservation;
import exercises.siiexercise.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReservationsRepo extends CrudRepository<Reservation, Long> {


    List<Reservation> findAll();

    List<Reservation> findReservationByUser(User user);

    List<Reservation> findReservationByLecture(Lecture lecture);

    List<Reservation> findReservationByLecture_Topic(String topic);

    Reservation save(Reservation reservation);
}
