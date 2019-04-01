package ro.utcn.daneeee.assignment1.persistance.data;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ro.utcn.daneeee.assignment1.persistance.*;


@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "assignment1.repository-type", havingValue = "DATA")
public class DataRepositoryFactory implements RepositoryFactory {

    private final DataUserRepository userRepository;
    private final DataQuestionRepository questionRepository;
    private final DataTagRepository tagRepository;
    private final DataQuestionTagAssociationRepository questionTagAssociationRepository;

    @Override
    public UserRepository createUserRepository() {
        return userRepository;
    }

    @Override
    public QuestionRepository createQuestionRepository() {
        return questionRepository;
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
