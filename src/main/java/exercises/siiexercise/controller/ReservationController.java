package exercises.siiexercise.controller;

import exercises.siiexercise.model.Lecture;
import exercises.siiexercise.model.Reservation;
import exercises.siiexercise.model.User;
import exercises.siiexercise.repository.LecturesRepo;
import exercises.siiexercise.repository.ReservationsRepo;
import exercises.siiexercise.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class ReservationController {

    private ReservationsRepo reservationsRepo;
    private LecturesRepo lecturesRepo;
    private UsersRepo usersRepo;


    @Autowired
    public ReservationController(ReservationsRepo reservationsRepo, LecturesRepo lecturesRepo, UsersRepo usersRepo) {
        this.reservationsRepo = reservationsRepo;
        this.lecturesRepo = lecturesRepo;
        this.usersRepo = usersRepo;
    }

    @PostMapping("/reservations/{title}")
    public ResponseEntity<String> addReservation(
            @PathVariable Optional<String> title, @RequestBody User user) {

        if (title.isPresent() && (usersRepo.getUserByLogin(user.getLogin()) != null)) {

             if ((reservationsRepo.findReservationByLecture(lecturesRepo.getLecturesByTitle(title.get())).size() >= 5)){
                return new ResponseEntity<>("The reservation cannot be placed as there are no free seats left at this lecture.", HttpStatus.BAD_REQUEST);

            } else if (usersRepo.getUserByLogin(user.getLogin()).getLecturesList().contains(lecturesRepo.getLecturesByTitle(title.get()))) {
                return new ResponseEntity<>("The reservation already exist.", HttpStatus.BAD_REQUEST);

            } else {
                user = usersRepo.getUserByLogin(user.getLogin());
                user.getLecturesList().add(lecturesRepo.getLecturesByTitle(title.get()));
                Reservation reservation = new Reservation(user, lecturesRepo.getLecturesByTitle(title.get()));
                reservationsRepo.save(reservation);
            }

        } else {
            return new ResponseEntity<>("You have to log in with your login and email", HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>("Reservation is complete", HttpStatus.OK);
    }

    @GetMapping("/reservations")
    public List<Reservation> getReservations(
            @RequestParam("login") Optional<String> login) {
        if (login.isPresent()) {
            return reservationsRepo.findReservationByUser(usersRepo.getUserByLogin(login.get()));
        } else {
            return reservationsRepo.findAll();
        }
    }

    @GetMapping("users/{login}/reservations")
    public List<Lecture> getUserReservations(
            @PathVariable("login") Optional<String> login) {
                    return usersRepo.getUserByLogin(login.get()).getLecturesList();
    }

    @DeleteMapping("users/{id1}/reservations/{id2}")
    public void deleteReservation(
            @PathVariable("id1") Optional<Long> id1, @PathVariable("id2") Optional<Long> id2) {
        if (id1.isPresent() && id2.isPresent()) {
            reservationsRepo.deleteById(id2.get());
        }
    }

}
