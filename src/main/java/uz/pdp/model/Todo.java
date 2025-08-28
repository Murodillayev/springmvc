package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
public class Todo {
    private String id;
    private String title;
    private String description;
    private boolean completed;

    public Todo() {
        this.id = UUID.randomUUID().toString();
    }
}
