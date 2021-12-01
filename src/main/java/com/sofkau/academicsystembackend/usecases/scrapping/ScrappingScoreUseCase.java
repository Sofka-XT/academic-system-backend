package com.sofkau.academicsystembackend.usecases.scrapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScrappingScoreUseCase {

    @Autowired
    private ScrapingScoreFromTrainings scrapingScoreFromTrainings;

    @Scheduled(cron = "0 30 6 ? * MON,TUE,WED,THU,FRI")
    public void apply(){
        scrapingScoreFromTrainings.apply();
    }





}
