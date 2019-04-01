package ro.utcn.daneeee.assignment1.persistance.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.daneeee.assignment1.model.User;
import ro.utcn.daneeee.assignment1.persistance.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate template;

    @Override
    public User save(User user) {
        if(user.getId() == 0){
            update(user);
        }
        else{
            int id = insert(user);
            user.setId(id);
        }
        return user;
    }

    @Override
    public Optional<User> findById(int id) {
        List<User> questions = template.query("SELECT * FROM verde WHERE id = ?",
                (resultSet, i) -> new User(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("pass")
                        ),
                id);
        return questions.isEmpty() ? Optional.empty() : Optional.ofNullable(questions.get(0));
    }

    @Override
    public Optional<User> findByUsername(String user) {
        List<User> questions = template.query("SELECT * FROM verde WHERE username = ?",
                (resultSet, i) -> new User(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("pass")
                ),
                user);
        return questions.isEmpty() ? Optional.empty() : Optional.ofNullable(questions.get(0));    }

    @Override
    public void remove(User user) {
        template.update("DELETE FROM verde WHERE id = ?", user.getId());
    }

    @Override
    public List<User> findAll() {
        return template.query("SELECT * FROM verde",
                (resultSet, i) -> new User(resultSet.getInt("id"),
                                                resultSet.getString("username"),
                                                resultSet.getString("pass")));
    }

    private int insert(User user){
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("verde");
        insert.setGeneratedKeyName("id");

        Map<String, Object> data = new HashMap<>();
        data.put("username", user.getUsername());
        data.put("pass", user.getPass());
        return insert.executeAndReturnKey(data).intValue();
    }

    private void update(User user){
        template.update("UPDATE verde SET username = ?, pass = ? WHERE id = ?",
                user.getUsername(),
                user.getPass(),
                user.getId());
    }

}
