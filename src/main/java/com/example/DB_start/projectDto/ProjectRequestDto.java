package com.example.DB_start.projectDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//dto для запроса на создание
public record ProjectRequestDto(

        @NotBlank(message = "Название не может быть пустым!")
        String name,

        @NotBlank(message = "Описание не может быть пустым!")
        String description,

        @NotNull(message = "Статус выполнения обязателен!")
        Boolean completed

) {
}
