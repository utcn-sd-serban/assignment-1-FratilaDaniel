package ro.utcn.daneeee.assignment1.persistance.data;

import org.springframework.data.repository.Repository;
import ro.utcn.daneeee.assignment1.model.Question;
import ro.utcn.daneeee.assignment1.persistance.QuestionRepository;


public interface DataQuestionRepository extends Repository<Question, Integer>, QuestionRepository {

    void delete(Question question);

    @Override
    default void remove(Question question) {
        delete(question);
    }
}
