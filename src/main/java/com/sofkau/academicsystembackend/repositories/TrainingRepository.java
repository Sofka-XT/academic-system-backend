package com.sofkau.academicsystembackend.repositories;


import com.sofkau.academicsystembackend.collections.training.Training;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends ReactiveCrudRepository<Training, String> {
}
