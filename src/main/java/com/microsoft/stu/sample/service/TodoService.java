package com.microsoft.stu.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsoft.stu.sample.model.TodoItem;
import com.microsoft.stu.sample.repository.TodoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository repository;

    @Autowired
    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    // Creates new item with automatic creationDate and no closeDate
    public TodoItem createTodoItem(TodoItem item) {
        item.setCreationDate(LocalDateTime.now());
        item.setDone(false);
        item.setCloseDate(null);
        return repository.save(item);
    }

    // Marks the item as done, setting done flag and closeDate automatically
    public Optional<TodoItem> markTodoItemDone(Long id) {
        Optional<TodoItem> itemOpt = repository.findById(id);
        itemOpt.ifPresent(item -> {
            item.setDone(true);
            item.setCloseDate(LocalDateTime.now());
            repository.save(item);
        });
        return itemOpt;
    }

    public List<TodoItem> getAllTodoItems() {
        return repository.findAll();
    }

    public Optional<TodoItem> getTodoItem(Long id) {
        return repository.findById(id);
    }

    public boolean deleteTodoItem(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}