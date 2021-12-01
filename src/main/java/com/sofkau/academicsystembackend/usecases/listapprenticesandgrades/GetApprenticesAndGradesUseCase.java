package com.sofkau.academicsystembackend.usecases.listapprenticesandgrades;

import com.sofkau.academicsystembackend.models.apprentice.ApprenticeScoreDTO;
import com.sofkau.academicsystembackend.repositories.ApprenticeScoreRepository;
import com.sofkau.academicsystembackend.usecases.apprentice.MapperUtilsApprenticeScore;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;


@Service
@Validated
public class GetApprenticesAndGradesUseCase implements Function<String, Mono<ApprenticeScoreDTO>> {

    private final ApprenticeScoreRepository apprenticeScoreRepository;
    private final MapperUtilsApprenticeScore mapperUtilsApprenticeScore;

    public GetApprenticesAndGradesUseCase(ApprenticeScoreRepository apprenticeScoreRepository, MapperUtilsApprenticeScore mapperUtilsApprenticeScore) {
        this.apprenticeScoreRepository = apprenticeScoreRepository;
        this.mapperUtilsApprenticeScore = mapperUtilsApprenticeScore;
    }

    @Override
    public Mono<ApprenticeScoreDTO> apply(String email) {
        System.out.println("caso de uso");
        return apprenticeScoreRepository.findByEmail(email).map(mapperUtilsApprenticeScore.mapperEntityToApprenticeScoreDTO());
    }
}

