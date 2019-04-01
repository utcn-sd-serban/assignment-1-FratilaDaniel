package ro.utcn.daneeee.assignment1.persistance.data;

import org.springframework.data.repository.Repository;
import ro.utcn.daneeee.assignment1.model.QuestionTagAssociation;
import ro.utcn.daneeee.assignment1.persistance.QuestionTagAssociationRepository;

public interface DataQuestionTagAssociationRepository extends Repository<QuestionTagAssociation, Integer>, QuestionTagAssociationRepository {
    void delete(QuestionTagAssociation association);

    @Override
    default void remove(QuestionTagAssociation association){
        delete(association);
    }

}
