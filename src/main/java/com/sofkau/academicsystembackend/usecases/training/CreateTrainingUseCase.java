package com.sofkau.academicsystembackend.usecases.training;

import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import com.sofkau.academicsystembackend.repositories.TrainingRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class CreateTrainingUseCase implements Function<TrainingDTO, Mono<TrainingDTO>> {

    private TrainingMapper trainingMapper;
    private TrainingRepository trainingRepository;

    public CreateTrainingUseCase(TrainingRepository trainingRepository, TrainingMapper trainingMapper) {
        this.trainingRepository = trainingRepository;
        this.trainingMapper = trainingMapper;
    }

    @Override
    public Mono<TrainingDTO> apply(TrainingDTO trainingDTO) {
        return trainingRepository.save(trainingMapper.mapperToTraining()
                        .apply(trainingDTO)).map(training -> trainingMapper.mapperEntityToTrainingDTO().apply(training));
    }
}
