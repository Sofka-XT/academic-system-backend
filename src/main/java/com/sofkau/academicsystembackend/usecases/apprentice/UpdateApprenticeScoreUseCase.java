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

import java.time.LocalDate;

@Service
@Validated
public class UpdateApprenticeScoreUseCase {
    private final ApprenticeScoreRepository apprenticeScoreRepository;

    @Autowired
    public UpdateApprenticeScoreUseCase(ApprenticeScoreRepository apprenticeScoreRepository) {
        this.apprenticeScoreRepository = apprenticeScoreRepository;
    }

    public Mono<String> updateApprentice(ScoreDTO scoreDTO){

        var apprenticeToUpdate = apprenticeScoreRepository.findById(scoreDTO.getEmail());
        apprenticeToUpdate.subscribe(
                value ->{
                    value.getCourseScores().forEach(courses -> {
                        if (courses.getCourseId().equals(scoreDTO.getCourseId()) ) {
                            courses.getCategoryScoreList().forEach(categories->{
                                if (categories.getCategoryId().equals(scoreDTO.getCategoryId()) ) {
                                    categories.setScore(scoreDTO.getScore());
                                    categories.setUpdateScoreDate(LocalDate.now());
                                }
                            });
                        }
                    });
                apprenticeScoreRepository.save(value).subscribe();
                }
        );
        return  apprenticeToUpdate.map(apprenticeScore -> apprenticeScore.getEmail());
    }
}
