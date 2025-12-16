package com.example.DB_start.projectDto;

//dto для запроса на обновление
public record ProjectUpdateDto(
        String name,
        String description,
        Boolean completed
) {
}
