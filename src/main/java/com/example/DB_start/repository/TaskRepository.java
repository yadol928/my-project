package com.example.DB_start.repository;

import com.example.DB_start.model.Task;
import com.example.DB_start.model.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    //Поиск по id проекта
    List<Task> findByProjectId(Long ProjectId);

    //Поиск по статусу
    List<Task> findByStatus(TaskStatus status);

    //Поиск задач, которые в названии или описании содержат заданную подстроку (без учета регистра)
    List<Task> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);

    //Поиск по дате создания
    List<Task> findByCreatedAt(LocalDate createdAt);

    //Поиск по дате обновления
    List<Task> findByUpdatedAt(LocalDate updatedAt);
}
