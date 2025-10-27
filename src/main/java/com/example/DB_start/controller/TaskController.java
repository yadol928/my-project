package com.example.DB_start.controller;

import com.example.DB_start.model.Task;
import com.example.DB_start.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService service;

    @GetMapping
    public List<Task> findAllTasks(){
        return service.findAllTasks();
    }

    @PostMapping
    public String saveTask(@RequestBody Task task){
        service.saveTask(task);
        return "Задача успешно добавлена!";
    }

    @GetMapping("/{projectId}")
    public List<Task> findByProjectId(@PathVariable Long projectId){
        return service.findByProjectId(projectId);
    }

    @PutMapping
    public String updateTask(@RequestBody Task task){
        if(service.updateTask(task))
            return "Задача успешно обновлена!";
        else
            return "Задача с данным id не найдена!";
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id){
        if(service.deleteTask(id))
            return "Задача успешно удалена!";
        else
            return "Задача с данным id не найдена!";
    }
}
