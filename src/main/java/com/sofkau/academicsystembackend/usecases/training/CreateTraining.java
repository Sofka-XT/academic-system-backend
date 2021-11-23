package com.sofkau.academicsystembackend.usecases.training;

import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface CreateTraining {
    public Mono<TrainingDTO> apply(@Valid TrainingDTO trainingDTO);
}
