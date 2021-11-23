package com.sofkau.academicsystembackend.usecases.listactivetraining;


import com.sofkau.academicsystembackend.collections.training.Training;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class GetActiveTrainingsUseCase implements Supplier<Flux<Training>> {

    private

    @Override
    public Flux<Training> get() {
        return null;
    }
}
