package com.example.QuoraReactiveApp.Repository;

import com.example.QuoraReactiveApp.Model.Like;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends ReactiveMongoRepository<Like, String> {
    // Additional query methods can be defined here if needed
}
