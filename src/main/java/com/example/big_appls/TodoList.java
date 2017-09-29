package com.example.big_appls;

import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
@UIScope
public class TodoList extends VerticalLayout implements TodoChangeListener{

    @Autowired
    TodoRepository repository;
    List<Todo> todos;

    @PostConstruct
    void init(){
        setSpacing(true);

        update();
    }

    private void update() {
        setTodos(repository.findAll());
    }

    private void setTodos(List<Todo> todos)
    {
        this.todos = todos;
        removeAllComponents();
        todos.forEach(todo->{addComponent(new TodoLoyout(todo, this));});
    }

    public void save(Todo todo) {
        repository.save(todo);
        update();
    }

    @Override
    public void todoChange(Todo todo) {
        save(todo);
    }

    public void deleteCompleted()
    {
        repository.deleteInBatch(
        todos.stream().filter(Todo::isDone).collect(Collectors.toList()));

        update();
    }
}
