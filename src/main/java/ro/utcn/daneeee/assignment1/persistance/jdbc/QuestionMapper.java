package ro.utcn.daneeee.assignment1.persistance.jdbc;

import org.flywaydb.core.internal.jdbc.RowMapper;
import ro.utcn.daneeee.assignment1.model.Question;

import java.sql.Array;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class QuestionMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet resultSet) throws SQLException {

        return new Question(resultSet.getInt("id"),
                resultSet.getInt("author_id"),
                resultSet.getString("title"),
                resultSet.getString("text"),
                resultSet.getDate("creation_date")
                );

    }
}
