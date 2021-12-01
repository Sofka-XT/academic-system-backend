package com.sofkau.academicsystembackend.usecases.apprentice;

import com.sofkau.academicsystembackend.collections.apprentice.ApprenticeScore;
import com.sofkau.academicsystembackend.models.apprentice.ApprenticeScoreDTO;
import com.sofkau.academicsystembackend.models.apprentice.ScoreDTO;
import com.sofkau.academicsystembackend.models.program.ProgramDTO;
import com.sofkau.academicsystembackend.repositories.ApprenticeScoreRepository;
import com.sofkau.academicsystembackend.repositories.ProgramRepository;
import com.sofkau.academicsystembackend.usecases.program.MapperUtilsProgram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UpdateApprenticeScoreUseCase {
    private final ApprenticeScoreRepository apprenticeScoreRepository;
    private final MapperUtilsApprenticeScore mapperUtilsApprenticeScore;

    @Autowired
    public UpdateApprenticeScoreUseCase(ApprenticeScoreRepository apprenticeScoreRepository, MapperUtilsApprenticeScore mapperUtilsApprenticeScore) {
        this.apprenticeScoreRepository = apprenticeScoreRepository;
        this.mapperUtilsApprenticeScore = mapperUtilsApprenticeScore;
    }


    public Mono<ApprenticeScoreDTO> updateApprentice(ScoreDTO scoreDTO){


        var apprenticeToUpdate = apprenticeScoreRepository.findById(scoreDTO.getEmail());
        apprenticeToUpdate.subscribe(
                value ->{
                    value.getCourseScores().forEach(courses -> {
                        if (courses.getCourseId() == scoreDTO.getCourseId()) {
                            courses.getCategoryScoreList().forEach(categories->{
                                if (categories.getCategoryId() == scoreDTO.getCategoryId()) {
                                    categories.setScore(scoreDTO.getScore());
                                }
                            });
                        }
                    });
                    ApprenticeScore apprenticeScore = value;
                    apprenticeScoreRepository.save(apprenticeScore).subscribe();
                }
        );
        return null;
    }
}
