package com.example.task.dtos.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttachDTO {

    private String id;
    private String originalName;
    private String path;
    private Long size;
    private String extension;
    private LocalDateTime createdData;

    private String url;

    @Override
    public String toString() {
        return "AttachDTO{" +
                "id='" + id + '\'' +
                ", originalName='" + originalName + '\'' +
                ", path='" + path + '\'' +
                ", size=" + size +
                ", extension='" + extension + '\'' +
                ", createdData=" + createdData +
                ", url='" + url + '\'' +
                '}';
    }

}
