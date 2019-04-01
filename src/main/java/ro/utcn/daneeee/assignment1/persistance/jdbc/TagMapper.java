package ro.utcn.daneeee.assignment1.persistance.jdbc;

import org.flywaydb.core.internal.jdbc.RowMapper;
import ro.utcn.daneeee.assignment1.model.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagMapper implements RowMapper<Tag> {

    @Override
    public Tag mapRow(ResultSet resultSet) throws SQLException {
        return new Tag(resultSet.getInt("id"),
                resultSet.getString("content"));
    }

}
