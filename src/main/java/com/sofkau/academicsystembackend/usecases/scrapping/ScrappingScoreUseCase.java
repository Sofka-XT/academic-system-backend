package com.sofkau.academicsystembackend.usecases.scrapping;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.scheduler.Schedulers;

@Component
public class ScrappingScoreUseCase {

    @Autowired
    private ScrapingScoreFromTrainings getTrainingUseCase;


    @Scheduled(cron = "*/10 * * * * *")

    public void apply(){
        getTrainingUseCase.apply().subscribeOn(Schedulers.parallel()).subscribe();
    }





}
