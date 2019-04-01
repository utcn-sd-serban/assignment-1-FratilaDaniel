package ro.utcn.daneeee.assignment1.persistance.memory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ro.utcn.daneeee.assignment1.persistance.*;


@Component
@ConditionalOnProperty(name = "assignment1.repository-type", havingValue = "MEMORY")
public class InMemoryRepositoryFactory implements RepositoryFactory {

    private final InMemoryUserRepository userRepository = new InMemoryUserRepository();
    private final InMemoryQuestionRepository questionRepository = new InMemoryQuestionRepository();
    private final InMemoryTagRepository tagRepository = new InMemoryTagRepository();
    private final InMemoryQuestionTagAssociationRepository questionTagAssociationRepository = new InMemoryQuestionTagAssociationRepository();

    @Override
    public QuestionRepository createQuestionRepository() {
        return questionRepository;
    }

    @Override
    public UserRepository createUserRepository() {
        return userRepository;
    }

    @Override
    public TagRepository createTagRepository() {
        return tagRepository;
    }

    @Override
    public QuestionTagAssociationRepository createQuestionTagAssociationRepository() {
        return questionTagAssociationRepository;
    }


}
