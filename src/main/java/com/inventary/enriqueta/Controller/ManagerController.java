package com.inventary.enriqueta.Controller;

import com.inventary.enriqueta.Model.Manager;
import com.inventary.enriqueta.Service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/managers")

public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping
    public List<Manager> getAllManagers() {
        return managerService.getAllManagers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manager> getManagerById(@PathVariable String id) {
        return managerService.getManagerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Manager createManager(@RequestBody Manager manager) {
        return managerService.createOrUpdateManager(manager);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manager> updateManager(@PathVariable String id, @RequestBody Manager managerDetails) {
        return ResponseEntity.ok(managerService.updateManager(id, managerDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManager(@PathVariable String id) {
        managerService.deleteManager(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<Manager> logicalDeleteManager(@PathVariable String id) {
        return ResponseEntity.ok(managerService.logicalDeleteManager(id));
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<Manager> activateManager(@PathVariable String id) {
        return ResponseEntity.ok(managerService.activateManager(id));
    }

    @GetMapping("/active")
    public List<Manager> getActiveManagers() {
        return managerService.getActiveManagers();
    }

    @GetMapping("/inactive")
    public List<Manager> getInactiveManagers() {
        return managerService.getInactiveManagers();
    }
}
