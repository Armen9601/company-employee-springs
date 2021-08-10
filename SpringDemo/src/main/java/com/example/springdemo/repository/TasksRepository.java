package com.example.springdemo.repository;

import com.example.springdemo.entyty.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Task, Integer> {



}
