package com.sofkau.academicsystembackend.usecases.training;

import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import com.sofkau.academicsystembackend.repositories.TrainingRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;
import java.util.function.Supplier;

@Service
@Validated
public class GetAllTrainingUseCase implements Supplier<Flux<TrainingDTO>> {

    private TrainingMapper trainingMapper;
    private TrainingRepository trainingRepository;

    public GetAllTrainingUseCase(TrainingMapper trainingMapper, TrainingRepository trainingRepository) {
        this.trainingMapper = trainingMapper;
        this.trainingRepository = trainingRepository;
    }


    @Override
    public Flux<TrainingDTO> get() {
        return trainingRepository.findAll()
                .map(training -> trainingMapper.mapperEntityToTrainingDTO()
                        .apply(training));
    }
}
