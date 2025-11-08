package com.todo.todo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateTaskRequest {
    @NotBlank(message="title is required") private String title;
    private String detail;
    private Long categoryId;
    private LocalDateTime dueAt;
}
