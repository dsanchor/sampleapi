package com.microsoft.stu.sample.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Schema(description = "Todo Item Model - Represents a task to be completed")
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the todo item", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Name or title of the todo item", example = "Complete Spring Boot API", required = true)
    private String name;

    @Schema(description = "Detailed description of the todo item", example = "Implement all required endpoints and add proper documentation")
    private String details;

    @Schema(description = "Date and time when the todo item was created", example = "2023-01-20T10:30:00", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime creationDate;

    @Schema(description = "Date and time when the todo item was completed", example = "2023-01-25T15:45:00", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime closeDate;

    @Schema(description = "Status flag indicating if the todo item is completed", example = "false", defaultValue = "false")
    private boolean done;

    public TodoItem() {
    }

    public TodoItem(String name, String details) {
        this.name = name;
        this.details = details;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(LocalDateTime closeDate) {
        this.closeDate = closeDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}