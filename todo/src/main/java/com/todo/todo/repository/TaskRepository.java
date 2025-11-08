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

    // 新增：按“分类名称”做聚合（包含没有分类的任务，名称显示为 '—'）
    @Query("select coalesce(c.name, '—') as catName, count(t) " +
            "from Task t left join t.category c " +
            "group by c.name " +
            "order by catName asc")
    List<Object[]> countByCategoryName();
}
