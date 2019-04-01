package ro.utcn.daneeee.assignment1.persistance;

import ro.utcn.daneeee.assignment1.model.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository {

    Question save(Question question);

    Optional<Question> findById(int id);

    Optional<Question> findByTitle(String text);

    void remove(Question question);

    List<Question> findAll();
}
