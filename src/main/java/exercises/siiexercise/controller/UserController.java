package exercises.siiexercise.controller;

import exercises.siiexercise.model.Lecture;
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
public class UserController {

    private LecturesRepo lecturesRepo;
    private UsersRepo usersRepo;

    private ReservationsRepo reservationsRepo;

    @Autowired
    public UserController(LecturesRepo lecturesRepo, UsersRepo usersRepo, ReservationsRepo reservationsRepo) {
        this.lecturesRepo = lecturesRepo;
        this.usersRepo = usersRepo;
        this.reservationsRepo = reservationsRepo;
    }

    @GetMapping("/users")
    public List<User> getUser(
            @RequestParam("login") Optional<String> login) {
        if (login.isPresent()) {
            return usersRepo.findUserByLogin(login.get());
        } else {
            return usersRepo.findAll();
        }
    }

    @PostMapping("/users")
    public ResponseEntity<String> addUser(
            @RequestBody User user) {
        if (usersRepo.existsUserByLogin(user.getLogin())) {
            return new ResponseEntity<>("The login you provided is already taken.", HttpStatus.BAD_REQUEST);
        } else {
            usersRepo.save(user);
            return new ResponseEntity<>("New user was created", HttpStatus.OK);
        }
    }
}
