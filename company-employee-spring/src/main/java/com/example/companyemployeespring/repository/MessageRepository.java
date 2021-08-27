package com.example.companyemployeespring.repository;


import com.example.companyemployeespring.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query("from Message m where (m.from.id =:fromId and m.to.id=:toId) or ( m.from.id=:toId and m.to.id=:fromId) ")
    List<Message> findByFromIdAndToIdOrToIdAndFromId(@Param("fromId") int fromId, @Param("toId") int toId);
}
