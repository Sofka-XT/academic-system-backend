package com.sofkau.academicsystembackend.usecases.training;

import com.sofkau.academicsystembackend.repositories.TrainingRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class DeleteTrainingUseCase implements Function<String, Mono<Void>>{
private TrainingRepository trainingRepository;

public DeleteTrainingUseCase(TrainingRepository trainingRepository){
    this.trainingRepository = trainingRepository;
}

    @Override
    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id, "El id es requerido");
        return trainingRepository.deleteById(id);
    }
}
