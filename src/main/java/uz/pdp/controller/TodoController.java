package uz.pdp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.config.CustomUserDetails;
import uz.pdp.model.Todo;
import uz.pdp.service.TodoService;

import java.util.List;

@Controller
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping({"/", "/index"})
    public ModelAndView todosPage(@AuthenticationPrincipal CustomUserDetails sessionUser) {
        ModelAndView modelAndView = new ModelAndView("index");

//        CustomUserDetails sessionUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String id = sessionUser.getId();
        List<Todo> todos = service.getAll();
        modelAndView.addObject("todos", todos);
        return modelAndView;
    }

    @GetMapping("/add")
    public String addPage() {
        return "add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Todo todo) {
        service.create(todo);
        return "index";
    }
}
