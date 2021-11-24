package com.sofkau.academicsystembackend.usecases.course;

import com.sofkau.academicsystembackend.collections.course.*;
import com.sofkau.academicsystembackend.models.course.CourseDTO;
import com.sofkau.academicsystembackend.repositories.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Set;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class CreateCourseUseCaseTest {

    CreateCourseUseCase createCourseUseCase;
    CourseRepository courseRepository;
    MapperUtilsCourse mapperUtilsCourse;


    @BeforeEach
    public void setup() {
        mapperUtilsCourse = new MapperUtilsCourse();
        courseRepository = mock(CourseRepository.class);
        createCourseUseCase = new CreateCourseUseCase(courseRepository, mapperUtilsCourse);
    }

    @Test
    @DisplayName("test para validar la creación de un curso de manera correcta")
    void createCourseSeccessTest() {
        var courseDTO = new CourseDTO("C-111", "programacion reactiva y funcional",
                Set.of(new Category("programación funcional",
                        Set.of(new Rule(Type.DANGER, "<", "40", new Feedback("no sigas así campeon", "https://www.youtube.com/watch?v=NE6pANWJGuU&list=RDXfdgwJenJKY&index=4&ab_channel=fosterthepeopleVEVO")),
                                new Rule(Type.DANGER, "<", "70", new Feedback("mejora campeon", "https://www.youtube.com/watch?v=NE6pANWJGuU&list=RDXfdgwJenJKY&index=4&ab_channel=fosterthepeopleVEVO")),
                                new Rule(Type.DANGER, "=", "100", new Feedback("sigue así campeon", "https://www.youtube.com/watch?v=NE6pANWJGuU&list=RDXfdgwJenJKY&index=4&ab_channel=fosterthepeopleVEVO"))))));
        var course = new Course();

        course.setId("C-111");
        course.setName("programacion reactiva y funcional");
        course.setCategories(Set.of(new Category("programación funcional", Set.of(
                new Rule(Type.DANGER, "<", "40", new Feedback("no sigas así campeon", "https://www.youtube.com/watch?v=NE6pANWJGuU&list=RDXfdgwJenJKY&index=4&ab_channel=fosterthepeopleVEVO")),
                new Rule(Type.WARNING, "<", "70", new Feedback("mejora campeon", "https://www.youtube.com/watch?v=NE6pANWJGuU&list=RDXfdgwJenJKY&index=4&ab_channel=fosterthepeopleVEVO")),
                new Rule(Type.SUCCESS, "=", "100", new Feedback("sigue así campeon", "https://www.youtube.com/watch?v=NE6pANWJGuU&list=RDXfdgwJenJKY&index=4&ab_channel=fosterthepeopleVEVO"))))));


        when(courseRepository.save(any())).thenReturn(Mono.just(course));

        StepVerifier.create(createCourseUseCase.apply(courseDTO))
                .expectNextMatches(
                        courseDTO1 -> {
                            assert courseDTO1.getId().equals(course.getId());
                            assert courseDTO1.getName().equals(course.getName());
                            assert courseDTO1.getCategories().equals(course.getCategories());
                            return true;
                        }).verifyComplete();

        verify(courseRepository).save(any());
    }

    @Test
    @DisplayName("test para validar la cuando falla la creación de un curso")
    void createCourseNoSeccessTest() {
        var courseDTO = new CourseDTO("C-111", "programacion reactiva y funcional",
                Set.of(new Category("programación funcional",
                        Set.of(new Rule(Type.DANGER, "<", "40", new Feedback("no sigas así campeon", "https://www.youtube.com/watch?v=NE6pANWJGuU&list=RDXfdgwJenJKY&index=4&ab_channel=fosterthepeopleVEVO")),
                                new Rule(Type.DANGER, "<", "70", new Feedback("mejora campeon", "https://www.youtube.com/watch?v=NE6pANWJGuU&list=RDXfdgwJenJKY&index=4&ab_channel=fosterthepeopleVEVO")),
                                new Rule(Type.DANGER, "=", "100", new Feedback("sigue así campeon", "https://www.youtube.com/watch?v=NE6pANWJGuU&list=RDXfdgwJenJKY&index=4&ab_channel=fosterthepeopleVEVO")),
                                new Rule(Type.DANGER, "=", "100", new Feedback("sigue así campeon", "https://www.youtube.com/watch?v=NE6pANWJGuU&list=RDXfdgwJenJKY&index=4&ab_channel=fosterthepeopleVEVO"))))));
        var course = new Course();

        course.setId("C-111");
        course.setName("programacion reactiva y funcional");
        course.setCategories(Set.of(new Category("programación funcional", Set.of(
                new Rule(Type.DANGER, "<", "40", new Feedback("no sigas así campeon", "https://www.youtube.com/watch?v=NE6pANWJGuU&list=RDXfdgwJenJKY&index=4&ab_channel=fosterthepeopleVEVO")),
                new Rule(Type.DANGER, "<", "40", new Feedback("no sigas así campeon", "https://www.youtube.com/watch?v=NE6pANWJGuU&list=RDXfdgwJenJKY&index=4&ab_channel=fosterthepeopleVEVO")),
                new Rule(Type.DANGER, "<", "70", new Feedback("mejoracampeon", "https://www.youtube.com/watch?v=NE6pANWJGuU&list=RDXfdgwJenJKY&index=4&ab_channel=fosterthepeopleVEVO")),
                new Rule(Type.DANGER, "=", "100", new Feedback("sigue así campeon", "https://www.youtube.com/watch?v=NE6pANWJGuU&list=RDXfdgwJenJKY&index=4&ab_channel=fosterthepeopleVEVO"))))));


        when(courseRepository.save(any())).thenReturn(Mono.just(course));


        StepVerifier.create(createCourseUseCase.apply(courseDTO))
                .expectErrorMatches(throwable -> throwable instanceof IllegalArgumentException &&
                throwable.getMessage().equals("Las reglas solo deben ser tres por categoria")).verify();

}

}