package ro.utcn.daneeee.assignment1.persistance.jpa;

import lombok.RequiredArgsConstructor;
import ro.utcn.daneeee.assignment1.model.User;
import ro.utcn.daneeee.assignment1.persistance.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HibernateUserRepository implements UserRepository {

    private final EntityManager entityManager;

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
            return user;
        } else {
            return entityManager.merge(user);
        }    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public Optional<User> findByUsername(String user) {
        return Optional.empty();
    }

    @Override
    public void remove(User user) {
        entityManager.remove(user);
    }

    @Override
    public List<User> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        query.select(query.from(User.class));
        return entityManager.createQuery(query).getResultList();
    }
}
