package com.sofkau.academicsystembackend;

import com.sofkau.academicsystembackend.collections.program.CourseTime;
import com.sofkau.academicsystembackend.collections.program.Program;
import com.sofkau.academicsystembackend.collections.program.Time;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SchedulerHelperTest {



    @Test
    public void determinarCalendario(){
        var startDate = LocalDate.of(2022, 1, 1);
        var endDate = new AtomicReference<>(LocalDate.from(startDate));
        final AtomicInteger[] pivot = {new AtomicInteger()};
        final int[] index = {0};

        var program = new Program();
        program.setCourses(new ArrayList<>());
        var timesForCourse1 = new ArrayList<Time>();
        timesForCourse1.add(new Time("1", 2, "Principios", List.of()));
        timesForCourse1.add(new Time("2", 2, "Bases", List.of()));
        timesForCourse1.add(new Time("3", 4, "Fundamentos", List.of()));
        timesForCourse1.add(new Time("3", 5, "Fundamentos avazandos", List.of()));

        program.getCourses().add(new CourseTime("xxx-z", "Introducción", timesForCourse1));


        var scheduler = getDurationOf(program).map(category -> {
            var increment = endDate.get().getDayOfWeek().getValue() > 5 ? 8 - endDate.get().getDayOfWeek().getValue() : 0;
            pivot[0].set(pivot[0].get() + increment);
            endDate.set(LocalDate.from(endDate.get().plusDays(1 + increment)));
            var result =  startDate.plusDays(index[0] + pivot[0].get());
            index[0]++;
            return result;
        });

        scheduler.forEach(System.out::println);

    }

    private Stream<String> getDurationOf(Program program){
      return  program.getCourses().stream()
              .flatMap(courseTime -> courseTime.getCategories().stream())
              .flatMap(time -> IntStream.range(0, time.getDays()).mapToObj((i) -> time.getCategoryName()));
    }



}
