package uz.pdp.service;

import org.springframework.stereotype.Service;
import uz.pdp.model.Todo;
import uz.pdp.repository.TodoRepository;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<Todo> getAll() {
        return repository.findAll();
    }

    public void create(Todo todo) {
        repository.save(todo);


    }
}
