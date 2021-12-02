package com.sofkau.academicsystembackend.usecases.scrapping;

import com.sofkau.academicsystembackend.extraction.ExtractScoreUseCase;
import com.sofkau.academicsystembackend.extraction.Score;
import com.sofkau.academicsystembackend.extraction.SeleniumProcessLogin;
import com.sofkau.academicsystembackend.models.apprentice.ScoreDTO;
import com.sofkau.academicsystembackend.models.scrap.ScrapDTO;
import com.sofkau.academicsystembackend.usecases.apprentice.UpdateApprenticeScoreUseCase;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;


@Service
public class ScoreExtactorUseCase implements Function<ScrapDTO, Mono<List<Score>>> {

    SeleniumProcessLogin seleniumProcessLogin = new SeleniumProcessLogin();
    private final ExtractScoreUseCase extractScoreUseCase;
    private final UpdateApprenticeScoreUseCase updateGradesApprenticeUseCase;

    public ScoreExtactorUseCase(UpdateApprenticeScoreUseCase updateApprenticeScoreUseCase) {
        this.updateGradesApprenticeUseCase = updateApprenticeScoreUseCase;
        this.extractScoreUseCase = new ExtractScoreUseCase(seleniumProcessLogin);
    }


    @Override
    public Mono<List<Score>> apply(ScrapDTO scrapDTO) {
        var grades = extractScoreUseCase.apply(scrapDTO.getCategoriesToScraps().getCategoryURL().get(0), scrapDTO.getStudentsEmails());
        System.out.println("grades.size() = " + grades.size());
        grades.forEach(score -> {
            System.out.println("score.getName() = " + score.getName());
            System.out.println("score.getGrade() = " + score.getGrade());
            updateGradesApprenticeUseCase.updateApprentice(
                    new ScoreDTO(score.getName(), scrapDTO.getCategoriesToScraps().getCourseId(), scrapDTO.getCategoriesToScraps().getCategoryId(), Integer.valueOf((int) Double.parseDouble(score.getGrade().replace("%", "")))));
        });
        return Mono.just(grades);
    }
}
