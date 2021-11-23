package com.sofkau.academicsystembackend.usecases;

import com.sofkau.academicsystembackend.collections.training.Training;
import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TrainingMapper {

    public Function<TrainingDTO, Training> mapperToTraining() {
        return  (TrainingDTO trainingDTO) -> {
            var training = new Training();

            training.setTrainingId(trainingDTO.getTrainingId());
            training.setName(trainingDTO.getName());
            training.setProgram(trainingDTO.getProgram());
            training.setStartingDate(trainingDTO.getStartingDate());
            training.setApprentices(trainingDTO.getApprentices());

            return training;
        };
    }

    public Function<Training, TrainingDTO> mapperEntityToTrainingDTO() {
        return  (Training training) -> {
            var trainingDTO = new TrainingDTO();

            trainingDTO.setTrainingId(training.getTrainingId());
            trainingDTO.setName(training.getName());
            trainingDTO.setProgram(training.getProgram());
            trainingDTO.setStartingDate(training.getStartingDate());
            trainingDTO.setApprentices(training.getApprentices());

            return trainingDTO;
        };
    }

}
