package com.example.DB_start.projectDto;

import com.example.DB_start.taskDto.TaskResponseDto;

import java.time.LocalDate;
import java.util.List;

//dto для ответа
public record ProjectResponseDto(
        Long id,
        String name,
        String description,
        LocalDate createdAt,
        LocalDate updatedAt,
        Boolean completed,
        List<TaskResponseDto> tasks
) {
}
