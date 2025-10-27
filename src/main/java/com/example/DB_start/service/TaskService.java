package com.example.DB_start.service;

import com.example.DB_start.model.Task;
import com.example.DB_start.repository.TasksRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private final TasksRepository repository;

    public List<Task> findAllTasks(){
        return repository.findAll();
    }

    public void saveTask(Task task){
        task.setCreatedAt(LocalDate.now());
        repository.save(task);
    }

    public List<Task> findByProjectId(Long projectId){
        List<Task> tasks = repository.findByProjectId(projectId);

        return tasks.isEmpty() ?
                new ArrayList<>(List.of(new Task("Задачи не найдены!"))) :
                tasks;
    }

    public boolean updateTask(Task task){
        if (repository.findById(task.getId()).isPresent()) {
            repository.save(task);
            return true;
        }
        else
            return false;
    }

    public boolean deleteTask(Long id){

        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
            return true;
        }
        else
            return false;
    }
}
