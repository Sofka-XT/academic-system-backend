package com.sofkau.academicsystembackend.usecases.training;

import com.sofkau.academicsystembackend.collections.program.Program;
import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import com.sofkau.academicsystembackend.repositories.ProgramRepository;
import com.sofkau.academicsystembackend.repositories.TrainingRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
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
        Map<String, Integer > categoryIterable = new HashMap<>();
        programRepository.findById(programId).subscribe();
        var program = programRepository.findById(programId).blockOptional().orElse(new Program("null", "null", new ArrayList<>()));

        program.getCourses().stream().map(course -> {
            course.getCategories().stream().map(category ->{
                categoryIterable.put(category.getCategoryId(), category.getDays());
                return null;
            });
            return null;
        });


        categoryIterable.forEach((key, value)->{
            System.out.printf("Clave : "+key +" Valor: "+value);
        });

        return null;
    }
}
