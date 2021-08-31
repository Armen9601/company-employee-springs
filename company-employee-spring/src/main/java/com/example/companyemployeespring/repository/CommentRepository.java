package com.example.companyemployeespring.repository;


import com.example.companyemployeespring.entity.Comment;
import com.example.companyemployeespring.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByTopicId(int id);
}
