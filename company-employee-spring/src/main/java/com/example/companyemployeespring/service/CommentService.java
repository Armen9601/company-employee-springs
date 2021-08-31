package com.example.companyemployeespring.service;

import com.example.companyemployeespring.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAll();

    void deleteById(int id);

    void save(Comment comment);
    List<Comment> findByTopicId(int id);

//    List<Message> findByFromIdAndToIdOrToIdAndFromId(int fromId, int toId);
}
