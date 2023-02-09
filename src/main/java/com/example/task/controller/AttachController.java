package com.example.task.controller;

import com.example.task.dtos.request.AttachDTO;
import com.example.task.service.AttachService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/attach")
@RequiredArgsConstructor
public class AttachController {
    private final AttachService attachService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {

        AttachDTO attachDTO = attachService.saveToSystem(file);
        return ResponseEntity.ok().body(attachDTO);
    }

    @GetMapping(value = "/open/{fileName}", produces = MediaType.ALL_VALUE)
    public byte[] open_general(@PathVariable("fileName") String fileName) {
        return attachService.openGeneral(fileName);
    }

}
