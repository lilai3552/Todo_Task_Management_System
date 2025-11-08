package com.todo.todo.service.category;

import com.todo.todo.model.Category;

import java.util.List;

public interface CategoryService {
    Category create(String name, String description);
    List<Category> list(); Category get(Long id); void delete(Long id);
}