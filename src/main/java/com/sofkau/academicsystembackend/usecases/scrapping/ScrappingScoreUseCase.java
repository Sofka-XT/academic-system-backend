package com.sofkau.academicsystembackend.usecases.scrapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScrappingScoreUseCase {

    @Autowired
    private GetTrainingUseCase getTrainingUseCase;

    @Scheduled(cron = "*/10 * * * * *")
    public void apply(){
        getTrainingUseCase.getAll().subscribe();
//        System.out.println("Hello");

    }





}
