package com.inventary.enriqueta.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@Document(collection = "payment_detail")
public class PaymentDetail {
    @Id
    private String id;
    private Payment payment;
    private String payment_id;
    private String paymentFullName;
    private Attorney attorney;
    private String attorney_id;
    private String attorneyFullName;
    private Student student;
    private String student_id;
    private String studentFullName;
    private String amount;
    private LocalDate date;
    private String paymentType;
    private String status = "A";

    @Field("_class")
    private String className;
}
