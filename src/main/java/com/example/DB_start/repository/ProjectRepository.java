package com.example.DB_start.repository;

import com.example.DB_start.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    // Поиск проектов, у которых название или описание содержит заданную подстроку (без учета регистра)
    List<Project> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);

    //Поиск по статусу
    List<Project> findByCompleted(boolean completed);

    //Поиск по дате создания
    List<Project> findByCreatedAt(LocalDate createdAt);

    //Поиск по дате обновления
    List<Project> findByUpdatedAt(LocalDate updatedAt);

}
