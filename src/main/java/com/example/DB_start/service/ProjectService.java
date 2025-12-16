package com.example.DB_start.service;

import com.example.DB_start.exception.ResourceNotFoundException;
import com.example.DB_start.model.Project;
import com.example.DB_start.projectDto.ProjectMapper;
import com.example.DB_start.projectDto.ProjectRequestDto;
import com.example.DB_start.projectDto.ProjectResponseDto;
import com.example.DB_start.projectDto.ProjectUpdateDto;
import com.example.DB_start.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository repository;

    //Сохранение проекта
    @Transactional
    public ProjectResponseDto save(ProjectRequestDto dto) {
        Project project = ProjectMapper.toEntity(dto);
        repository.save(project);
        return ProjectMapper.toDto(project);
    }

    //Поиск всех проектов
    @Transactional(readOnly = true)
    public List<ProjectResponseDto> findAll(){
        List<Project> projects = repository.findAll();
        return ProjectMapper.toDtoList(projects);
    }

    //Постраничный поиск всех проектов
    @Transactional(readOnly = true)
    public Page<ProjectResponseDto> findAllWithPagination(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Project> projects =  repository.findAll(pageable);
        return ProjectMapper.toDtoPage(projects);
    }

    //Поиск проекта по id
    @Transactional(readOnly = true)
    public ProjectResponseDto findById(Long id){
        Project project = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Проект с данным id не найден!"));
        return ProjectMapper.toDto(project);
    }

    //Поиск проекта по статусу
    @Transactional(readOnly = true)
    public List<ProjectResponseDto> findByCompleted(Boolean completed){
        List<Project> projects = repository.findByCompleted(completed);
        return ProjectMapper.toDtoList(projects);
    }

    //Поиск проекта по описанию или названию
    @Transactional(readOnly = true)
    public List<ProjectResponseDto> findByKeyword(String keyword){
        List<Project> projects = repository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
        return ProjectMapper.toDtoList(projects);
    }

    //Поиск по дате создания
    @Transactional(readOnly = true)
    public List<ProjectResponseDto> findByCreatedAt(LocalDate createdAt){
        List<Project> projects = repository.findByCreatedAt(createdAt);
        return ProjectMapper.toDtoList(projects);
    }

    //Поиск проекта по дате изменения
    @Transactional(readOnly = true)
    public List<ProjectResponseDto> findByUpdatedAt(LocalDate updatedAt){
        List<Project> projects = repository.findByUpdatedAt(updatedAt);
        return ProjectMapper.toDtoList(projects);
    }

    //Частичное обновление проекта
    @Transactional
    public ProjectResponseDto update(Long id, ProjectUpdateDto project){
        Project entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Проекта с id " + id + " не найдено!"));

        String name = project.name();
        if(name != null && !name.isBlank())
            entity.setName(name);

        String description = project.description();
        if(description != null && !description.isBlank())
            entity.setDescription(description);

        Boolean completed = project.completed();
        if(completed != null)
            entity.setCompleted(completed);

        entity.setUpdatedAt(LocalDate.now());

        repository.save(entity);

        return ProjectMapper.toDto(entity);

    }

    //Удаление проекта
    @Transactional
    public void delete(Long id) {

        repository.findById(id).ifPresentOrElse(repository::delete, () -> {
            throw new ResourceNotFoundException("Проект с id " + id + " не найден!");
        });

    }
}
