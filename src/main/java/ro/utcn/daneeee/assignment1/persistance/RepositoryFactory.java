package ro.utcn.daneeee.assignment1.persistance;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

public interface RepositoryFactory {

    QuestionRepository createQuestionRepository();

    TagRepository createTagRepository();

    UserRepository createUserRepository();

    QuestionTagAssociationRepository createQuestionTagAssociationRepository();
}
