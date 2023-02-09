package com.example.task.dtos.request;

import com.example.task.enums.MessageType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDTO {
    //--data '{"chat": "<CHAT_ID>", "author": "<USER_ID>", "type": "text", "content": "hi"}'
    private MessageType type; // photo
    private String content;   // photoId
    private Integer authorId;
    private Integer chatId;
    private String ext;

}
