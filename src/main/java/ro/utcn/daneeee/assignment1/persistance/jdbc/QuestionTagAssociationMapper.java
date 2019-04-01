package ro.utcn.daneeee.assignment1.persistance.jdbc;

import org.flywaydb.core.internal.jdbc.RowMapper;
import ro.utcn.daneeee.assignment1.model.QuestionTagAssociation;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionTagAssociationMapper implements RowMapper<QuestionTagAssociation> {

        @Override
        public QuestionTagAssociation mapRow(ResultSet resultSet) throws SQLException {

                return new QuestionTagAssociation(resultSet.getInt("id"),
                        resultSet.getInt("questionId"),
                        resultSet.getInt("tagId"));
        }
}
