package com.example.DB_start.controller;

import com.example.DB_start.model.Project;
import com.example.DB_start.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@AllArgsConstructor
public class ProjectController {

    private final ProjectService service;

    @PostMapping
    public String saveProject(@RequestBody Project project){
        service.saveProject(project);
        return "Проект успешно сохранен!";
    }

    @GetMapping
    public List<Project> findAllProjects(){
        return service.findAllProjects();
    }

    @GetMapping("/{id}")
    public Project findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PutMapping
    public String updateProject(@RequestBody Project project){
        if(service.updateProject(project))
            return "Проект успешно обновлен!";
        else
            return "Проекта с данным id не найдено!";
    }

    @DeleteMapping("/{id}")
    public String deleteProject(@PathVariable Long id){
        if(service.deleteProject(id))
            return "Проект успешно удален!";
        else
            return "Проект с данным id не найден!";
    }

}
