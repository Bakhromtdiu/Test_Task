package com.example.task.repository;

import com.example.task.entity.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findAllByChat_idOrderByCreatedAtDesc(Integer id);

}
