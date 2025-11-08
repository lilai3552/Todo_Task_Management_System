package com.todo.todo.controller;

import com.todo.todo.service.statistics.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// controller/StatisticsController.java
@RestController
@RequestMapping("/api/statistics") @RequiredArgsConstructor
public class StatisticsController {
    private final StatisticsService service;
    @GetMapping("/overview") public Map<String,Object> overview(){ return service.overview(); }
}

