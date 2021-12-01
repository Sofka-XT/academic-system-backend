package com.sofkau.academicsystembackend.usecases.scrapping;

import com.sofkau.academicsystembackend.collections.training.Apprentice;

import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class GetTrainingUseCase {
    Logger logger = LoggerFactory.getLogger(GetTrainingUseCase.class);
    @Autowired
    private WebClient client;
    private MapperUtilsScrapping mapperUtilsScrapping;



    public Flux<TrainingDTO> getAll() {
        return  client.get().uri("/training/list-actives").accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(response -> response.bodyToFlux(TrainingDTO.class)).map(traing -> {
                    // [trainingActive , trainingActive ,trainingActive ,trainingActive ]
                    var listStudents = traing.getApprentices().stream().map(Apprentice::getEmailAddress).collect(Collectors.toList());
                    // logica sacar mapa


                    logger.info(Arrays.toString(listStudents.toArray()));
                    return traing;
                });
    }

//    public Flux<ApprenticeDTO>  getStudents(){
//        return getAll().map(obj -> {
////            var dtoToMap = obj.getApprentices().stream().map(apprentice -> )
//            return obj;
//                   });
//    }

}
