package com.sofkau.academicsystembackend.usecases.training;

import com.sofkau.academicsystembackend.collections.training.Training;
import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import com.sofkau.academicsystembackend.repositories.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CreateTrainigUseCase implements CreateTraining {


    private TrainingMapper trainingMapper;
    private TrainingRepository trainingRepository;

    public CreateTrainigUseCase(TrainingRepository trainingRepository, TrainingMapper trainingMapper) {
        this.trainingRepository = trainingRepository;
        this.trainingMapper = trainingMapper;
    }

    @Override
    public Mono<TrainingDTO> apply(TrainingDTO trainingDTO) {
        return trainingRepository.save(trainingMapper.mapperToTraining()
                        .apply(trainingDTO))
                .map(training -> trainingMapper.mapperEntityToTrainingDTO()
                        .apply(training));
    }
}

