package com.sofkau.academicsystembackend.usecases.apprentice;

import com.sofkau.academicsystembackend.collections.apprentice.ApprenticeScore;
import com.sofkau.academicsystembackend.models.apprentice.ApprenticeScoreDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperUtilsApprenticeScore {
    public Function<ApprenticeScoreDTO, ApprenticeScore> mapperToApprenticeScore(){
        return apprenticeScoreDTO -> {
            var apprentice = new ApprenticeScore();
            apprentice.setApprenticeName(apprenticeScoreDTO.getApprenticeName());
            apprentice.setEmail(apprenticeScoreDTO.getEmail());
            apprentice.setPhoneNumber(apprenticeScoreDTO.getPhoneNumber());
            apprentice.setTrainingId(apprenticeScoreDTO.getTrainingId());
            apprentice.setCourseScores(apprenticeScoreDTO.getCourseScores());
            return apprentice;
        };
    }

    public Function<ApprenticeScore, ApprenticeScoreDTO> mapperEntityToApprenticeScoreDTO(){
        return apprentice -> new ApprenticeScoreDTO(
            apprentice.getEmail(),
                apprentice.getApprenticeName(),
                apprentice.getTrainingId(),
                apprentice.getPhoneNumber(),
                apprentice.getCourseScores()

        );
    }
}
