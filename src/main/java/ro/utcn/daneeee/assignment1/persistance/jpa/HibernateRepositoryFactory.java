package ro.utcn.daneeee.assignment1.persistance.jpa;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.daneeee.assignment1.persistance.*;

import javax.persistence.EntityManager;
import javax.persistence.Table;

@RequiredArgsConstructor
@ConditionalOnProperty(name = "assignment1.repository-type", havingValue = "JPA")
public class HibernateRepositoryFactory implements RepositoryFactory {

    private final EntityManager entityManager;

    @Override
    public QuestionRepository createQuestionRepository() {
        return new HibernateQuestionRepository(entityManager);
    }

    @Override
    public UserRepository createUserRepository() {
        return new HibernateUserRepository(entityManager);
    }

    @Override
    public TagRepository createTagRepository(){return new HibernateTagRepository(entityManager);}

    @Override
    public QuestionTagAssociationRepository createQuestionTagAssociationRepository() {
        return new HibernateQuestionTagAssociationRepository(entityManager);
    }

}
