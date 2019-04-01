package ro.utcn.daneeee.assignment1.persistance.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.daneeee.assignment1.model.Question;
import ro.utcn.daneeee.assignment1.persistance.QuestionRepository;

import java.util.*;

@RequiredArgsConstructor
public class JdbcQuestionRepository implements QuestionRepository {

    private final JdbcTemplate template;

    @Override
    public Question save(Question question) {
        if(question.getId() == 0){
            update(question);
        }
        else{
            int id = insert(question);
            question.setId(id);
        }
        return question;
    }

    @Override
    public Optional<Question> findById(int id) {
        List<Question> questions = template.query("SELECT * FROM question WHERE id = ?",
                (resultSet, i) -> new Question(resultSet.getInt("id"),
                        resultSet.getInt("author_id"),
                        resultSet.getString("title"),
                        resultSet.getString("text"),
                        resultSet.getDate("creation_date")
                ),
                id);
        return questions.isEmpty() ? Optional.empty() : Optional.ofNullable(questions.get(0));
    }

    @Override
    public Optional<Question> findByTitle(String text) {
        return Optional.empty();
    }

    @Override
    public void remove(Question question) {
        template.update("DELETE FROM question WHERE id = ?",question.getId());
    }

    @Override
    public List<Question> findAll() {
        return template.query("SELECT * FROM question",
                (resultSet, i) -> new Question(resultSet.getInt("id"),
                        resultSet.getInt("author_id"),
                        resultSet.getString("title"),
                        resultSet.getString("text"),
                        resultSet.getDate("creation_date")));
    }

    private int insert(Question question){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("question");
        insert.setGeneratedKeyName("id");

        Map<String, Object> data = new HashMap<>();
        data.put("title", question.getTitle());
        data.put("text", question.getText());
        return insert.executeAndReturnKey(data).intValue();
    }

    private void update(Question question){
        template.update("UPDATE question SET title = ?, text = ? WHERE id = ?",
                question.getTitle(),
                question.getText(),
                question.getId());
    }
}
