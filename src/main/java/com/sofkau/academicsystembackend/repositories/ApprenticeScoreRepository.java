package com.sofkau.academicsystembackend.repositories;

import com.sofkau.academicsystembackend.collections.apprentice.ApprenticeScore;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ApprenticeScoreRepository extends ReactiveMongoRepository <ApprenticeScore,String> {



}
