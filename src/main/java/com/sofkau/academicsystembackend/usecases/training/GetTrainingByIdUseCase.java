package com.sofkau.academicsystembackend.usecases.training;

import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import com.sofkau.academicsystembackend.repositories.TrainingRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;


@Service
@Validated
public class GetTrainingByIdUseCase implements Function<String, Mono<TrainingDTO>> {

    private TrainingMapper trainingMapper;
    private TrainingRepository trainingRepository;

    public GetTrainingByIdUseCase(TrainingMapper trainingMapper, TrainingRepository trainingRepository) {
        this.trainingMapper = trainingMapper;
        this.trainingRepository = trainingRepository;
    }

    @Override
    public Mono<TrainingDTO> apply(String id) {
        Objects.requireNonNull(id, "Id no puede ser nulo");
        return trainingRepository.findById(id).map(training -> trainingMapper.mapperEntityToTrainingDTO().apply(training));
    }
}
