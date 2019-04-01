package ro.utcn.daneeee.assignment1.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ro.utcn.daneeee.assignment1.model.User;
import ro.utcn.daneeee.assignment1.persistance.RepositoryFactory;
import ro.utcn.daneeee.assignment1.persistance.UserRepository;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserSeed implements CommandLineRunner {

    private final RepositoryFactory factory;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        UserRepository repository = factory.createUserRepository();
        if(repository.findAll().isEmpty()){
            repository.save(new User("user1", "1111"));
            repository.save(new User("user2", "2222"));
            repository.save(new User("user3", "3333"));
        }
    }
}
