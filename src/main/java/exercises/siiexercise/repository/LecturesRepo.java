package exercises.siiexercise.repository;

import exercises.siiexercise.model.Lecture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LecturesRepo extends CrudRepository<Lecture, Long> {

    List<Lecture> findAll();
    List<Lecture> findLecturesByTitle(String title);
    Lecture getLecturesByTitle(String title);

    Lecture save(Lecture lecture);
}
