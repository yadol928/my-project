package com.example.DB_start.taskDto;

import com.example.DB_start.model.enums.TaskStatus;

//dto для запроса на обновление
public record TaskUpdateDto(
        String title,
        String description,
        TaskStatus status
) {


}
