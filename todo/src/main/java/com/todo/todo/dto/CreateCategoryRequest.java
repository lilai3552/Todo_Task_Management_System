package com.todo.todo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCategoryRequest {
    @NotBlank(message="name is required") private String name;
    private String description;
}