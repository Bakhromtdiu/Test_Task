package com.example.task.controller;

import com.example.task.dtos.request.ChatDTO;
import com.example.task.dtos.responce.UserDto;
import com.example.task.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    ChatService chatService;


    @PostMapping("/add")
    public HttpEntity<?> create(@RequestBody ChatDTO chatDTO) {

        ChatDTO save = chatService.save(chatDTO);

        return ResponseEntity.ok(save.getId());
    }

    @PostMapping("/get")
    public HttpEntity<?> get(@RequestBody UserDto dto) {

        return ResponseEntity.ok(chatService.getChatsByAuthorId(dto.getUser()));

    }

}
