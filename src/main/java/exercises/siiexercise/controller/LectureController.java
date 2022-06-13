package exercises.siiexercise.controller;

import exercises.siiexercise.model.Lecture;
import exercises.siiexercise.repository.LecturesRepo;
import exercises.siiexercise.repository.ReservationsRepo;
import exercises.siiexercise.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @GetMapping("/lectures/popularityByTitle")
    public ResponseEntity<Map<Integer, Lecture>> sortLecturesByTitlePopularity() {
        List<Lecture> lecturesList;
        lecturesList = lecturesRepo.findAll();

        Map<Integer, Lecture> sortedLecturesMap = new TreeMap<>();
        int count;

        for (int i = 0; i < 9; i++) {
            count = reservationsRepo.findReservationByLecture(lecturesList.get(i)).size();
            sortedLecturesMap.put(count,lecturesList.get(i));
        }
        return new ResponseEntity<>(sortedLecturesMap, HttpStatus.OK);
    }
}
