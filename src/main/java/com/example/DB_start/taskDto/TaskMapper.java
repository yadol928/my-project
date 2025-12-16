package com.example.DB_start.taskDto;

import com.example.DB_start.model.Task;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class TaskMapper {

    //Dto -> сущность
    public static Task toEntity(TaskRequestDto dto){
        Task task = new Task();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStatus(dto.status());
        return task;
    }

    //Сущность -> dto
    public static TaskResponseDto toDto(Task task){
        return new TaskResponseDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCreatedAt(),
                task.getUpdatedAt(),
                task.getStatus()
        );
    }

    //Пагинированный список сущностей -> пагинированный список dto
    public static Page<TaskResponseDto> toDtoPage(Page<Task> tasks){
        return tasks.map(TaskMapper::toDto);
    }

    //Список сущностей -> список dto
    public static List<TaskResponseDto> toDtoList(List<Task> tasks){
        List<TaskResponseDto> dto = new ArrayList<>();
        for(Task t : tasks){
            dto.add(toDto(t));
        }
        return dto;
    }

}
