package com.example.interview.repository;

import com.example.interview.entity.ClientAnswer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<ClientAnswer, Integer> {

    List <ClientAnswer> getByUserId(int userId);


}
