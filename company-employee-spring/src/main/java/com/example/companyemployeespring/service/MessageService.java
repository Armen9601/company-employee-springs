package com.example.companyemployeespring.service;

import com.example.companyemployeespring.entity.Message;

import java.util.List;

public interface MessageService {

    List<Message> findAll();

    void deleteById(int id);

    void save(Message message);

    List<Message> findByFromIdAndToIdOrToIdAndFromId(int fromId, int toId);
}
