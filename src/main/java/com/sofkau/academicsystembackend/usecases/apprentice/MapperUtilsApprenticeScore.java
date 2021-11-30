package com.sofkau.academicsystembackend.usecases.apprentice;

import com.sofkau.academicsystembackend.collections.apprentice.ApprenticeScore;
import com.sofkau.academicsystembackend.collections.program.Program;
import com.sofkau.academicsystembackend.models.apprentice.ApprenticeScoreDTO;
import com.sofkau.academicsystembackend.models.program.ProgramDTO;

import java.util.function.Function;

public class MapperUtilsApprentice {
    public Function<ApprenticeScoreDTO, ApprenticeScore> mapperToApprenticeScore(){
        return apprenticeScoreDTO -> {
            var apprentice = new ApprenticeScore();
            apprentice.setApprenticeName(apprenticeScoreDTO.getApprenticeName());
            apprentice.setEmail(apprenticeScoreDTO.getEmail());
            apprentice.setPhoneNumber(apprentice.getPhoneNumber());
            apprentice.setTrainingId(apprenticeScoreDTO.getTrainingId());
            apprentice.setCourseScores(apprentice.getCourseScores());
            return apprentice;
        };
    }

    public Function<ApprenticeScore, ApprenticeScoreDTO> mapperEntityToApprenticeScoreDTO(){
        return apprentice -> new ApprenticeScoreDTO(

        );
    }
}
