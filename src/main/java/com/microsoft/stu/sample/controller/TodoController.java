package com.microsoft.stu.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microsoft.stu.sample.model.TodoItem;
import com.microsoft.stu.sample.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public TodoItem createTodoItem(@RequestBody TodoItem item) {
        return todoService.createTodoItem(item);
    }

    @PutMapping("/{id}/done")
    public ResponseEntity<TodoItem> markTodoItemDone(@PathVariable Long id) {
        return todoService.markTodoItemDone(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<TodoItem> getAllTodoItems() {
        return todoService.getAllTodoItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> getTodoItem(@PathVariable Long id) {
        return todoService.getTodoItem(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoItem(@PathVariable Long id) {
        return todoService.deleteTodoItem(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}