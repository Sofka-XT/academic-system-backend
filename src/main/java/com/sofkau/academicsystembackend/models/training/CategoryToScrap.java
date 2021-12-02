package com.sofkau.academicsystembackend.models.training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryToScrap {
    private String categoryId;
    private List<String> categoryURL;
    private String courseId;





}
