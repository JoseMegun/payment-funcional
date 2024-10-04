package com.inventary.enriqueta.Controller;

import com.inventary.enriqueta.Model.DTO.ManagerDTO;
import com.inventary.enriqueta.Model.Payment;
import com.inventary.enriqueta.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        try {
            return ResponseEntity.ok(paymentService.getAllPayments());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/active")
    public ResponseEntity<List<Payment>> getActivePayments() {
        try {
            return ResponseEntity.ok(paymentService.getActivePayments());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<Payment>> getInactivePayments() {
        try {
            return ResponseEntity.ok(paymentService.getInactivePayments());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable String id) {
        try {
            return paymentService.getPaymentById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        try {
            return ResponseEntity.ok(paymentService.createPayment(payment));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable String id, @RequestBody Payment paymentDetails) {
        try {
            return ResponseEntity.ok(paymentService.updatePayment(id, paymentDetails));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable String id) {
        try {
            paymentService.deletePayment(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<Payment> deactivatePayment(@PathVariable String id) {
        try {
            return ResponseEntity.ok(paymentService.deactivatePayment(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<Payment> activatePayment(@PathVariable String id) {
        try {
            return ResponseEntity.ok(paymentService.activatePayment(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/managers/{id}")
    public ResponseEntity<ManagerDTO> getManagerById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(paymentService.getManagerDTO(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/managers")
    public ResponseEntity<List<ManagerDTO>> getAllManagers() {
        try {
            return ResponseEntity.ok(paymentService.getAllManagerDTOs());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
