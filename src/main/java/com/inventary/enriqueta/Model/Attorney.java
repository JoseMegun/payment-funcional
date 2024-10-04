package com.inventary.enriqueta.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "attorney")
public class Attorney {
    @Id
    private String id;
    private String names;
    private String surnames;
    private String sex;
    private String birthDate;
    private String baptism;
    private String firstCommunion;
    private String confirmation;
    private String marriage;
    private String relationship;
    private String email;
    private String cellphone;
    private String address;
    private String documentType;
    private String documentNumber;
    private String status;
}
