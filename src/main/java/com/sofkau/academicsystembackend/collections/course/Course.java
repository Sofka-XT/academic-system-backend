package com.sofkau.academicsystembackend.collections.course;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document
public class Course {
    @Id
    private Integer id;

}
