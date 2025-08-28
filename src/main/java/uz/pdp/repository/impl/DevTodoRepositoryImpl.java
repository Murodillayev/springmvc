package uz.pdp.repository.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import uz.pdp.model.Todo;
import uz.pdp.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("dev")
public class DevTodoRepositoryImpl implements TodoRepository {

    private static final List<Todo> todos = new ArrayList<>();
    @Override
    public List<Todo> findAll() {
        return todos;
    }

    @Override
    public void save(Todo todo) {
        todos.add(todo);
    }
}
