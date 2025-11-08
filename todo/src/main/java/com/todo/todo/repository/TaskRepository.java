package com.todo.todo.repository;

import com.todo.todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCategoryId(Long categoryId);
    List<Task> findByCompleted(boolean completed);

    @Query("select t.category.id, count(t) from Task t group by t.category.id")
    List<Object[]> countByCategory();
}
