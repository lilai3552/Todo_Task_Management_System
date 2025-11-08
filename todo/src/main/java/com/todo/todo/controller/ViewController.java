package com.todo.todo.controller;

import com.todo.todo.model.Task;
import com.todo.todo.service.category.CategoryService;
import com.todo.todo.service.todo.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final TodoService todoService;
    private final CategoryService categoryService;

    /** 默认首页：直接进入任务页（添加任务界面） */
    @GetMapping({"/", "/tasks"})
    public String tasks(Model model, @ModelAttribute("msg") String msg) {

        // 1️⃣ 获取任务列表
        List<Task> tasks = todoService.listAll();

        // 2️⃣ 在内存中按 dueAt 升序（最早的在前，null 放最后）
        tasks.sort(Comparator.comparing(
                Task::getDueAt,
                Comparator.nullsLast(Comparator.naturalOrder())
        ));

        // 3️⃣ 放入模型
        model.addAttribute("tasks", tasks);
        model.addAttribute("categories", categoryService.list());

        if (msg != null && !msg.isBlank()) model.addAttribute("msg", msg);

        return "tasks"; // 对应 templates/tasks.html
    }

    /** 统计页 */
    @GetMapping("/stats")
    public String statsPage() {
        return "stats"; // 对应 templates/stats.html（前端用 fetch 拉 /api/statistics/overview）
    }

    /*Completed Page*/
    @GetMapping("/completed")
    public String completedTasks(Model model) {
        // 复用现有 service，只拿已完成任务
        model.addAttribute("tasks", todoService.listAll());
        model.addAttribute("categories", categoryService.list());
        return "completed"; // 对应 completed.html
    }


    /** 表单创建任务（x-www-form-urlencoded） */
    @PostMapping(path = "/ui/tasks", consumes = "application/x-www-form-urlencoded")
    public String createFromForm(@RequestParam String title,
                                 @RequestParam(required = false) String detail,
                                 @RequestParam(required = false) Long categoryId,
                                 @RequestParam(required = false, name = "dueAt") String dueAtStr,
                                 RedirectAttributes ra) {
        LocalDateTime dueAt = null;
        if (dueAtStr != null && !dueAtStr.isBlank()) {
            try {
                dueAt = LocalDateTime.parse(dueAtStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            } catch (Exception e) {
                ra.addFlashAttribute("msg", "Invalid dueAt. Please pick a valid date & time.");
                return "redirect:/tasks";
            }
        }
        todoService.create(title, detail, categoryId, dueAt);
        ra.addFlashAttribute("msg", "Task created");
        return "redirect:/tasks";
    }
}
