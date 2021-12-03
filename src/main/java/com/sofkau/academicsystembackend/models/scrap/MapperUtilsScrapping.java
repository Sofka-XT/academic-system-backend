package com.sofkau.academicsystembackend.models.scrap;

import com.sofkau.academicsystembackend.collections.training.Apprentice;
import org.springframework.beans.BeanUtils;

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
