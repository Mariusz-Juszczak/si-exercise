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
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
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

        User tempUser = usersRepo.getUserByLogin(user.getLogin());
        Lecture tempLecture = lecturesRepo.getLectureByTitle(title.get());

        if (tempUser != null) {

             if ((reservationsRepo.findReservationByLecture(tempLecture).size() >= 5)){
                return new ResponseEntity<>("The reservation cannot be placed as there are no free seats left at this lecture.", HttpStatus.BAD_REQUEST);

             } else if (tempUser.getLecturesList().contains(tempLecture)) {
                 return new ResponseEntity<>("The reservation already exist.", HttpStatus.BAD_REQUEST);

             } else {
                 int i = 0;
                 while (i<5) {
                 if (tempUser.getLecturesList().size() > i && tempUser.getLecturesList().get(i).getStartDateTime().isEqual(tempLecture.getStartDateTime())) {
                     return new ResponseEntity<>("You have already another lecture scheduled at that time", HttpStatus.BAD_REQUEST);
                 }
                     i++;
                 }

                 tempUser.getLecturesList().add(tempLecture);
                 Reservation reservation = new Reservation(tempUser, tempLecture);
                 reservationsRepo.save(reservation);

                 File file = new File("notifications.txt");
                 LocalDateTime localDateTime = LocalDateTime.now();
                 try {
                    PrintWriter writer = new PrintWriter(file);
                    writer.println("Post date: " + localDateTime);
                    writer.println("To: " + user.getEmail());
                    writer.println("Thank you for making a reservation for: " + tempLecture.getTitle() + " lecture at our conference!");
                    writer.close();
                 } catch (IOException e) {
                    e.printStackTrace();
                 }
            }
        }
        else {
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
