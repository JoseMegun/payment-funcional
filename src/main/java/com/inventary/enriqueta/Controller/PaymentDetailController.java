package com.inventary.enriqueta.Controller;

import com.inventary.enriqueta.Model.DTO.AttorneyDTO;
import com.inventary.enriqueta.Model.DTO.StudentDTO;
import com.inventary.enriqueta.Model.PaymentDetail;
import com.inventary.enriqueta.Service.PaymentDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/payment-details")
public class PaymentDetailController {

    @Autowired
    private PaymentDetailService paymentDetailService;

    @GetMapping
    public ResponseEntity<List<PaymentDetail>> getAllPaymentDetails() {
        try {
            return ResponseEntity.ok(paymentDetailService.getAllPaymentDetails());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/active")
    public ResponseEntity<List<PaymentDetail>> getActivePaymentDetails() {
        try {
            return ResponseEntity.ok(paymentDetailService.getActivePaymentDetails());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<PaymentDetail>> getInactivePaymentDetails() {
        try {
            return ResponseEntity.ok(paymentDetailService.getInactivePaymentDetails());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDetail> getPaymentDetailById(@PathVariable String id) {
        try {
            return paymentDetailService.getPaymentDetailById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<PaymentDetail> createPaymentDetail(@RequestBody PaymentDetail paymentDetail) {
        try {
            return ResponseEntity.ok(paymentDetailService.createPaymentDetail(paymentDetail));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDetail> updatePaymentDetail(@PathVariable String id, @RequestBody PaymentDetail paymentDetailDetails) {
        try {
            return ResponseEntity.ok(paymentDetailService.updatePaymentDetail(id, paymentDetailDetails));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentDetail(@PathVariable String id) {
        try {
            paymentDetailService.deletePaymentDetail(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<PaymentDetail> deactivatePaymentDetail(@PathVariable String id) {
        try {
            return ResponseEntity.ok(paymentDetailService.deactivatePaymentDetail(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<PaymentDetail> activatePaymentDetail(@PathVariable String id) {
        try {
            return ResponseEntity.ok(paymentDetailService.activatePaymentDetail(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(paymentDetailService.getStudentDTO(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/attorneys/{id}")
    public ResponseEntity<AttorneyDTO> getAttorneyById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(paymentDetailService.getAttorneyDTO(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
