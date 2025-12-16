package com.example.DB_start.controller;

import com.example.DB_start.model.enums.TaskStatus;
import com.example.DB_start.service.TaskService;
import com.example.DB_start.taskDto.TaskRequestDto;
import com.example.DB_start.taskDto.TaskResponseDto;
import com.example.DB_start.taskDto.TaskUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    //Поиск всех задач
    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> findAll(){
        List<TaskResponseDto> tasks = service.findAll();
        return ResponseEntity.ok(tasks);
    }

    //Поиск задачи по id
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> findById(@PathVariable Long id){
        TaskResponseDto task = service.findById(id);
        return ResponseEntity.ok(task);
    }

    //Сохранение задачи
    @PostMapping
    public ResponseEntity<TaskResponseDto> save(@Valid @RequestBody TaskRequestDto task,
                                                @RequestParam Long projectId){
        TaskResponseDto saved = service.save(task, projectId);
        return ResponseEntity.created(URI.create("/tasks/" + saved.id())).body(saved);
    }

    //Поиск задач по проекту
    @GetMapping("/project")
    public ResponseEntity<List<TaskResponseDto>> findByProjectId(@RequestParam Long projectId){
        List<TaskResponseDto> tasks = service.findByProjectId(projectId);
        return ResponseEntity.ok(tasks);
    }

    //Поиск по дате создания
    @GetMapping("/created")
    public ResponseEntity<List<TaskResponseDto>> findByCreatedAt(@RequestParam LocalDate createdAt){
        List<TaskResponseDto> tasks = service.findByCreatedAt(createdAt);
        return ResponseEntity.ok(tasks);
    }

    //Поиск по дате изменения
    @GetMapping("/updated")
    public ResponseEntity<List<TaskResponseDto>> findByUpdatedAt(@RequestParam LocalDate updatedAt){
        List<TaskResponseDto> tasks = service.findByUpdatedAt(updatedAt);
        return ResponseEntity.ok(tasks);
    }

    //Частичное обновление задачи
    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponseDto> update(@RequestBody TaskUpdateDto task,
                                                  @PathVariable Long id){
        TaskResponseDto updated = service.update(task, id);
        return ResponseEntity.ok(updated);
    }

    //Удаление задачи
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    //Поиск задачи по статусу
    @GetMapping("/status")
    public ResponseEntity<List<TaskResponseDto>> findByStatus(@RequestParam String status){
        List<TaskResponseDto> tasks = service.findByStatus(status);
        return ResponseEntity.ok(tasks);
    }

    //Поиск задачи по описанию или названию
    @GetMapping("/keyword")
    public ResponseEntity<List<TaskResponseDto>> findByKeyword(@RequestParam String keyword){
        List<TaskResponseDto> tasks = service.findByKeyword(keyword);
        return ResponseEntity.ok(tasks);
    }

    //Постраничный поиск задач
    @GetMapping("/page")
    public ResponseEntity<Page<TaskResponseDto>> findWithPagination(@RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "5") int size
    ){
        Page<TaskResponseDto> tasks = service.findWithPagination(page, size);
        return ResponseEntity.ok(tasks);
    }
}
