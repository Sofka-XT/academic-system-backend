package com.sofkau.academicsystembackend.usecases.scrapping;


import com.sofkau.academicsystembackend.collections.apprentice.ApprenticeScore;
import com.sofkau.academicsystembackend.collections.apprentice.CategoryScore;
import com.sofkau.academicsystembackend.collections.apprentice.CourseScore;
import com.sofkau.academicsystembackend.models.apprentice.ApprenticeScoreDTO;
import com.sofkau.academicsystembackend.models.apprentice.ScoreDTO;
import com.sofkau.academicsystembackend.models.scrap.ApprenticeDTO;
import com.sofkau.academicsystembackend.models.scrap.ScrapDTO;
import com.sofkau.academicsystembackend.models.training.CategoryToScrap;
import com.sofkau.academicsystembackend.repositories.ApprenticeScoreRepository;
import com.sofkau.academicsystembackend.usecases.apprentice.UpdateApprenticeScoreUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
class ScoreExtactorUseCaseTest {
    ScoreExtactorUseCase scoreExtactorUseCase;
    ApprenticeScoreRepository apprenticeScoreRepository;
    UpdateApprenticeScoreUseCase updateApprenticeScoreUseCase;

    @BeforeEach
    void setUp() {
        apprenticeScoreRepository = Mockito.mock(ApprenticeScoreRepository.class);
        updateApprenticeScoreUseCase = new UpdateApprenticeScoreUseCase(apprenticeScoreRepository);
        scoreExtactorUseCase = new ScoreExtactorUseCase(updateApprenticeScoreUseCase);
    }

    @Test
    @DisplayName("Test para validar la extracción de notas del campus")
    void extractScoreUseCaseSuccess() {
        ArrayList<String> emails = new ArrayList<>();
        emails.add("juanfth2001@gmail.com");
        ArrayList<String> uris = new ArrayList<>();
        uris.add("reports/listtestusers/id:2121,type:Test,group:,branch:,completion_status:");
        var scrap = new CategoryToScrap("c-111", uris, "cu-111");
        var dto = new ScrapDTO(emails, scrap);
        var apprentice = new ApprenticeScore("juanfth2001@gmail.com", "Hola", "ccccc", "2323232",
                List.of(new CourseScore("vvv", "zzzz", List.of(new CategoryScore("122", "1222", 85)))));
       var dtoApprentice = new ScoreDTO("juanfth2001@gmail.com", "C-111","Ca-111", 85);

        Mockito.when(apprenticeScoreRepository.findById("juanfth2001@gmail.com")).thenReturn(Mono.just(apprentice));
        Mockito.when(updateApprenticeScoreUseCase.updateApprentice(dtoApprentice)).thenReturn(Mono.just("juanfth2001@gmail.com"));

        scoreExtactorUseCase.apply(dto).subscribe(
                result -> {
                    result.forEach(System.out::println);
                });

    }

}