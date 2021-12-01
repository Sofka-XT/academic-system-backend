package com.sofkau.academicsystembackend.repositories;

import com.sofkau.academicsystembackend.collections.apprentice.ApprenticeScore;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ApprenticeScoreRepository extends ReactiveMongoRepository <ApprenticeScore,String> {
    Mono<ApprenticeScore> findByEmail(String email);
}
