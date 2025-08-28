package uz.pdp.repository;

import org.springframework.jdbc.core.RowMapper;
import uz.pdp.model.Todo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TodoRowMapper implements RowMapper<Todo> {
    @Override
    public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Todo todo = new Todo();
        todo.setCompleted(rs.getBoolean("completed"));
        todo.setDescription(rs.getString("description"));
        todo.setTitle(rs.getString("title"));
        todo.setId(rs.getString("id"));
        return todo;
    }
}
