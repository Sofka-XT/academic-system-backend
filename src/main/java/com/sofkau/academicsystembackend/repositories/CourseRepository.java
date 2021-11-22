package com.sofkau.academicsystembackend.repositories;

import com.sofkau.academicsystembackend.collections.course.Course;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends ReactiveMongoRepository<Course, String> {
}
