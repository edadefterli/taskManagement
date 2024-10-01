package com.example.taskManagement.repository;

import com.example.taskManagement.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAll();
    Optional<Task> findById(Long id);
    Optional<Task> findByTitle(String title);
    void deleteById(Long id);
    List<Task> findByUserName(String userName);




}
