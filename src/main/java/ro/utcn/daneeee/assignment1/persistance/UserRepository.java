package ro.utcn.daneeee.assignment1.persistance;

import ro.utcn.daneeee.assignment1.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(int id);

    Optional<User> findByUsername(String user);

    void remove(User user);

    List<User> findAll();
}
