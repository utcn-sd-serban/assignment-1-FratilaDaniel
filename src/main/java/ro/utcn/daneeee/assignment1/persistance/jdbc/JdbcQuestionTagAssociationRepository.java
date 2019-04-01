package ro.utcn.daneeee.assignment1.persistance.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.daneeee.assignment1.model.QuestionTagAssociation;
import ro.utcn.daneeee.assignment1.persistance.QuestionTagAssociationRepository;

import java.util.*;

@RequiredArgsConstructor
public class JdbcQuestionTagAssociationRepository implements QuestionTagAssociationRepository {
    private final JdbcTemplate template;

    @Override
    public QuestionTagAssociation save(QuestionTagAssociation association) {
        if(association.getId() == 0){
            update(association);
        }
        else{
            int id = insert(association);
            association.setId(id);
        }
        return association;
    }

    @Override
    public Optional<QuestionTagAssociation> findById(int id) {
        List<QuestionTagAssociation> questions = template.query("SELECT * FROM questiontags WHERE id = ?",
                (resultSet, i) -> new QuestionTagAssociation(resultSet.getInt("id"),
                        resultSet.getInt("question_id"),
                        resultSet.getInt("tag_id")
                ),
                id);
        return questions.isEmpty() ? Optional.empty() : Optional.ofNullable(questions.get(0));
    }

    @Override
    public void remove(QuestionTagAssociation association) {
        template.update("DELETE FROM questiontags WHERE id = ?", association.getId());
    }

    @Override
    public List<QuestionTagAssociation> findAll() {
        return template.query("SELECT * FROM questiontags",
                (resultSet, i) -> new QuestionTagAssociation(resultSet.getInt("id"),
                        resultSet.getInt("question_id"),
                        resultSet.getInt("tag_id")));
    }

    @Override
    public List<Integer> findByTagId(int id) {
        List<QuestionTagAssociation> l = template.query("SELECT question_id FROM questiontags WHERE tag_id = ?",
                (resultSet, i) -> new QuestionTagAssociation(resultSet.getInt("id"),
                        resultSet.getInt("question_id"),
                        resultSet.getInt("tag_id")),
                id);
        List<Integer> filteredList = new ArrayList<Integer>();
        for(QuestionTagAssociation q: l){
            filteredList.add(q.getQuestionId());
        }
        return filteredList;
    }

    @Override
    public List<Integer> findByQuestionId(int id) {

        List<QuestionTagAssociation> l = template.query("SELECT tag_id FROM questiontags WHERE question_id = ?",
                (resultSet, i) -> new QuestionTagAssociation(resultSet.getInt("id"),
                        resultSet.getInt("question_id"),
                        resultSet.getInt("tag_id")),
                id);
        List<Integer> filteredList = new ArrayList<Integer>();
        for(QuestionTagAssociation q: l){
            filteredList.add(q.getTagId());
        }
        return filteredList;
    }

    private int insert(QuestionTagAssociation association){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("questiontags");
        insert.setGeneratedKeyName("id");

        Map<String, Object> data = new HashMap<>();
        data.put("question_id", association.getQuestionId());
        data.put("tag_id", association.getTagId());
        return insert.executeAndReturnKey(data).intValue();
    }

    private void update(QuestionTagAssociation association){
        template.update("UPDATE questiontags SET question_id = ?, tag_id = ? WHERE id = ?",
                association.getQuestionId(),
                association.getTagId(),
                association.getId());
    }
}
