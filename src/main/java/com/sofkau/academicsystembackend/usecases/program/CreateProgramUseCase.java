package com.sofkau.academicsystembackend.usecases.program;


import com.sofkau.academicsystembackend.models.program.ProgramDTO;
import com.sofkau.academicsystembackend.repositories.ProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CreateProgramUseCase implements SaveProgram{
    private final ProgramRepository programRepository;
    private final MapperUtilsProgram mapperUtilsProgram;

    public CreateProgramUseCase(ProgramRepository programRepository, MapperUtilsProgram mapperUtilsProgram) {
        this.programRepository = programRepository;
        this.mapperUtilsProgram = mapperUtilsProgram;
    }

    @Override
    public Mono<ProgramDTO> apply(ProgramDTO programDTO) {
        checkIfProgramNameIsEmpty(programDTO);
        checkCategoryDuration(programDTO);

        return programRepository.save(mapperUtilsProgram.mapperToProgram().apply(programDTO))
                .map(course ->  mapperUtilsProgram.mapperEntityToProgram().apply(course));

    }

    private void checkCategoryDuration(ProgramDTO programDTO) {
        programDTO.getCourses().forEach(courseTime -> courseTime.getCategories().forEach(time -> {
            if (time.getDays() < 1) {
                throw new IllegalArgumentException("A course category must last more than one day");
            }
        }));
    }

    private void checkIfProgramNameIsEmpty(ProgramDTO programDTO) {
        if (programDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("The name cannot be empty");
        }
    }
}
