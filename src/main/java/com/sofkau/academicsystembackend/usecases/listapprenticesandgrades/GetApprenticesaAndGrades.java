package com.sofkau.academicsystembackend.usecases.listapprenticesandgrades;

import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import com.sofkau.academicsystembackend.usecases.listactivetraining.GetActiveTrainingsUseCase;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;
import java.util.function.Supplier;

@Service
@Validated
public class GetApprenticesaAndGrades implements Function<String, Mono<Object>> {

    private final GetActiveTrainingsUseCase getActiveTrainingsUseCase;

    public GetApprenticesaAndGrades(GetActiveTrainingsUseCase getActiveTrainingsUseCase) {
        this.getActiveTrainingsUseCase = getActiveTrainingsUseCase;
    }


    @Override
    public Mono<Object> apply(String email) {
        return getActiveTrainingsUseCase.get().filter(trainingDTO -> trainingDTO.getTrainingId().equals("q")).elementAt(0).map(TrainingDTO::getApprentices);
    }
}

