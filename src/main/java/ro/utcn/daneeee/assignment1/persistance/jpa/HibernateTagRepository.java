package ro.utcn.daneeee.assignment1.persistance.jpa;

import lombok.RequiredArgsConstructor;
import ro.utcn.daneeee.assignment1.model.Tag;
import ro.utcn.daneeee.assignment1.persistance.TagRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HibernateTagRepository implements TagRepository {
    private final EntityManager entityManager;

    @Override
    public Tag save(Tag tag) {
        if (tag.getId() == null) {
            entityManager.persist(tag);
            return tag;
        } else {
            return entityManager.merge(tag);
        }    }

    @Override
    public Optional<Tag> findById(int id) {
        return Optional.ofNullable(entityManager.find(Tag.class, id));
    }

    @Override
    public Optional<Tag> findByContent(String searchedTag) {//?
        //return Optional.ofNullable(entityManager.find(Tag.class, searchedTag));
        return Optional.empty();
    }

    @Override
    public void remove(Tag tag) {
        entityManager.remove(tag);
    }

    @Override
    public List<Tag> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> query = builder.createQuery(Tag.class);
        query.select(query.from(Tag.class));
        return entityManager.createQuery(query).getResultList();
    }

}
