package com.example.companyemployeespring.service.impl;

import com.example.companyemployeespring.entity.Topic;
import com.example.companyemployeespring.repository.TopicRepository;
import com.example.companyemployeespring.security.CurrentUser;
import com.example.companyemployeespring.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;

    @Override
    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        topicRepository.deleteById(id);
    }

    @Override
    public void save(Topic topic) {
        topicRepository.save(topic);
    }

    @Override
    public List<Topic> findByEmployee_Company_Id(int id) {
        return topicRepository.findByEmployee_Company_Id(id);
    }

    @Override
    public Topic getById(int id) {
        return topicRepository.getById(id);
    }

    @Override
    public void deleteById(int id, int currentUser) {
        Topic byId = topicRepository.getById(id);
        if (byId.getEmployee().getId()==currentUser){
            topicRepository.deleteById(id);
        }
    }
    //    @Override
//    public List<Message> findByFromIdAndToIdOrToIdAndFromId(int fromId, int toId) {
//        return topicRepository.findByFromIdAndToIdOrToIdAndFromId(fromId, toId);
//    }
}
