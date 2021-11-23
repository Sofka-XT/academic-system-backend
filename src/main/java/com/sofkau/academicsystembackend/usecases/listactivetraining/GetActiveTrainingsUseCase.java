package com.sofkau.academicsystembackend.usecases.listactivetraining;


import com.sofkau.academicsystembackend.collections.training.Training;
import com.sofkau.academicsystembackend.repositories.TrainingRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Supplier;

@Service
@Validated
public class GetActiveTrainingsUseCase implements Supplier<Flux<Training>> {

    Calendar cal = Calendar.getInstance();

    private final TrainingRepository trainingRepository;
    private final MapperUtilsActiveTraining mapperUtilsActiveTraining;

    public GetActiveTrainingsUseCase(TrainingRepository trainingRepository, MapperUtilsActiveTraining mapperUtilsActiveTraining) {
        this.trainingRepository = trainingRepository;
        this.mapperUtilsActiveTraining = mapperUtilsActiveTraining;
    }

    @Override
    public Flux<Training> get() {
        return trainingRepository.findAll().filter(
                training -> {
                    cal.setTime(training.getProgram().getStartingDate());
                    cal.add(Calendar.DATE, training.getProgram().getCourses().stream().map(
                            (courseTime) -> courseTime.getTotalTime()
                    ).reduce(0, Integer::sum));
                    return new Date().before(
                            cal.getTime());
                }
        );
    }
}
