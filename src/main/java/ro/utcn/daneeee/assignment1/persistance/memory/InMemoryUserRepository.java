package ro.utcn.daneeee.assignment1.persistance.memory;

import ro.utcn.daneeee.assignment1.exception.UserNotFoundException;
import ro.utcn.daneeee.assignment1.model.User;
import ro.utcn.daneeee.assignment1.persistance.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryUserRepository implements UserRepository{

    private final Map<Integer, User> data = new ConcurrentHashMap<>();
    private final AtomicInteger currentId = new AtomicInteger(0);

    @Override
    public User save(User user) {
        if(user.getId() == null){
            user.setId(currentId.incrementAndGet());
        }
        data.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public Optional<User> findByUsername(String user) {
        List<User> l = findAll();
        for(User u : l ){
            if(u.getUsername().equals(user)){
                return Optional.ofNullable(u);
            }
        }
        return Optional.ofNullable(data.get(user));
        //stiu ca nu e ok
    }

    @Override
    public void remove(User user) {
        data.remove(user.getId());
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(data.values());
    }
}
