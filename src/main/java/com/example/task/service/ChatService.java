package com.example.task.service;

import com.example.task.dtos.request.ChatDTO;
import com.example.task.entity.chat.Chat;
import com.example.task.entity.user.User;
import com.example.task.exception.BadRequestException;
import com.example.task.exception.NotFoundException;
import com.example.task.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final UserService userService;

    private final ChatRepository chatRepository;

    public ChatDTO save(ChatDTO dto) {

        Chat byName = getByName(dto.getName());
        if (byName != null) {
            throw new NotFoundException("Chat name all ready exists!");
        }
        checkDuplicate(dto);
        Chat chat = new Chat();
        chat.setName(dto.getName());
        chat.setCreated_at(LocalDateTime.now());
        List<User> users = dto.getUsers()
                .stream()
                .map(userService::get)
                .toList();
        chat.setUsers(users);
        Chat save = chatRepository.save(chat);
        dto.setId(save.getId());
        return dto;
    }

    public Chat getByName(final String name) {
        return chatRepository.findByName(name)
                .orElse(null);
    }

    public List<Chat> getChatsByAuthorId(final Integer id) {
        return chatRepository.findAllByUserId(userService.get(id));
    }

    public Chat getById(Integer chatId) {
        return chatRepository.findById(chatId).orElseThrow(() -> {
            throw new NotFoundException("Chat not found!");
        });
    }

    public void checkDuplicate(Object o) {
        ChatDTO chatDTO = (ChatDTO) o;
        int size = chatDTO.getUsers().size();

        if (size > 1) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i != j && chatDTO.getUsers().get(i) == chatDTO.getUsers().get(j)) {
                        throw new BadRequestException("duplicate detected");
                    }
                }
            }
        } else
            throw new BadRequestException("chat must have al least two users ");
    }
}
