package ro.utcn.daneeee.assignment1.persistance.jpa;

import lombok.RequiredArgsConstructor;
import ro.utcn.daneeee.assignment1.model.QuestionTagAssociation;
import ro.utcn.daneeee.assignment1.persistance.QuestionTagAssociationRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HibernateQuestionTagAssociationRepository implements QuestionTagAssociationRepository {

    private final EntityManager entityManager;


    @Override
    public List<QuestionTagAssociation> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<QuestionTagAssociation> query = builder.createQuery(QuestionTagAssociation.class);
        query.select(query.from(QuestionTagAssociation.class));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Integer> findByTagId(int id) {
        return null;
    }

    @Override
    public List<Integer> findByQuestionId(int id) {
        return null;
    }

    @Override
    public QuestionTagAssociation save(QuestionTagAssociation association) {
        if (association.getId() == null) {
            entityManager.persist(association);
            return association;
        } else {
            return entityManager.merge(association);
        }
    }

    @Override
    public void remove(QuestionTagAssociation association) {
        entityManager.remove(association);
    }

    @Override
    public Optional<QuestionTagAssociation> findById(int id) {
        return Optional.ofNullable(entityManager.find(QuestionTagAssociation.class, id));
    }
}
