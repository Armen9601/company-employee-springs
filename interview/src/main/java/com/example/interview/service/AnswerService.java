package com.example.interview.service;

import com.example.interview.entity.Answer;
import com.example.interview.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public void saveAnswer(Answer answer){
        answerRepository.save(answer);
    }


}
