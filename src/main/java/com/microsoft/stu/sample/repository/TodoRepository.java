package com.microsoft.stu.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microsoft.stu.sample.model.TodoItem;

public interface TodoRepository extends JpaRepository<TodoItem, Long> {
}