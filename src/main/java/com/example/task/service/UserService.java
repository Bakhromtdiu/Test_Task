package com.example.task.service;

import com.example.task.dtos.request.UserDTO;
import com.example.task.entity.user.User;
import com.example.task.exception.NotFoundException;
import com.example.task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public Integer save(UserDTO dto) {

        User byUsername = getByUsername(dto.getUsername());

        if (byUsername != null) {
            throw new NotFoundException("Username already exists!");
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setCreated_at(LocalDateTime.now());
        User save = repository.save(user);
        return save.getId();
    }

    public User get(final Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException("User not found!");
                });
    }

    public User getByUsername(final String username) {
        return repository.findByUsername(username)
                .orElse(null);
    }
}
