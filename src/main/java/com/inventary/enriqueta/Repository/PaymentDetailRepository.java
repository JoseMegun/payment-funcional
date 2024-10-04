package com.inventary.enriqueta.Repository;

import com.inventary.enriqueta.Model.PaymentDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentDetailRepository extends MongoRepository<PaymentDetail, String> {
    List<PaymentDetail> findByStatus(String status);
}
