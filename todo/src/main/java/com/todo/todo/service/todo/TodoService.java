package com.todo.todo.service.todo;

import com.todo.todo.model.Task;

import java.time.LocalDateTime;
import java.util.List;

public interface TodoService {
    Task create(String title, String detail, Long categoryId, LocalDateTime dueAt);
    Task markCompleted(Long taskId, boolean completed);
    List<Task> listAll(); List<Task> listByCategory(Long categoryId);
    void delete(Long taskId);
}

