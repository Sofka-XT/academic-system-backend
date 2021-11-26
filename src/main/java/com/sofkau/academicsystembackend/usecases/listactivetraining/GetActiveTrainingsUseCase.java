package com.sofkau.academicsystembackend.usecases.listactivetraining;


import com.sofkau.academicsystembackend.collections.program.Time;
import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import com.sofkau.academicsystembackend.repositories.ProgramRepository;
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
public class GetActiveTrainingsUseCase implements Supplier<Flux<TrainingDTO>> {

    private final TrainingRepository trainingRepository;
    private final ProgramRepository programRepository;
    private final MapperUtilsActiveTraining mapperUtilsActiveTraining;

    public GetActiveTrainingsUseCase(TrainingRepository trainingRepository, ProgramRepository programRepository,MapperUtilsActiveTraining mapperUtilsActiveTraining) {
        this.trainingRepository = trainingRepository;
        this.programRepository = programRepository;
        this.mapperUtilsActiveTraining = mapperUtilsActiveTraining;
    }

    @Override
    public Flux<TrainingDTO> get() {
        return trainingRepository.findAll().filter(
                training -> {
                    programRepository.findById(training.getProgram()).subscribe();
                    var program = programRepository.findById(training.getProgram()).block();
                    var end_date = training.getStartingDate().plusDays(program.getCourses().stream()
                            .map(courseTime -> courseTime.getCategories().stream()
                                        .map(Time::getDays).reduce(0, Integer::sum)
                        ).reduce(0, Integer::sum));

                    return LocalDate.now().isBefore(end_date);
                }
        ).map(mapperUtilsActiveTraining.mapperEntityToTraining());
    }
}
