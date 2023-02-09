package com.example.task.entity.message;

import com.example.task.entity.chat.Chat;
import com.example.task.entity.user.User;
import com.example.task.enums.MessageType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "content_type")
    private MessageType contentType;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "author_id")
    private Integer authorId;
    @JoinColumn(name = "author_id", updatable = false, insertable = false)
    @ManyToOne
    private User author;


//    media


    @Column(name = "chat_id")
    private Integer chatId;
    @JoinColumn(name = "chat_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Chat chat;

    private String extension ;


}
