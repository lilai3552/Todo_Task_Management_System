package com.todo.todo.controller;

import org.springframework.ui.Model;
import com.todo.todo.service.category.CategoryService;
import com.todo.todo.service.todo.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// controller/ViewController.java
@Controller
@RequiredArgsConstructor
public class ViewController {
    private final TodoService todoService;
    private final CategoryService categoryService;

    @GetMapping({"", "/", "/home"})
    public String home(Model model) {
        model.addAttribute("tasks", todoService.listAll());
        model.addAttribute("categories", categoryService.list());
        return "home"; // 对应 templates/home.html
    }
}




