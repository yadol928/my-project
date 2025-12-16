package com.example.DB_start.taskDto;

import com.example.DB_start.model.enums.TaskStatus;

import java.time.LocalDate;

//dto для ответа
public record TaskResponseDto(
        Long id,
        String title,
        String description,
        LocalDate createdAt,
        LocalDate updatedAt,
        TaskStatus status
) {
}
