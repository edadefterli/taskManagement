package com.example.taskManagement.controller;

import com.example.taskManagement.model.Task;
import com.example.taskManagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    //tüm görevleri listeleme
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> tasks = taskService.getAllTasks();
        if (tasks.isEmpty()){
            throw new RuntimeException("Task list is empty.");
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    //ID ile görev bulma
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id){
        Optional<Task> task = taskService.getTaskById(id);
        return task.map(t -> ResponseEntity.ok(t)).orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/{title}")
    public ResponseEntity<Task> getTaskByTitle(@PathVariable String title){
        Optional<Task> task = taskService.getTaskByTitle(title);
        return task.map(t->ResponseEntity.ok(t)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("user/{userName}")
    public ResponseEntity<List<Task>> getTasksByUsername(@PathVariable String userName){
        List<Task> tasks = taskService.getTasksByUsername(userName);
        if (tasks.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tasks,HttpStatus.OK);
    }

    //görev güncelleme
    @PutMapping("/{title}")
    public ResponseEntity<Task> updateTaskTitle(@PathVariable String title, @RequestBody Task task){
        task.setTitle(title);
        Task updatedTask = taskService.updateTitle(task);
        return new ResponseEntity<>(updatedTask,HttpStatus.OK);
    }

    @PutMapping("/{startDate}")
    public ResponseEntity<Task> updateTaskStartDate(@PathVariable LocalDateTime startDate, @RequestBody Task task){
        task.setStartDate(startDate);
        Task updatedTask = taskService.updateStartDate(task);
        return new ResponseEntity<>(updatedTask,HttpStatus.OK);
    }
    @PutMapping("/{endDate}")
    public ResponseEntity<Task> updateTaskEndDate(@PathVariable LocalDateTime endDate, @RequestBody Task task){
        task.setEndDate(endDate);
        Task updatedTask = taskService.updateEndDate(task);
        return new ResponseEntity<>(updatedTask,HttpStatus.OK);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<Task> updateTaskUserName(@PathVariable String userName, @RequestBody Task task){
        task.setUserName(userName);
        Task updatedTask = taskService.updateUserName(task);
        return new ResponseEntity<>(updatedTask,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    //görev ekleme
    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task){
        Task createdTask = taskService.addTask(task);
        return new ResponseEntity<>(createdTask,HttpStatus.CREATED);
    }


}
