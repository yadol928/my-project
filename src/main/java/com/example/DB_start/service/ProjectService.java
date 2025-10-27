package com.example.DB_start.service;

import com.example.DB_start.model.Project;
import com.example.DB_start.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjectService {

    private final ProjectRepository repository;

    public void saveProject(Project project){
        project.setCreatedAt(LocalDate.now());
        repository.save(project);
    }

    public List<Project> findAllProjects(){
        return repository.findAll();
    }

    public Project findById(Long id){
        if(id <= 0)
            return new Project("Проект не найден. Id меньше нуля!");
        return repository.findById(id).
                orElse(new Project("Проект с таким id не найден"));
    }

    public boolean updateProject(Project project){
        if(repository.findById(project.getId()).isPresent()){
            repository.save(project);
            return true;
        }
        else
            return false;
    }

    public boolean deleteProject(Long id) {

        if (repository.findById(id).isEmpty())
            return false;
        else {
            repository.deleteById(id);
            return true;
        }

    }
}
