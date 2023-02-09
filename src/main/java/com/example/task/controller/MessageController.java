package com.example.task.controller;

import com.example.task.dtos.request.MessageDTO;
import com.example.task.dtos.responce.MessageResDTO;
import com.example.task.dtos.responce.ResponseChatDto;
import com.example.task.entity.message.Message;
import com.example.task.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService service;

    @PostMapping("/add")
    public HttpEntity<?> create(@RequestBody MessageDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }


    @PostMapping("/get")
    public HttpEntity<List<MessageResDTO>> get(@RequestBody ResponseChatDto dto) {
        return ResponseEntity.ok(service.get(dto.getChatId()));
    }
}
