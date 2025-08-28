package uz.pdp.repository.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.pdp.model.Todo;
import uz.pdp.repository.TodoRepository;
import uz.pdp.repository.TodoRowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Profile("!dev")
public class TodoRepositoryImpl implements TodoRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TodoRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Todo> findAll() {
        String sql = "select * from todos";
        return jdbcTemplate.query(sql, new TodoRowMapper());
    }

    public Optional<Todo> findById(String id) {

        String sql = "select * from todos where id = ?";

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new TodoRowMapper()));
    }


    @Override
    public void save(Todo todo) {

        Todo exist = findById(todo.getId()).orElse(null);

        String sql = (exist != null) ?
                "update todo set title = :title, description = :description, completed = :completed where id = :id"
                : "insert into todo(id, title, description, completed) values(:id, :title, :description, :completed)";

        Map<String, Object> params = new HashMap<>();
        params.put("id", todo.getId());
        params.put("title", todo.getTitle());
        params.put("description", todo.getDescription());
        params.put("completed", todo.isCompleted());

        namedParameterJdbcTemplate.update(sql, params);
    }
//    @Override
//    public void save(Todo todo) {
//
//        Todo exist = findById(todo.getId()).orElse(null);
//
//        String sql = (exist != null) ?
//                "update todo set title = ?, description = ?, completed = ? where id = ?"
//                : "insert into todo(id, title, description, completed) values(?, ?, ?, ?)";
//
//        jdbcTemplate.update(sql, todo.getTitle(), todo.getDescription(), todo.isCompleted(), todo.getId());
//    }

    public void delete(String id) {
        jdbcTemplate.update("delete from todo where id = ?", id);
    }


    //    @Override
//    public List<Todo> findAll() {
//
//        String sql = "select * from todos";
//
//        return jdbcTemplate.query(sql,
//                (rs, rowNum) -> {
//                    Todo todo = new Todo();
//                    todo.setId(rs.getString("id"));
//                    todo.setTitle(rs.getString("title"));
//                    todo.setDescription(rs.getString("description"));
//                    todo.setCompleted(rs.getBoolean("completed"));
//                    return todo;
//                });
//    }


    //    public Optional<Todo> findById(String id) {
//
//        String sql = "select * from todos where id = ?";
//
//        return Optional.ofNullable(jdbcTemplate.queryForObject(sql,
//                (rs, rowNum) -> new Todo(rs.getString("id"), rs.getString("title"), rs.getString("description"), rs.getBoolean("completed"))));
//    }

}
