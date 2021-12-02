package com.sofkau.academicsystembackend.usecases.scrapping;

import com.sofkau.academicsystembackend.collections.training.Apprentice;
import com.sofkau.academicsystembackend.collections.training.Coach;
import com.sofkau.academicsystembackend.models.scrap.ScrapDTO;
import com.sofkau.academicsystembackend.models.training.CategoryToScrap;
import com.sofkau.academicsystembackend.models.training.TrainingDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest

class CreateScrapDtoFromTrainingUseCaseTest {

  CreateScrapDtoFromTrainingUseCase createScrapDtoFromTrainingUseCase= new CreateScrapDtoFromTrainingUseCase();

  @Test
  void testWithValidArguments(){

    var categorysToScrap = new HashMap<String, List<CategoryToScrap>>();
    var category =
    categorysToScrap.put("2021-12-01", List.of(
            CategoryToScrap.builder().categoryId("xxxx-1111").courseId("xxxx-2222-2021-12-1").categoryURL(List.of("url1111","url2222")).build(),
            CategoryToScrap.builder().categoryId("xxxx-3333").courseId("xxxx-4444-2021-12-1").categoryURL(List.of("url3333","url4444")).build(),
            CategoryToScrap.builder().categoryId("xxxx-5555").courseId("xxxx-6666-2021-12-1").categoryURL(List.of("url5555","url66666")).build()
    ) );
    categorysToScrap.put("2021-12-04", List.of(
            CategoryToScrap.builder().categoryId("xxxx-1111").courseId("xxxx-2222-2021-12-4").categoryURL(List.of("url1111","url2222")).build(),
            CategoryToScrap.builder().categoryId("xxxx-3333").courseId("xxxx-4444-2021-12-4").categoryURL(List.of("url3333","url4444")).build()

    ) );
    categorysToScrap.put("2021-12-05", List.of(
            CategoryToScrap.builder().categoryId("xxxx-1111").courseId("xxxx-2222-2021-12-5").categoryURL(List.of("url1111","url2222")).build(),
            CategoryToScrap.builder().categoryId("xxxx-3333").courseId("xxxx-4444-2021-12-5").categoryURL(List.of("url3333","url4444")).build(),
            CategoryToScrap.builder().categoryId("xxxx-5555").courseId("xxxx-6666-2021-12-5").categoryURL(List.of("url5555","url66666")).build()
    ) );

    ArrayList<Apprentice> listOfApprentices1 = new ArrayList<Apprentice>();
    listOfApprentices1.add(new Apprentice("001", "Carlos Galvis", "44332211", "algo@gmail.com"));
    listOfApprentices1.add(new Apprentice("002", "Juan Gomez", "11223344", "otracosa@gmail.com"));
    listOfApprentices1.add(new Apprentice("003", "Ruiz Sebas", "22334455", "maluco@gmail.com"));
    listOfApprentices1.add(new Apprentice("004", "Marcela Mor", "33445566", "el-bejuco@gmail.com"));

    ArrayList<Coach> listOfCoaches = new ArrayList<Coach>();
    listOfCoaches.add(new Coach("01", "Oscar Peñalosa", "44332211", "hello@gmail.com"));
    listOfCoaches.add(new Coach("02", "Raul Galvis", "11223344", "its-me@gmail.com"));
    TrainingDTO trainingDTO1 = TrainingDTO.builder()
            .trainingId("61a6a479d920d5595c93c9fd")
            .name("DESARROLLO")
            .programId("61a12cb1fb9597627509645c")
            .startingDate(LocalDate.of(2022, 11, 3))
            .apprentices(listOfApprentices1)
            .categoriesToScraps(categorysToScrap)
            .coaches(listOfCoaches).build();

    var response =createScrapDtoFromTrainingUseCase.apply(trainingDTO1,LocalDate.of(2021,12,4));
    var result = List.of(
            ScrapDTO.builder().studentsEmails(List.of( "algo@gmail.com",
                    "otracosa@gmail.com",
                    "maluco@gmail.com",
                    "el-bejuco@gmail.com")).categoriesToScraps(CategoryToScrap.builder().categoryId("xxxx-1111").courseId("xxxx-2222-2021-12-4").categoryURL(List.of("url1111","url2222")).build()).build(),
            ScrapDTO.builder().studentsEmails(List.of( "algo@gmail.com",
                    "otracosa@gmail.com",
                    "maluco@gmail.com",
                    "el-bejuco@gmail.com")).categoriesToScraps(CategoryToScrap.builder().categoryId("xxxx-3333").courseId("xxxx-4444-2021-12-4").categoryURL(List.of("url3333","url4444")).build()).build()


    );
    assertEquals(result , response);
  }
}