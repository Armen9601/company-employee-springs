package com.example.springdemo.controller;


import com.example.springdemo.entyty.Task;
import com.example.springdemo.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TaskController {
    @Autowired
    private TasksRepository tasksRepository;
    @GetMapping("/tasks")
    public String tasks() {

        List<Task> all = tasksRepository.findAll();


        return "tasks";
    }
}
