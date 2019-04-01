package ro.utcn.daneeee.assignment1.persistance.jpa;

import lombok.RequiredArgsConstructor;
import ro.utcn.daneeee.assignment1.model.Question;
import ro.utcn.daneeee.assignment1.persistance.QuestionRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HibernateQuestionRepository implements QuestionRepository {

    private final EntityManager entityManager;

    //findAllByOrderByDateAsc
    @Override
    public List<Question> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Question> query = builder.createQuery(Question.class);
        query.select(query.from(Question.class));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Question save(Question question) {
        if (question.getId() == null) {
            entityManager.persist(question);
            return question;
        } else {
            return entityManager.merge(question);
        }
    }

    @Override
    public void remove(Question question) {
        entityManager.remove(question);
    }

    @Override
    public Optional<Question> findById(int id) {
        return Optional.ofNullable(entityManager.find(Question.class, id));
    }

    @Override
    public Optional<Question> findByTitle(String text) {
        return Optional.empty();
    }
}
