package com.sofkau.academicsystembackend.usecases.training;

import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import com.sofkau.academicsystembackend.repositories.ProgramRepository;
import com.sofkau.academicsystembackend.repositories.TrainingRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Validated
public class CreateCalendarUseCase implements Function<Mono<TrainingDTO>, Mono<TrainingDTO>> {
    private TrainingMapper trainingMapper;
    private TrainingRepository trainingRepository;
    private ProgramRepository programRepository;

    public CreateCalendarUseCase(TrainingMapper trainingMapper, TrainingRepository trainingRepository, ProgramRepository programRepository) {
        this.trainingMapper = trainingMapper;
        this.trainingRepository = trainingRepository;
        this.programRepository = programRepository;
    }

    @Override
    public Mono<TrainingDTO> apply(Mono<TrainingDTO> trainingDTO) {
        var programId = trainingDTO.block().getProgram();
        var program = programRepository.findById(programId);
        Map<String, Integer > categoryIterable = new HashMap<>();
        program.flatMap(program1 -> {
            program1.getCourses().
        })

        /*program.getCourses().stream().forEach(course -> {
            course.getCategories().stream().forEach(category ->{
                categoryIterable.put(category.getCategoryId(), category.getDays());
            });
        });*/


        categoryIterable.forEach((key, value)->{
            System.out.printf("Clave : "+key +" Valor: "+value);
        });

        return null;
    }
}
