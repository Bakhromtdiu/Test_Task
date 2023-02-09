package com.example.task.entity.attach;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Data
@Entity(name = "attach")
public class Attach {

    @Id
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "original_name")
    private String originalName;

    @Column(name = "path")
    private String path;

    @Column(name = "size")
    private Long size;

    @Column(name = "extension")
    private String extension;

    @Column(name = "created_date")
    private LocalDateTime createdData = LocalDateTime.now();

    @Column(name = "attachOpenUrl")
    private String attachOpenUrl;
}
