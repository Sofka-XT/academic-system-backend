package com.sofkau.academicsystembackend.usecases.program;

import com.sofkau.academicsystembackend.collections.program.Time;
import com.sofkau.academicsystembackend.models.program.ProgramDTO;
import com.sofkau.academicsystembackend.repositories.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Service
@Validated
public class UpdateProgramUseCase {
    private final ProgramRepository programRepository;
    private final MapperUtilsProgram mapperUtilsProgram;

    @Autowired
    public UpdateProgramUseCase(ProgramRepository programRepository, MapperUtilsProgram mapperUtilsProgram) {
        this.programRepository = programRepository;
        this.mapperUtilsProgram = mapperUtilsProgram;
    }

    public Mono<ProgramDTO> updateProgram(ProgramDTO programDTO){
        checkNameLength(programDTO);
        return  programRepository.existsById(programDTO.getId()).flatMap(programExists->{
            return checkIfProgramExists(programDTO, programExists);
        });
    }

    private Mono<ProgramDTO> checkIfProgramExists(ProgramDTO programDTO, Boolean programExists) {

        checkProgramCourses(programDTO);


        if(programExists){
            programDTO.getCourses().forEach(courseTime -> courseTime.getCategories().forEach(time -> {
                checkTimeIsValid(time);
            }));
            return programRepository.save(mapperUtilsProgram.mapperToProgram().apply(programDTO))
                    .flatMap(value -> Mono.just(mapperUtilsProgram.mapperEntityToProgram().apply(value)));
        }
        else{
            throw new NoSuchElementException();
        }
    }

    private void checkProgramCourses(ProgramDTO programDTO) {
        if(programDTO.getCourses().isEmpty()){
            throw new IllegalArgumentException("A program must contain at least one course");
        }
    }

    private void checkTimeIsValid(Time time) {
        if (time.getDays() < 1) {
            throw new IllegalArgumentException("A course category must last at least one day");
        }
    }

    private void checkNameLength(ProgramDTO programDTO) {
        if (programDTO.getName().length() < 4 || programDTO.getName().length() > 100 ) {
            throw new IllegalArgumentException("Program name must have at least 3 characters");
        }
    }


}
