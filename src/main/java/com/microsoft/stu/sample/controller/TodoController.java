package com.microsoft.stu.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microsoft.stu.sample.model.TodoItem;
import com.microsoft.stu.sample.service.TodoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/todos")
@Tag(name = "Todo Items", description = "Todo Items Management API")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @Operation(summary = "Create a new todo item", description = "Creates a new todo item with the provided details and returns the created item with generated ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo item created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TodoItem.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    @PostMapping
    public TodoItem createTodoItem(
            @Parameter(description = "Todo item to be created", required = true) @RequestBody TodoItem item) {
        return todoService.createTodoItem(item);
    }

    @Operation(summary = "Mark a todo item as done", description = "Updates the status of an existing todo item to 'done' and sets the close date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo item marked as done successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TodoItem.class))),
            @ApiResponse(responseCode = "404", description = "Todo item not found", content = @Content)
    })
    @PutMapping("/{id}/done")
    public ResponseEntity<TodoItem> markTodoItemDone(
            @Parameter(description = "ID of the todo item to be marked as done", required = true) @PathVariable Long id) {
        return todoService.markTodoItemDone(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all todo items", description = "Retrieves a list of all todo items in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of todo items", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TodoItem.class)))
    })
    @GetMapping
    public List<TodoItem> getAllTodoItems() {
        return todoService.getAllTodoItems();
    }

    @Operation(summary = "Get a todo item by ID", description = "Retrieves the details of a specific todo item based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the todo item", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TodoItem.class))),
            @ApiResponse(responseCode = "404", description = "Todo item not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> getTodoItem(
            @Parameter(description = "ID of the todo item to be retrieved", required = true) @PathVariable Long id) {
        return todoService.getTodoItem(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a todo item", description = "Deletes a todo item from the system based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo item successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Todo item not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoItem(
            @Parameter(description = "ID of the todo item to be deleted", required = true) @PathVariable Long id) {
        return todoService.deleteTodoItem(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}