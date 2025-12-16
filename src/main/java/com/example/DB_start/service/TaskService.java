package com.example.DB_start.service;

import com.example.DB_start.exception.ResourceNotFoundException;
import com.example.DB_start.model.Project;
import com.example.DB_start.model.Task;
import com.example.DB_start.model.enums.TaskStatus;
import com.example.DB_start.repository.ProjectRepository;
import com.example.DB_start.repository.TaskRepository;
import com.example.DB_start.taskDto.TaskMapper;
import com.example.DB_start.taskDto.TaskRequestDto;
import com.example.DB_start.taskDto.TaskResponseDto;
import com.example.DB_start.taskDto.TaskUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository tasksRepository;
    private final ProjectRepository projectRepository;

    //Поиск всех задач
    @Transactional(readOnly = true)
    public List<TaskResponseDto> findAll(){
        List<Task> tasks = tasksRepository.findAll();
        return TaskMapper.toDtoList(tasks);
    }

    //Поиск задачи по id
    @Transactional(readOnly = true)
    public TaskResponseDto findById(Long id){
        Task task = tasksRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Задача с id " + id + " не найдена!"));
        return TaskMapper.toDto(task);
    }

    //Сохранение задачи
    @Transactional
    public TaskResponseDto save(TaskRequestDto task, Long projectId){

        Project project = projectRepository.findById(projectId).
                orElseThrow(() -> new ResourceNotFoundException("Проект с id " + projectId + " не найден!"));
        Task entity = TaskMapper.toEntity(task);
        entity.setProject(project);
        tasksRepository.save(entity);
        return TaskMapper.toDto(entity);
    }

    //Поиск задачи по id его проекта
    @Transactional(readOnly = true)
    public List<TaskResponseDto> findByProjectId(Long projectId){
        List<Task> tasks = tasksRepository.findByProjectId(projectId);
        return TaskMapper.toDtoList(tasks);
    }

    //Поиск задачи по дате создания
    @Transactional(readOnly = true)
    public List<TaskResponseDto> findByCreatedAt(LocalDate createdAt){
        List<Task> tasks = tasksRepository.findByCreatedAt(createdAt);
        return TaskMapper.toDtoList(tasks);
    }

    //Поиск задачи по дате изменения
    @Transactional(readOnly = true)
    public List<TaskResponseDto> findByUpdatedAt(LocalDate updatedAt){
        List<Task> tasks = tasksRepository.findByUpdatedAt(updatedAt);
        return TaskMapper.toDtoList(tasks);
    }

    //Частичное обновление задачи
    @Transactional
    public TaskResponseDto update(TaskUpdateDto task, Long id){

        Task entity = tasksRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Задачи с id " + id + " не найдено!"));

        String title = task.title();
        if(title != null && !title.isBlank())
            entity.setTitle(title);

        String description = task.description();
        if(description != null && !description.isBlank())
            entity.setDescription(description);

        TaskStatus status = task.status();
        if(status != null)
            entity.setStatus(status);

        entity.setUpdatedAt(LocalDate.now());

        tasksRepository.save(entity);

        return TaskMapper.toDto(entity);

    }

    //Удаление задачи
    @Transactional
    public void delete(Long id){
        tasksRepository.findById(id)
                .ifPresentOrElse(tasksRepository::delete, () -> {
                    throw new ResourceNotFoundException("Задачи с id " + id + " не найдено!");
                });
    }

    //Поиск задачи по статусу
    @Transactional(readOnly = true)
    public List<TaskResponseDto> findByStatus(String status) {
        TaskStatus taskStatus;
        try{
            taskStatus = TaskStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Некорректный статус: " + status +
                    " Доступные статусы: " + Arrays.toString(TaskStatus.values()));
        }
        List<Task> tasks = tasksRepository.findByStatus(taskStatus);
        return TaskMapper.toDtoList(tasks);
    }

    //Поиск задачи по названию или описанию
    @Transactional(readOnly = true)
    public List<TaskResponseDto> findByKeyword(String keyword){
        List<Task> tasks = tasksRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
        return TaskMapper.toDtoList(tasks);
    }

    //Постраничный поиск всех задач
    @Transactional(readOnly = true)
    public Page<TaskResponseDto> findWithPagination(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Task> tasks = tasksRepository.findAll(pageable);
        return TaskMapper.toDtoPage(tasks);
    }

}
