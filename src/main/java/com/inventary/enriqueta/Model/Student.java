package com.inventary.enriqueta.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "student")
public class Student {
    @Id
    private String id;
    private String name;
    private String lastName;
    private String documentType;
    private String documentNumber;
    private String gender;
    private String birthdate;
    private String baptism;
    private String communion;
    private String email;
    private String birthPlace;
    private String level;
    private String grade;
    private String section;
    private String status;
}
