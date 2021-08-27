package com.example.interview.service;

import com.example.interview.entity.Question;
import com.example.interview.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Question save(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public List<Question> findById(int id){
        return questionRepository.findByInterviewId(id);    }
}
