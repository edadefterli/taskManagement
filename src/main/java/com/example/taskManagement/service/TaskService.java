package com.example.taskManagement.service;

import com.example.taskManagement.model.Task;
import com.example.taskManagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task addTask(Task task){
        return taskRepository.save(task);
    }

    public Task updateTitle(Task task){
        if(!taskRepository.existsById(task.getId())){
            throw new RuntimeException("Task not found.");
        }
        return taskRepository.save(task);
    }
    public Task updateStartDate(Task task){
        if(!taskRepository.existsById(task.getId())){
            throw new RuntimeException("Task not found.");
        }
        return taskRepository.save(task);
    }
    public Task updateEndDate(Task task){
        if(!taskRepository.existsById(task.getId())){
            throw new RuntimeException("Task not found.");
        }
        return taskRepository.save(task);
    }
    public Task updateUserName(Task task){
        if(!taskRepository.existsById(task.getId())){
            throw new RuntimeException("Task not found.");
        }
        return taskRepository.save(task);
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public List<Task> getTasksByUsername(String userName){
        return taskRepository.findByUserName(userName);
    }

    public Optional<Task> getTaskById(Long id){
        return taskRepository.findById(id);
    }
    public Optional<Task> getTaskByTitle(String title){
        return taskRepository.findByTitle(title);
    }


}
