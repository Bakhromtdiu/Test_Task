package com.example.task.service;

import com.example.task.dtos.request.MessageDTO;
import com.example.task.dtos.responce.MessageResDTO;
import com.example.task.entity.attach.Attach;
import com.example.task.entity.chat.Chat;
import com.example.task.entity.message.Message;
import com.example.task.entity.user.User;
import com.example.task.enums.MessageType;
import com.example.task.exception.AccessDeniedException;
import com.example.task.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository repository;
    public static final String messageHash = "messageHash";
    private final ChatService chatService;
    private final UserService userService;

    private final RedisTemplate<String, Object> template;

    private final AttachService attachService;

    public Integer save(MessageDTO dto) {

        Chat chat = chatService.getById(dto.getChatId());
        User user = userService.get(dto.getAuthorId());
        if (!chat.getUsers().contains(user)) {
            throw new AccessDeniedException("You are not allowed this chat!");
        }
        Message message = new Message();
        //"chat": "<CHAT_ID>"
        message.setChatId(dto.getChatId());
        //"author": "<USER_ID>"
        message.setAuthorId(dto.getAuthorId());

        message.setContentType(dto.getType());

        if (dto.getType().equals(MessageType.IMAGE)) {
            attachService.getById(dto.getContent());
            message.setContent(dto.getContent());
            message.setExtension(dto.getExt());
        } else {
            message.setContent(dto.getContent());
        }
        message.setCreatedAt(LocalDateTime.now());
        Message save = repository.save(message);
        template.opsForHash().put(messageHash, message.getId(), message);
        return save.getId();
    }

    public List<MessageResDTO> get(Integer chatId) {
        Chat chat = chatService.getById(chatId);

        List<Message> allByChat_id = repository
                .findAllByChat_idOrderByCreatedAtDesc(chatId);
        return allByChat_id.stream().map(m -> toDto(m, chat)).toList();
    }

    public MessageResDTO toDto(Message message, Chat chat) {
        MessageResDTO messageDTO = new MessageResDTO();
        if (message.getContentType().equals(MessageType.IMAGE)) {
            Attach image = attachService.getById(message.getContent());
            messageDTO.setContent(image.getAttachOpenUrl());
        } else {
            messageDTO.setContent(message.getContent());
        }
        messageDTO.setType(message.getContentType());
        messageDTO.setAuthor(message.getAuthor());
        messageDTO.setChat(chat);
        return messageDTO;

    }

//    public List<Message> getMessagesByChatId(final Integer id) {
//        return repository.findAllByChat_Id(id);
//    }
}
