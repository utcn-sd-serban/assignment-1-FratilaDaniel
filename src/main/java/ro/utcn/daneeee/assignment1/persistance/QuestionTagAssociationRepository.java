package ro.utcn.daneeee.assignment1.persistance;

import ro.utcn.daneeee.assignment1.model.QuestionTagAssociation;

import java.util.List;
import java.util.Optional;

public interface QuestionTagAssociationRepository {


    QuestionTagAssociation save(QuestionTagAssociation association);

    Optional<QuestionTagAssociation> findById(int id);

    void remove(QuestionTagAssociation association);

    List<QuestionTagAssociation> findAll();

    List<Integer> findByTagId(int id);

    List<Integer> findByQuestionId(int id);

}
