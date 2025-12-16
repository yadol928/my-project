package com.example.DB_start.projectDto;

import com.example.DB_start.model.Project;
import com.example.DB_start.taskDto.TaskMapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class ProjectMapper {

    //Dto -> сущность
    public static Project toEntity(ProjectRequestDto dto){
        Project project = new Project();
        project.setName(dto.name());
        project.setDescription(dto.description());
        project.setCompleted(dto.completed());
        return project;
    }

    //Сущность -> dto
    public static ProjectResponseDto toDto(Project project){
        return new ProjectResponseDto(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getCreatedAt(),
                project.getUpdatedAt(),
                project.getCompleted(),
                TaskMapper.toDtoList(project.getTasks())
        );
    }

    //Список сущностей -> список dto
    public static List<ProjectResponseDto> toDtoList(List<Project> projects){
        List<ProjectResponseDto> dtoList = new ArrayList<>();
        for(Project p : projects){
            dtoList.add(toDto(p));
        }
        return dtoList;
    }

    //Пагинированный список сущностей -> пагинированный список dto
    public static Page<ProjectResponseDto> toDtoPage(Page<Project> projects){
        return projects.map(ProjectMapper::toDto);
    }

}
