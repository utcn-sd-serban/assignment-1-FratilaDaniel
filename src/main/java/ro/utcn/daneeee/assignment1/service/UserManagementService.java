package ro.utcn.daneeee.assignment1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ro.utcn.daneeee.assignment1.exception.UserNotFoundException;
import ro.utcn.daneeee.assignment1.model.User;
import ro.utcn.daneeee.assignment1.persistance.RepositoryFactory;
import ro.utcn.daneeee.assignment1.persistance.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserManagementService {

    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<User> listUsers() {
        return repositoryFactory.createUserRepository().findAll();
    }

    @Transactional
    public User addUser(String username, String pass) {
        return repositoryFactory.createUserRepository().save(new User(username, pass));
    }

    public User findById(int id){
        return repositoryFactory.createUserRepository().findById(id).orElseThrow(UserNotFoundException::new);
    }


    public User findByUsername(String user){
        return repositoryFactory.createUserRepository().findByUsername(user).orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public void updatePassword(int id, String newPwd) {
        UserRepository repository = repositoryFactory.createUserRepository();
        User user = repository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setPass(newPwd);
        repository.save(user);
    }

    @Transactional
    public void removeUser(int id) {
        UserRepository repository = repositoryFactory.createUserRepository();
        User user = repository.findById(id).orElseThrow(UserNotFoundException::new);
        repository.remove(user);
    }
}