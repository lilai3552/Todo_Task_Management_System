package com.todo.todo.controller;

import com.todo.todo.dto.CreateCategoryRequest;
import com.todo.todo.model.Category;
import com.todo.todo.service.category.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/categories") @RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;
    @PostMapping
    public Category create(@Valid @RequestBody CreateCategoryRequest r){ return service.create(r.getName(), r.getDescription()); }
    @GetMapping
    public List<Category> list(){ return service.list(); }
    @GetMapping("/{id}") public Category get(@PathVariable Long id){ return service.get(id); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id){ service.delete(id); }
}

