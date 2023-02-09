package com.example.task.repository;

import com.example.task.entity.attach.Attach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachRepository extends JpaRepository<Attach, String> {



}
