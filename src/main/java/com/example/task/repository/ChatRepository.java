package com.example.task.repository;

import com.example.task.entity.chat.Chat;
import com.example.task.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ChatRepository extends JpaRepository<Chat,Integer> {
    Optional<Chat> findByName(String name);


//    List<Chat> findAllByUsers
    @Query(" FROM Chat c where :users member c.users order by c.created_at desc")
    List<Chat> findAllByUserId(User users);

}
