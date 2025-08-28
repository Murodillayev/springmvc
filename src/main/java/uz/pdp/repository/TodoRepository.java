package uz.pdp.repository;

import uz.pdp.model.Todo;

import java.util.List;

public interface TodoRepository {
    List<Todo> findAll();

    void save(Todo todo);
}
