package com.todo.todo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Entity
@Table(name="tasks")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Task {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, length=120) private String title;
    @Column(length=500) private String detail;
    private boolean completed;
    private LocalDateTime dueAt;
    private LocalDateTime createdAt;

    @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="category_id")
    private Category category;
}

