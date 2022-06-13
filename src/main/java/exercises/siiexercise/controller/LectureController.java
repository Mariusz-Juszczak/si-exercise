package exercises.siiexercise.controller;

import exercises.siiexercise.model.Lecture;
import exercises.siiexercise.repository.LecturesRepo;
import exercises.siiexercise.repository.ReservationsRepo;
import exercises.siiexercise.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class LectureController {

    private LecturesRepo lecturesRepo;
    private UsersRepo usersRepo;

    private ReservationsRepo reservationsRepo;

    @Autowired
    public LectureController(LecturesRepo lecturesRepo, UsersRepo usersRepo, ReservationsRepo reservationsRepo) {
        this.lecturesRepo = lecturesRepo;
        this.usersRepo = usersRepo;
        this.reservationsRepo = reservationsRepo;
    }

    @GetMapping("/lectures")
    public List<Lecture> getLecture(
            @RequestParam("title") Optional<String> title) {
        if (title.isPresent()) {
            return lecturesRepo.findLecturesByTitle(title.get());
        } else {
            return lecturesRepo.findAll();
        }
    }
}
