package com.sofkau.academicsystembackend.models.scrap;

import com.sofkau.academicsystembackend.models.training.CategoryToScrap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScrapDTO {
    private List<String> studentsEmails;
    private CategoryToScrap categoriesToScraps;

}
