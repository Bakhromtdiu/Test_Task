package com.example.task.dtos.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatDTO {
    //--data '{"chat": "<CHAT_ID>"}' \
    private Integer id;

    private String name;

    private List<Integer> users;

}
