package com.inventary.enriqueta.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "manager")
public class Manager {
    @Id
    private String id;
    private String lastName;
    private String firstName;
    private String documentType;
    private String documentNumber;
    private String address;
    private Integer ubigeoId;
    private String email;
    private String status;

    @Field("_class")
    private String className;
}
