package ro.utcn.daneeee.assignment1.persistance.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ro.utcn.daneeee.assignment1.persistance.*;


@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "assignment1.repository-type", havingValue = "JDBC")
public class JdbcRepositoryFactory implements RepositoryFactory {

    private final JdbcTemplate template;

    @Override
    public QuestionRepository createQuestionRepository() {
        return new JdbcQuestionRepository(template);
    }

    @Override
    public TagRepository createTagRepository() {
        return new JdbcTagRepository(template);
    }

    @Override
    public UserRepository createUserRepository() {
        return new JdbcUserRepository(template);
    }

    @Override
    public QuestionTagAssociationRepository createQuestionTagAssociationRepository() {
        return new JdbcQuestionTagAssociationRepository(template);
    }
}
