package exercises.siiexercise.controller;

import exercises.siiexercise.model.Lecture;
import exercises.siiexercise.model.User;
import exercises.siiexercise.repository.LecturesRepo;
import exercises.siiexercise.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private LecturesRepo lecturesRepo;

    private UsersRepo usersRepo;

    @Autowired
    public DataLoader(LecturesRepo lecturesRepo, UsersRepo usersRepo) {
        this.lecturesRepo = lecturesRepo;
        this.usersRepo = usersRepo;
    }

    @Override
    public void run(ApplicationArguments args) {

        List<Lecture> lecturesList = new ArrayList<>();

        Lecture lecture11 = new Lecture( LocalDateTime.of(2021, 6 , 1, 10, 00), LocalDateTime.of(2021, 6, 1, 11, 45), "REST API", "WEB APPLICATIONS");
        Lecture lecture12 = new Lecture( LocalDateTime.of(2021, 6 , 1, 12, 00), LocalDateTime.of(2021, 6, 1, 13, 45), "SPRING", "WEB APPLICATIONS");
        Lecture lecture13 = new Lecture( LocalDateTime.of(2021, 6 , 1, 14, 00), LocalDateTime.of(2021, 6, 1, 15, 45), "HIBERNATE", "WEB APPLICATIONS");
        Lecture lecture21 = new Lecture( LocalDateTime.of(2021, 6 , 1, 10, 00), LocalDateTime.of(2021, 6, 1, 11, 45), "HISTORY OF JAVA", "JAVA");
        Lecture lecture22 = new Lecture( LocalDateTime.of(2021, 6 , 1, 12, 00), LocalDateTime.of(2021, 6, 1, 13, 45), "WHY JAVA IS SO POPULAR", "JAVA");
        Lecture lecture23 = new Lecture( LocalDateTime.of(2021, 6 , 1, 14, 00), LocalDateTime.of(2021, 6, 1, 15, 45), "JAVA 17 WHAT'S NEW", "JAVA");
        Lecture lecture31 = new Lecture( LocalDateTime.of(2021, 6 , 1, 10, 00), LocalDateTime.of(2021, 6, 1, 11, 45), "SECURITY IN IT", "SECURITY");
        Lecture lecture32 = new Lecture( LocalDateTime.of(2021, 6 , 1, 12, 00), LocalDateTime.of(2021, 6, 1, 13, 45), "VULNERABILITY MANAGEMENT", "SECURITY");
        Lecture lecture33 = new Lecture( LocalDateTime.of(2021, 6 , 1, 14, 00), LocalDateTime.of(2021, 6, 1, 15, 45), "LOG4J VULNERABILITY", "SECURITY");

        lecturesRepo.save(lecture11);
        lecturesRepo.save(lecture12);
        lecturesRepo.save(lecture13);
        lecturesRepo.save(lecture21);
        lecturesRepo.save(lecture22);
        lecturesRepo.save(lecture23);
        lecturesRepo.save(lecture31);
        lecturesRepo.save(lecture32);
        lecturesRepo.save(lecture33);
    }
}