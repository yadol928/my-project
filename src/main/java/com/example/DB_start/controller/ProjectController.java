package com.example.DB_start.controller;

import com.example.DB_start.projectDto.ProjectRequestDto;
import com.example.DB_start.projectDto.ProjectResponseDto;
import com.example.DB_start.projectDto.ProjectUpdateDto;
import com.example.DB_start.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService service;

    //Сохранение проекта
    @PostMapping
    public ResponseEntity<ProjectResponseDto> save(@Valid @RequestBody ProjectRequestDto dto){
        ProjectResponseDto saved = service.save(dto);
        return ResponseEntity.created(URI.create("/projects/" + saved.id())).body(saved);
    }

    //Поиск всех проектов
    @GetMapping
    public ResponseEntity<List<ProjectResponseDto>> findAll(){
        List<ProjectResponseDto> projects = service.findAll();
        return ResponseEntity.ok(projects);
    }

    //Постраничный поиск всех проектов
    @GetMapping("/page")
    public ResponseEntity<Page<ProjectResponseDto>> findAllWithPagination(@RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "5") int size){
        Page<ProjectResponseDto> projects = service.findAllWithPagination(page, size);
        return ResponseEntity.ok(projects);
    }

    //Поиск по дате изменения
    @GetMapping("/updated")
    public ResponseEntity<List<ProjectResponseDto>> findByUpdatedAt(@RequestParam LocalDate updatedAt){
        List<ProjectResponseDto> projects = service.findByUpdatedAt(updatedAt);
        return ResponseEntity.ok(projects);
    }

    //Поиск по id
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> findById(@PathVariable Long id){
        ProjectResponseDto project = service.findById(id);
        return ResponseEntity.ok(project);
    }

    //Поиск по статусу
    @GetMapping("/completed")
    public ResponseEntity<List<ProjectResponseDto>> findByCompleted(@RequestParam(defaultValue = "false") Boolean completed){
        List<ProjectResponseDto> projects = service.findByCompleted(completed);
        return ResponseEntity.ok(projects);
    }

    //Поиск по описанию или названию
    @GetMapping("/search")
    public ResponseEntity<List<ProjectResponseDto>> findByKeyword(@RequestParam String keyword){
        List<ProjectResponseDto> projects = service.findByKeyword(keyword);
        return ResponseEntity.ok(projects);
    }

    //Поиск по дате создания
    @GetMapping("/created")
    public ResponseEntity<List<ProjectResponseDto>> findByCreatedAt(@RequestParam LocalDate createdAt){
        List<ProjectResponseDto> projects = service.findByCreatedAt(createdAt);
        return ResponseEntity.ok(projects);
    }

    //Частичное обновление проекта
    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> update(@PathVariable Long id,
                                                            @RequestBody ProjectUpdateDto project){
        ProjectResponseDto dto = service.update(id, project);

        return ResponseEntity.ok(dto);
    }

    //Удаление проекта
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
