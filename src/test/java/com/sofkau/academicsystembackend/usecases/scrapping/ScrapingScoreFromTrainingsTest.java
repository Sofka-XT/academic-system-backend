package com.sofkau.academicsystembackend.usecases.scrapping;

import com.sofkau.academicsystembackend.collections.training.Apprentice;
import com.sofkau.academicsystembackend.collections.training.Coach;
import com.sofkau.academicsystembackend.models.scrap.ScrapDTO;
import com.sofkau.academicsystembackend.models.training.CategoryToScrap;
import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;


@ExtendWith(MockitoExtension.class)
class ScrapingScoreFromTrainingsTest {
    @InjectMocks
    ScrapingScoreFromTrainings scrapingScoreFromTrainings;

    @Mock
    WebClient client;
    @Mock
    ScoreExtactorUseCase scoreExtactorUseCase;
    @Mock
    GetTrainingUseCase getTrainingUseCase;
    @Mock
    CreateScrapDtoFromTrainingUseCase createScrapDtoFromTrainingUseCase;

    @Test
    void scrapingScoreFromTrainingsSuccess() {
        //region dtoTraining
        Coach coach1 = new Coach();
        coach1.setId("12iwefya0p4ep984");
        coach1.setPhoneNumber("3153982789");
        coach1.setEmailAddress("raul@gmail.com");
        coach1.setName("Raul Alzate");

        Coach coach2 = new Coach();
        coach2.setId("34roq8wreyq49");
        coach2.setPhoneNumber("3215896254");
        coach2.setEmailAddress("oscar@gmail.com");
        coach2.setName("Oscar Lopera");

        List<Coach> coaches = new ArrayList<>();
        coaches.add(coach1);
        coaches.add(coach2);

        Apprentice apprentice1 = new Apprentice();
        apprentice1.setId("123sdgfhe6");
        apprentice1.setPhoneNumber("3164589875");
        apprentice1.setEmailAddress("Santi@gmail.com");
        apprentice1.setName("Santiago Posada");

        Apprentice apprentice2 = new Apprentice();
        apprentice2.setId("123sfgse6y6");
        apprentice2.setPhoneNumber("3175896254");
        apprentice2.setEmailAddress("Daniela@gmail.com");
        apprentice2.setName("Daniela Vargas");

        List<Apprentice> apprentices = new ArrayList<>();
        apprentices.add(apprentice1);
        apprentices.add(apprentice2);

        TrainingDTO trainingDTO = new TrainingDTO();
        trainingDTO.setTrainingId("31sdasd3546sa8asd");
        trainingDTO.setName("Training de desarrollo");
        trainingDTO.setProgram("123ffser43");
        trainingDTO.setStartingDate(LocalDate.now());
        trainingDTO.setCoaches(coaches);
        trainingDTO.setApprentices(apprentices);
        //endregion

        //region scrapDTO
        ArrayList<String> emails = new ArrayList<>();
        emails.add("juanfth2001@gmail.com");
        ArrayList<String> uris = new ArrayList<>();
        uris.add("reports/listtestusers/id:2121,type:Test,group:,branch:,completion_status:");
        var scrap = new CategoryToScrap("c-111", uris, "cu-111", 20);
        var dto = new ScrapDTO(emails, scrap);
        //endregion

        Mockito.when(getTrainingUseCase.apply()).thenReturn(Flux.just(trainingDTO));
        Mockito.when(createScrapDtoFromTrainingUseCase.apply(any(), any())).thenReturn(Flux.just(dto));
        Mockito.when(scoreExtactorUseCase.apply(refEq(dto))).thenReturn(Flux.fromIterable(List.of("juanfth2001@gmail.com")));

        StepVerifier.create(scrapingScoreFromTrainings.apply()).expectNext("juanfth2001@gmail.com").verifyComplete();

    }
}
