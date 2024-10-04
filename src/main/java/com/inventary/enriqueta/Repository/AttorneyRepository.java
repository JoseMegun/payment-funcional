package com.inventary.enriqueta.Repository;

import com.inventary.enriqueta.Model.Attorney;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttorneyRepository extends MongoRepository<Attorney, String> {
}
