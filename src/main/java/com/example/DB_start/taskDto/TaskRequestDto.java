package com.example.DB_start.taskDto;

import com.example.DB_start.model.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//dto для запроса на создание
public record TaskRequestDto(
        @NotBlank(message = "Название не может быть пустым!")
        String title,

        @NotBlank(message = "Описание не может быть пустым!")
        String description,

        @NotNull(message = "Статус обязателен!")
        TaskStatus status
) {
}
