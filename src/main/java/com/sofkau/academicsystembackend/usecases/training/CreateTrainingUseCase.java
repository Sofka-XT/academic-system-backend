package com.sofkau.academicsystembackend.usecases.training;

import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import com.sofkau.academicsystembackend.repositories.TrainingRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class CreateTrainingUseCase implements Function<TrainingDTO, Mono<TrainingDTO>> {

    private TrainingMapper trainingMapper;
    private TrainingRepository trainingRepository;
    private CreateCalendarUseCase createCalendarUseCase;

    public CreateTrainingUseCase(TrainingRepository trainingRepository, TrainingMapper trainingMapper, CreateCalendarUseCase createCalendarUseCase) {
        this.trainingRepository = trainingRepository;
        this.trainingMapper = trainingMapper;
        this.createCalendarUseCase = createCalendarUseCase;
    }

    @Override
    public Mono<TrainingDTO> apply(TrainingDTO trainingDTO) {

        createCalendarUseCase.apply(trainingDTO);
        return trainingRepository.save(trainingMapper.mapperToTraining()
                        .apply(trainingDTO))
                .map(training -> trainingMapper.mapperEntityToTrainingDTO()
                        .apply(training));
    }
}

