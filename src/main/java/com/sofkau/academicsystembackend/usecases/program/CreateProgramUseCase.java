package com.sofkau.academicsystembackend.usecases.program;


import com.sofkau.academicsystembackend.models.program.ProgramDTO;
import com.sofkau.academicsystembackend.repositories.ProgramRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;

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

        checkIfProgramNameFormat(programDTO);
        checkDuplicateCourses(programDTO);
        checkCategoryDuration(programDTO);

        if(programDTO.getId() == null){
            return programRepository.save(mapperUtilsProgram.mapperToProgram().apply(programDTO))
                    .map(course ->  mapperUtilsProgram.mapperEntityToProgram().apply(course));
        }

        return programRepository.existsById(programDTO.getId()).flatMap(programExists ->{
            if(programExists){
                throw new NoSuchElementException("A program with the same Id already exists");
            }else{
                return programRepository.save(mapperUtilsProgram.mapperToProgram().apply(programDTO))
                        .map(course ->  mapperUtilsProgram.mapperEntityToProgram().apply(course));
            }
        });

    }


    private void checkDuplicateCourses(ProgramDTO programDTO) {
        var courseIdsList = new ArrayList<>();

        programDTO.getCourses().forEach(course ->
                courseIdsList.add(course.getCourseId()));

        var courseIdsSet = new HashSet<>(courseIdsList);

        if(courseIdsSet.size()< courseIdsList.size()){
            throw new IllegalArgumentException("Program cannot have the same course multiple times");        }
    }

    private void checkCategoryDuration(ProgramDTO programDTO) {
        programDTO.getCourses().forEach(courseTime -> courseTime.getCategories().forEach(time -> {
            if (time.getDays() < 1) {
                throw new IllegalArgumentException("A course category must last more than one day");
            }
        }));
    }


    private void checkIfProgramNameFormat(ProgramDTO programDTO) {

        if (programDTO.getName().isEmpty()  || programDTO.getName().trim().length() < 3) {
            throw new IllegalArgumentException("A program must have a name with more than 3 characters");
        }
    }

}
