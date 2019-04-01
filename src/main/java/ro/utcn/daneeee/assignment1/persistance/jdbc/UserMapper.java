package ro.utcn.daneeee.assignment1.persistance.jdbc;

import org.flywaydb.core.internal.jdbc.RowMapper;
import ro.utcn.daneeee.assignment1.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt("id"),
                resultSet.getString("username"),
                resultSet.getString("pass"));
    }
}
