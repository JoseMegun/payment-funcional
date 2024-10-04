package com.inventary.enriqueta.Repository;

import com.inventary.enriqueta.Model.Manager;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ManagerRepository extends MongoRepository<Manager, String> {
    List<Manager> findByStatus(String status);
}

