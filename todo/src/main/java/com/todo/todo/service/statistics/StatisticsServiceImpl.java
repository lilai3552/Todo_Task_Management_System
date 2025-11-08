package com.todo.todo.service.statistics;

import com.todo.todo.model.Task;
import com.todo.todo.repository.TaskRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class StatisticsServiceImpl implements StatisticsService {
    private final TaskRepository taskRepo;
    public Map<String,Object> overview(){
        var all = taskRepo.findAll();
        long total = all.size(), done = all.stream().filter(Task::isCompleted).count();
        Map<Long,Long> byCat = new LinkedHashMap<>();
        for(Object[] r: taskRepo.countByCategory()){ byCat.put((Long)r[0], (Long)r[1]); }
        return Map.of("total", total, "completed", done, "pending", total-done,
                "completionRate", total==0?0.0:(done*1.0/total), "byCategory", byCat);
    }
}
