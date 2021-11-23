package com.sofkau.academicsystembackend.usecases.program;

import com.sofkau.academicsystembackend.collections.program.Program;
import com.sofkau.academicsystembackend.models.program.ProgramDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperUtilsProgram {
    public Function<ProgramDTO, Program> mapperToProgram(){
        return programDTO -> {
            var program = new Program();
            program.setId(programDTO.getId());
            program.setName(programDTO.getName());
            program.setCourses(programDTO.getCourses());
            return program;
        };
    }

    public Function<Program, ProgramDTO> mapperEntityToProgram(){
        return program -> new ProgramDTO(
                program.getId(),
                program.getName(),
                program.getCourses()
        );
    }

}
