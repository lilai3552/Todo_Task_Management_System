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
@Transactional(readOnly = true)
public class StatisticsServiceImpl implements StatisticsService {

    private final TaskRepository taskRepo;

    @Override
    public Map<String, Object> overview() {
        var all = taskRepo.findAll();
        long total = all.size();
        long done  = all.stream().filter(Task::isCompleted).count();

        // 用“分类名 → 数量”
        Map<String, Long> byCat = new LinkedHashMap<>();
        for (Object[] r : taskRepo.countByCategoryName()) {
            String name = (String) r[0];   // 分类名（可能为 '—'）
            Long   cnt  = (Long)   r[1];
            byCat.put(name, cnt);
        }

        return Map.of(
                "total", total,
                "completed", done,
                "pending", total - done,
                "completionRate", total == 0 ? 0.0 : (done * 1.0 / total),
                "byCategory", byCat
        );
    }
}
