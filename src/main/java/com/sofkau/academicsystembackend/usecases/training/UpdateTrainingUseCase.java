package com.sofkau.academicsystembackend.usecases.training;

import com.mongodb.Block;
import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import com.sofkau.academicsystembackend.repositories.TrainingRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;


@Service
@Validated
public class UpdateTrainingUseCase implements Function<TrainingDTO, Mono<TrainingDTO>> {

    private TrainingMapper mapper;
    private TrainingRepository repository;

    public UpdateTrainingUseCase(TrainingMapper mapper, TrainingRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public Mono<TrainingDTO> apply(TrainingDTO trainingDTO) {
        Objects.requireNonNull(trainingDTO);
        return repository
                .save(mapper.mapperToTraining().apply(trainingDTO))
                .map(training -> mapper.mapperEntityToTrainingDTO().apply(training));
    }
}
