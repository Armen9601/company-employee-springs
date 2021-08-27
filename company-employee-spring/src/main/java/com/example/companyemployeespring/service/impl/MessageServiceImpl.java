package com.example.companyemployeespring.service.impl;

import com.example.companyemployeespring.entity.Message;
import com.example.companyemployeespring.repository.MessageRepository;
import com.example.companyemployeespring.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        messageRepository.deleteById(id);
    }

    @Override
    public void save(Message message) {
        if (message != null) {
            messageRepository.save(message);
        }
    }

    @Override
    public List<Message> findByFromIdAndToIdOrToIdAndFromId(int fromId, int toId) {
        return messageRepository.findByFromIdAndToIdOrToIdAndFromId(fromId, toId);
    }
}
