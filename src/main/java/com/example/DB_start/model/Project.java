package com.example.DB_start.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "projects")
@RequiredArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    private String description;

    private LocalDate createdAt;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Task> tasks = new ArrayList<>();

}
