package com.sofkau.academicsystembackend.usecases.course;

import com.sofkau.academicsystembackend.collections.course.*;
import com.sofkau.academicsystembackend.models.course.CourseDTO;
import com.sofkau.academicsystembackend.repositories.CourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
class GetCoursesByNameUseCaseTest {

    @SpyBean
    GetCoursesByNameUseCase getCoursesByNameUseCase;

    @MockBean
    CourseRepository courseRepository;

    @Test
    @DisplayName("test para validar el listar un curso por id de manera correcta")
    void getCoursesByNameUseCaseTest() {

        ArrayList<Category> categories = new ArrayList<>();
        ArrayList<Rule> rules = new ArrayList<>();
        ArrayList<String> url = new ArrayList<>(Arrays.asList("https://www.youtube.com/watch?v=NE6pANWJGuU&list=RDXfdgwJenJKY&index=4&ab_channel=fosterthepeopleVEVO"));

        rules.add(new Rule(Type.DANGER,"<","25",new Feedback("feedbackName","url")));

        categories.add(new Category( "testCategorie1", rules, url));
        categories.add(new Category( "testCategorie2", rules, url));

        var courseDTO = new CourseDTO("1", "testCourse1", categories);
        var course = new Course();

        course.setId(courseDTO.getId());
        course.setName(courseDTO.getName());
        course.setCategories(courseDTO.getCategories());

        Mockito.when(courseRepository.findAll()).thenReturn(Flux.just(course));

        var result = getCoursesByNameUseCase.apply("testCourse1");

        Assertions.assertEquals(result.count().block(), 1L);

    }

}