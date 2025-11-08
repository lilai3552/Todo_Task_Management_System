package com.todo.todo.service.category;

import com.todo.todo.model.Category;
import com.todo.todo.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repo;
    public Category create(String name, String description){
        repo.findByName(name).ifPresent(c -> { throw new IllegalArgumentException("Category exists"); });
        return repo.save(Category.builder().name(name).description(description).build());
    }
    public List<Category> list(){ return repo.findAll(); }
    public Category get(Long id){ return repo.findById(id).orElseThrow(); }
    public void delete(Long id){ repo.deleteById(id); }
}
