package com.todo.todo;

import com.todo.todo.model.Category;
import com.todo.todo.repository.CategoryRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class TodoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

    @Bean
    public org.springframework.boot.CommandLineRunner seedCategories(CategoryRepository categoryRepository) {
        return args -> {
            List<String> names = List.of("personal", "work", "school", "health", "social", "urgent");
            for (String n : names) {
                categoryRepository.findByName(n).orElseGet(() ->
                        categoryRepository.save(Category.builder()
                                .name(n)
                                .description(n + " category")
                                .build())
                );
            }
        };
    }
}
