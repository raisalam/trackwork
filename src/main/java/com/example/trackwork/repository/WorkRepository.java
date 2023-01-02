package com.example.trackwork.repository;

import com.example.trackwork.model.Work;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface WorkRepository extends MongoRepository<Work, String> {
}
