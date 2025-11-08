package com.todo.todo.controller;

import com.todo.todo.dto.CreateTaskRequest;
import com.todo.todo.model.Task;
import com.todo.todo.service.todo.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// controller/TodoController.java
@RestController
@RequestMapping("/api/tasks") @RequiredArgsConstructor
public class TodoController {
    private final TodoService service;
    @PostMapping
    public Task create(@Valid @RequestBody CreateTaskRequest r){ return service.create(r.getTitle(), r.getDetail(), r.getCategoryId(), r.getDueAt()); }
    @PatchMapping("/{id}/completed") public Task toggle(@PathVariable Long id, @RequestParam boolean value){ return service.markCompleted(id, value); }
    @GetMapping public List<Task> listAll(){ return service.listAll(); }
    @GetMapping("/by-category/{categoryId}") public List<Task> listByCategory(@PathVariable Long categoryId){ return service.listByCategory(categoryId); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id){ service.delete(id); }
}

