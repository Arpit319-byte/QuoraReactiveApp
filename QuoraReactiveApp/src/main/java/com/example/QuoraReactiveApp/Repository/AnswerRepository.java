package com.example.QuoraReactiveApp.Repository;

import com.example.QuoraReactiveApp.Model.Answer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends ReactiveMongoRepository<Answer, String> {
    // Additional query methods can be defined here if needed
}
