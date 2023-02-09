package com.example.task.dtos.responce;

import com.example.task.entity.chat.Chat;
import com.example.task.entity.user.User;
import com.example.task.enums.MessageType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageResDTO {
    private MessageType type;
    private String content;
    private User author;
    private Chat chat;
    private String ext;
}
