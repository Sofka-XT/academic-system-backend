package com.sofkau.academicsystembackend.usecases.scrapping;

import com.sofkau.academicsystembackend.extraction.ExtractScoreUseCase;
import com.sofkau.academicsystembackend.extraction.Score;
import com.sofkau.academicsystembackend.extraction.SeleniumProcessLogin;
import com.sofkau.academicsystembackend.models.scrap.scrapDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.function.Function;


@Service
public class ScoreExtactorUseCase implements Function<scrapDTO, Mono<List<Score>>> {

    SeleniumProcessLogin seleniumProcessLogin = new SeleniumProcessLogin();
    private final ExtractScoreUseCase extractScoreUseCase;
    private final UpdateGradesApprenticeUseCase updateGradesApprenticeUseCase;

    public ScoreExtactorUseCase(UpdateGradesApprenticeUseCase updateGradesApprenticeUseCase) {
        this.updateGradesApprenticeUseCase = updateGradesApprenticeUseCase;
        this.extractScoreUseCase = new ExtractScoreUseCase(seleniumProcessLogin);
    }

    @Override
    public Mono<List<Score>> apply(scrapDTO scrapDto) {
        var grades = extractScoreUseCase.apply(scrapDto.getCategoriesToScraps().get(0).getCategoryURL().get(0), scrapDto.getStudentsEmails());
        grades.forEach(score -> {
            updateGradesApprenticeUseCase.apply( Map.of("email", score.getName(), "courseId", scrapDto.getCategoriesToScraps().get(0).getCourseId(), "categoryId", scrapDto.getCategoriesToScraps().get(0).getCategoryId(), "score", score.getGrade()));
        });
        return Mono.just(grades);
    }
}
