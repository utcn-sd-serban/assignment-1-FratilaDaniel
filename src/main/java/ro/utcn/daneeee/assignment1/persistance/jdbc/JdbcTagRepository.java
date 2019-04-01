package ro.utcn.daneeee.assignment1.persistance.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.daneeee.assignment1.model.Question;
import ro.utcn.daneeee.assignment1.model.Tag;
import ro.utcn.daneeee.assignment1.persistance.TagRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JdbcTagRepository implements TagRepository {

    private final JdbcTemplate template;

    @Override
    public Tag save(Tag tag) {
        if(tag.getId() == 0){
            update(tag);
        }
        else{
            int id = insert(tag);
            tag.setId(id);
        }
        return tag;
    }

    @Override
    public Optional<Tag> findById(int id) {
        List<Tag> tags = template.query("SELECT * FROM apptags WHERE id = ?",
                (resultSet, i) -> new Tag(resultSet.getInt("id"),
                        resultSet.getString("content")
                        ),
                id);
        return tags.isEmpty() ? Optional.empty() : Optional.ofNullable(tags.get(0));
    }


    @Override
    public Optional<Tag> findByContent(String text) {
        List<Tag> tags = template.query("SELECT * FROM apptags WHERE content = ?",
                (resultSet, i) -> new Tag(resultSet.getInt("id"),
                        resultSet.getString("content")
                        ),
                text);
        return tags.isEmpty() ? Optional.empty() : Optional.ofNullable(tags.get(0));
    }


    @Override
    public void remove(Tag tag) {
        template.update("DELETE FROM tag WHERE id = ?", tag.getId());
    }

    @Override
    public List<Tag> findAll() {
        return template.query("SELECT * FROM apptags",
                (resultSet, i) -> new Tag(resultSet.getInt("id"),
                                                resultSet.getString("content")));
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


    private int insert(Tag tag){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("tag");
        insert.setGeneratedKeyName("id");

        Map<String, Object> data = new HashMap<>();
        data.put("content", tag.getContent());
        return insert.executeAndReturnKey(data).intValue();
    }

    private void update(Tag tag){
        template.update("UPDATE tag SET content = ? WHERE id = ?",
                tag.getContent(),
                tag.getId());
    }
}
