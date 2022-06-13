package exercises.siiexercise.repository;

import exercises.siiexercise.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsersRepo extends CrudRepository<User, Long> {

    boolean existsUserByLogin(String login);

    List<User> findAll();

    List<User> findUserByLogin(String login);

    User getUserByLogin(String login);

    User save(User user);
}
