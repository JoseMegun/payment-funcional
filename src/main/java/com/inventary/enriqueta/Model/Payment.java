package com.inventary.enriqueta.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@Document(collection = "payment")
public class Payment {
    @Id
    private String id;

    @DBRef
    private Manager manager;
    private String description;
    private LocalDate dueDate;
    private LocalDate date;
    private String amount;
    private String status;
    private String manager_id;
    private String managerFullName;

    @Field("_class")
    private String className;
}
