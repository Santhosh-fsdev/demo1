package com.example.demo.repository;

import com.example.demo.model.Data;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface DataRepository extends MongoRepository<Data, String>{
    public Optional<Data> findById(String id);
    
}
