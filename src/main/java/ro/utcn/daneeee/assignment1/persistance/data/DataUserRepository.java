package ro.utcn.daneeee.assignment1.persistance.data;

import org.springframework.data.repository.Repository;
import ro.utcn.daneeee.assignment1.model.User;
import ro.utcn.daneeee.assignment1.persistance.UserRepository;

public interface DataUserRepository extends Repository<User, Integer>, UserRepository {

    void delete(User user);

    @Override
    default void remove(User user){
        delete(user);
    }

}
