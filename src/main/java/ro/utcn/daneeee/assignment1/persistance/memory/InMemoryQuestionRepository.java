package ro.utcn.daneeee.assignment1.persistance.memory;

import org.springframework.stereotype.Repository;
import ro.utcn.daneeee.assignment1.model.Question;
import ro.utcn.daneeee.assignment1.persistance.QuestionRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryQuestionRepository implements QuestionRepository {

    private final Map<Integer, Question> data = new ConcurrentHashMap<>();
    private final AtomicInteger currentId = new AtomicInteger(0);

    @Override
    public Question save(Question question) {
        if(question.getId() == null){
            question.setId(currentId.incrementAndGet());
        }
        data.put(question.getId(), question);
        return question;
    }

    @Override
    public Optional<Question> findById(int id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public Optional<Question> findByTitle(String text){
        List<Question> questions = findAll();
        for(Question currentQuestion: questions){
            if(currentQuestion.getTitle().equals(text)){
                return Optional.ofNullable(currentQuestion);
            }
        }
        return Optional.empty();
    }

    @Override
    public void remove(Question question) {
        data.remove(question.getId());
    }

    @Override
    public List<Question> findAll() {
        List<Question> list = new ArrayList<>(data.values());
        list.sort(new Comparator<Question>() {
            @Override
            public int compare(Question o1, Question o2) {
                if(o1.getCreationDate().getTime() < o2.getCreationDate().getTime()){
                    return 1;
                }
                else if (o1.getCreationDate().getTime() == o2.getCreationDate().getTime()){
                    if(o1.getId() < o2.getId()){
                        return 1;
                    }
                    else{
                        return -1;
                    }
                }
                else{
                    return -1;
                }
            }
        });
        return list;
    }




}