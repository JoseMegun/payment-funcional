package com.inventary.enriqueta.Service;

import com.inventary.enriqueta.Model.Manager;
import com.inventary.enriqueta.Repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    public Optional<Manager> getManagerById(String id) {
        return managerRepository.findById(id);
    }

    public Manager createOrUpdateManager(Manager manager) {
        return managerRepository.save(manager);
    }

    public void deleteManager(String id) {
        managerRepository.deleteById(id);
    }

    public Manager updateManager(String id, Manager managerDetails) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manager not found with id " + id));
        manager.setLastName(managerDetails.getLastName());
        manager.setFirstName(managerDetails.getFirstName());
        manager.setDocumentType(managerDetails.getDocumentType());
        manager.setDocumentNumber(managerDetails.getDocumentNumber());
        manager.setAddress(managerDetails.getAddress());
        manager.setUbigeoId(managerDetails.getUbigeoId());
        manager.setEmail(managerDetails.getEmail());
        manager.setStatus(managerDetails.getStatus());
        return managerRepository.save(manager);
    }

    public Manager logicalDeleteManager(String id) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manager not found with id " + id));
        manager.setStatus("I");
        return managerRepository.save(manager);
    }

    public Manager activateManager(String id) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manager not found with id " + id));
        manager.setStatus("A");
        return managerRepository.save(manager);
    }

    public List<Manager> getActiveManagers() {
        return managerRepository.findByStatus("A");
    }

    public List<Manager> getInactiveManagers() {
        return managerRepository.findByStatus("I");
    }
}
