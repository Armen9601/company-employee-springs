package com.example.companyemployeespring.service.impl;

import com.example.companyemployeespring.entity.Comment;
import com.example.companyemployeespring.entity.Message;
import com.example.companyemployeespring.repository.CommentRepository;
import com.example.companyemployeespring.repository.CompanyRepository;
import com.example.companyemployeespring.repository.MessageRepository;
import com.example.companyemployeespring.service.CommentService;
import com.example.companyemployeespring.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> findByTopicId(int id) {
        return commentRepository.findByTopicId(id);
    }
}
