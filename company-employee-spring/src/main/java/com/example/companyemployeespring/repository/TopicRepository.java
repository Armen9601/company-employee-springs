package com.example.companyemployeespring.repository;


import com.example.companyemployeespring.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {
    List<Topic> findByEmployee_Company_Id(int id);
    Topic getById(int id);
    void deleteById(int id);

}
