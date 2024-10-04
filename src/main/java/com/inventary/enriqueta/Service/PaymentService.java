package com.inventary.enriqueta.Service;

import com.inventary.enriqueta.Model.DTO.ManagerDTO;
import com.inventary.enriqueta.Model.Manager;
import com.inventary.enriqueta.Model.Payment;
import com.inventary.enriqueta.Repository.ManagerRepository;
import com.inventary.enriqueta.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ManagerRepository managerRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public List<Payment> getActivePayments() {
        return paymentRepository.findByStatus("A");
    }

    public List<Payment> getInactivePayments() {
        return paymentRepository.findByStatus("I");
    }

    public Optional<Payment> getPaymentById(String id) {
        return paymentRepository.findById(id);
    }

    public Payment createPayment(Payment payment) {
        Manager manager = managerRepository.findById(payment.getManager_id())
                .orElseThrow(() -> new RuntimeException("Manager not found with id " + payment.getManager().getId()));
        payment.setManager(manager);

        payment.setClassName("com.inventary.enriqueta.Model.payment");
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(String id, Payment paymentDetails) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id " + id));

        Manager manager = managerRepository.findById(paymentDetails.getManager_id())
                .orElseThrow(() -> new RuntimeException("Manager not found with id " + paymentDetails.getManager().getId()));

        payment.setManager(manager);
        payment.setDescription(paymentDetails.getDescription());
        payment.setDueDate(paymentDetails.getDueDate());
        payment.setDate(paymentDetails.getDate());
        payment.setAmount(paymentDetails.getAmount());
        payment.setManager_id(paymentDetails.getManager_id());
        payment.setManagerFullName(paymentDetails.getManagerFullName());
        payment.setStatus(paymentDetails.getStatus());
        payment.setClassName("com.inventary.enriqueta.Model.payment");
        return paymentRepository.save(payment);
    }

    public void deletePayment(String id) {
        paymentRepository.deleteById(id);
    }

    public Payment deactivatePayment(String id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id " + id));
        payment.setStatus("I");
        return paymentRepository.save(payment);
    }

    public Payment activatePayment(String id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id " + id));
        payment.setStatus("A");
        return paymentRepository.save(payment);
    }

    public ManagerDTO getManagerDTO(String managerId) {
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager not found with id " + managerId));
        return new ManagerDTO(manager.getFirstName(), manager.getLastName());
    }

    public List<ManagerDTO> getAllManagerDTOs() {
        List<Manager> managers = managerRepository.findAll();
        return managers.stream()
                .map(manager -> new ManagerDTO(manager.getFirstName(), manager.getLastName()))
                .collect(Collectors.toList());
    }
}
