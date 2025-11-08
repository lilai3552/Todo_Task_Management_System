package com.todo.todo.service.todo;

import com.todo.todo.model.Category;
import com.todo.todo.model.Task;
import com.todo.todo.repository.CategoryRepository;
import com.todo.todo.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoServiceImpl implements TodoService {
    private final TaskRepository taskRepo; private final CategoryRepository catRepo;
    public Task create(String title, String detail, Long categoryId, LocalDateTime dueAt){
        Category cat = (categoryId==null)? null : catRepo.findById(categoryId).orElseThrow();
        Task t = Task.builder().title(title).detail(detail).completed(false)
                .dueAt(dueAt).createdAt(LocalDateTime.now()).category(cat).build();
        return taskRepo.save(t);
    }
    public Task markCompleted(Long id, boolean completed){
        Task t = taskRepo.findById(id).orElseThrow(); t.setCompleted(completed); return taskRepo.save(t);
    }
    public List<Task> listAll(){ return taskRepo.findAll(); }
    public List<Task> listByCategory(Long categoryId){ return taskRepo.findByCategoryId(categoryId); }
    public void delete(Long id){ taskRepo.deleteById(id); }
}
