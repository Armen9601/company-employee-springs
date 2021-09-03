package com.example.companyemployeespring.service;

import com.example.companyemployeespring.entity.Topic;

import java.util.List;

public interface TopicService {

    List<Topic> findAll();

    void deleteById(int id);

    void save(Topic topic);

    List<Topic> findByEmployee_Company_Id(int id);

    Topic getById(int id);

    void deleteById(int id, int currentUserId);


}
