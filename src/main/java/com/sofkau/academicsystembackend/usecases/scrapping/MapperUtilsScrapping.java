package com.sofkau.academicsystembackend.usecases.scrapping;

import com.sofkau.academicsystembackend.collections.training.Apprentice;
import com.sofkau.academicsystembackend.collections.training.Training;
import com.sofkau.academicsystembackend.models.scrap.ApprenticeDTO;
import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import org.springframework.beans.BeanUtils;

import java.beans.JavaBean;
import java.util.function.Function;

public class MapperUtilsScrapping {

    public Function<Apprentice, ApprenticeDTO> mapperToApprenticeDTO(){
        return apprentice -> {
           ApprenticeDTO apprenticeDTO = new ApprenticeDTO();
            BeanUtils.copyProperties(apprentice,apprenticeDTO);
            return apprenticeDTO;
        };
    }

    public Function<ApprenticeDTO, Apprentice> mapperToApprentice(){
        return apprenticeDTO -> {
            Apprentice apprentice = new Apprentice();
            BeanUtils.copyProperties(apprenticeDTO,apprentice);
            return apprentice;
        };
    }
}
