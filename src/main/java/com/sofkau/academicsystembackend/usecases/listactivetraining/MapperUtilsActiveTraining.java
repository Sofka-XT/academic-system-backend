package com.sofkau.academicsystembackend.usecases.listactivetraining;

import com.sofkau.academicsystembackend.collections.training.Training;
import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperUtilsActiveTraining {

    public Function<TrainingDTO, Training>mapperToTraining(){
        return trainingDTO -> {
            var training = new Training();
            training.setTrainingId(trainingDTO.getTrainingId());
            training.setName(trainingDTO.getName());
            training.setProgram(trainingDTO.getProgram());
            training.setStartingDate(trainingDTO.getStartingDate());
            training.setApprentices(trainingDTO.getApprentices());
            training.setCoaches(trainingDTO.getCoaches());
            return training;
        };
    }
    public Function<Training, TrainingDTO> mapperEntityToTraining() {
        return training -> new TrainingDTO(
                training.getTrainingId(),
                training.getName(),
                training.getProgram(),
                training.getStartingDate(),
                training.getApprentices(),
                training.getCoaches(),
                null
        );
    }
}


