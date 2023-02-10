package com.example.task.service;

import com.example.task.dtos.request.UserDTO;
import com.example.task.entity.user.User;
import com.example.task.exception.NotFoundException;
import com.example.task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    public static final String profileHash = "profileHash";
    private final UserRepository repository;
    private final RedisTemplate<String, Object> template;


    public Integer save(UserDTO dto) {

        User byUsername = getByUsername(dto.getUsername());

        if (byUsername != null) {
            throw new NotFoundException("Username already exists!");
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setCreated_at(LocalDateTime.now());
        User save = repository.save(user);
        template.opsForHash().put(profileHash, user.getId(), user);
        return save.getId();
    }

    public User getR(final Integer id) {
        User user = (User) template.opsForHash().get(profileHash, id);
        return user;
    }

    public User get(final Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException("User not found!");
                });
    }

        public User getByUsername ( final String username){
            return repository.findByUsername(username)
                    .orElse(null);
        }
    }
