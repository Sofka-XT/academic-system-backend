package com.sofkau.academicsystembackend.usecases.scrapping;


import com.sofkau.academicsystembackend.models.scrap.scrapDTO;
import com.sofkau.academicsystembackend.models.training.CategoryToScrap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class ScoreExtactorUseCaseTest {
    ScoreExtactorUseCase scoreExtactorUseCase;
    @BeforeEach
    void setUp() {
        scoreExtactorUseCase = new ScoreExtactorUseCase();
    }
    @Test
    @DisplayName("Test para validar la extracción de notas del campus")
    void extractScoreUseCaseSuccess() {
        ArrayList<String> emails = new ArrayList<>();
        emails.add("ktns0930@gmail.com");
        emails.add("juanfth2001@gmail.com");
        emails.add("141013@unsaac.edu.pe");
        emails.add("sebasruigalle62@gmail.com");
        emails.add("yamsoncalapzu@gmail.com");
        ArrayList<String> uris = new ArrayList<>();
        uris.add("reports/listtestusers/id:2121,type:Test,group:,branch:,completion_status:");
        ArrayList<CategoryToScrap> listCatToSCrap = new ArrayList<>();
        listCatToSCrap.add(new CategoryToScrap("c-111",uris,"cu-111"));
        var dto = new scrapDTO(emails, listCatToSCrap);
        scoreExtactorUseCase.apply(dto).subscribe(
                result -> {
                    result.forEach(System.out::println);
                });

    }

}