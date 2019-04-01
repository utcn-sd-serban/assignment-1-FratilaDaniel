package ro.utcn.daneeee.assignment1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ro.utcn.daneeee.assignment1.exception.QuestionNotFoundException;
import ro.utcn.daneeee.assignment1.exception.UserNotFoundException;
import ro.utcn.daneeee.assignment1.model.Question;
import ro.utcn.daneeee.assignment1.model.User;
import ro.utcn.daneeee.assignment1.persistance.QuestionRepository;
import ro.utcn.daneeee.assignment1.persistance.RepositoryFactory;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionManagementService {

    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<Question> listQuestions(){
        return repositoryFactory.createQuestionRepository().findAll();
    }

    @Transactional
    public Question addQuestion(int authorId, String title, String text, Calendar creationDate/*, List<String> tags*/){
        return repositoryFactory.createQuestionRepository().save(new Question(authorId, title, text, creationDate.getTime()));
    }

    @Transactional
    public Question addQuestion(int authorId, String title, String text, int year, int month, int day){
        String date = day + "/"+ month + "/" + year;
        return repositoryFactory.createQuestionRepository().save(new Question(authorId, title, text, date));
    }

    @Transactional
    public Question findById(int id){
        return repositoryFactory.createQuestionRepository().findById(id).orElseThrow(QuestionNotFoundException::new);
    }

    @Transactional
    public Question findByTitle(String text){
        return repositoryFactory.createQuestionRepository().findByTitle(text).orElseThrow(QuestionNotFoundException::new);
    }

    @Transactional
    public void removeQuestion(int id){
        QuestionRepository repository = repositoryFactory.createQuestionRepository();
        Question question = repository.findById(id).orElseThrow(QuestionNotFoundException::new);
        repository.remove(question);
    }

}
