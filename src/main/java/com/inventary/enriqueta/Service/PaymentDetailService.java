package com.inventary.enriqueta.Service;

import com.inventary.enriqueta.Model.DTO.AttorneyDTO;
import com.inventary.enriqueta.Model.DTO.StudentDTO;
import com.inventary.enriqueta.Model.Payment;
import com.inventary.enriqueta.Model.PaymentDetail;
import com.inventary.enriqueta.Model.Attorney;
import com.inventary.enriqueta.Model.Student;
import com.inventary.enriqueta.Repository.AttorneyRepository;
import com.inventary.enriqueta.Repository.PaymentDetailRepository;
import com.inventary.enriqueta.Repository.PaymentRepository;
import com.inventary.enriqueta.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentDetailService {

    @Autowired
    private PaymentDetailRepository paymentDetailRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private AttorneyRepository attorneyRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public PaymentDetail createPaymentDetail(PaymentDetail paymentDetail) {
        Payment payment = paymentRepository.findById(paymentDetail.getPayment_id())
                .orElseThrow(() -> new RuntimeException("Payment not found with id " + paymentDetail.getPayment().getId()));
        paymentDetail.setPayment(payment);

        Attorney attorney = attorneyRepository.findById(paymentDetail.getAttorney_id())
                .orElseThrow(() -> new RuntimeException("Male Attorney not found with id " + paymentDetail.getAttorney().getId()));
        paymentDetail.setAttorney(attorney);

        Student student = studentRepository.findById(paymentDetail.getStudent_id())
                .orElseThrow(() -> new RuntimeException("Female Attorney not found with id " + paymentDetail.getStudent().getId()));
        paymentDetail.setStudent(student);

        paymentDetail.setClassName("com.inventary.enriqueta.Model.payment_detail");
        return paymentDetailRepository.save(paymentDetail);
    }

    public List<PaymentDetail> getAllPaymentDetails() {
        return paymentDetailRepository.findAll();
    }

    public List<PaymentDetail> getActivePaymentDetails() {
        return paymentDetailRepository.findByStatus("A");
    }

    public List<PaymentDetail> getInactivePaymentDetails() {
        return paymentDetailRepository.findByStatus("I");
    }

    public Optional<PaymentDetail> getPaymentDetailById(String id) {
        return paymentDetailRepository.findById(id);
    }

    public void deletePaymentDetail(String id) {
        paymentDetailRepository.deleteById(id);
    }

    public PaymentDetail updatePaymentDetail(String id, PaymentDetail paymentDetailDetails) {

        PaymentDetail paymentDetail = paymentDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PaymentDetail not found with id " + id));

        Payment payment = paymentRepository.findById(paymentDetailDetails.getPayment_id())
                .orElseThrow(() -> new RuntimeException("Payment not found with id " + paymentDetailDetails.getPayment().getId()));
        paymentDetail.setPayment(payment);

        Attorney attorney = attorneyRepository.findById(paymentDetailDetails.getAttorney_id())
                .orElseThrow(() -> new RuntimeException("Male Attorney not found with id " + paymentDetailDetails.getAttorney().getId()));
        paymentDetail.setAttorney(attorney);

        Student student = studentRepository.findById(paymentDetailDetails.getStudent_id())
                .orElseThrow(() -> new RuntimeException("Student not found with id " + paymentDetailDetails.getStudent().getId()));
        paymentDetail.setStudent(student);

        paymentDetail.setPayment(payment);
        paymentDetail.setPayment_id(paymentDetailDetails.getPayment_id());
        paymentDetail.setPaymentFullName(paymentDetailDetails.getPaymentFullName());
        paymentDetail.setAttorney(attorney);
        paymentDetail.setAttorney_id(paymentDetailDetails.getAttorney_id());
        paymentDetail.setAttorneyFullName(paymentDetailDetails.getAttorneyFullName());
        paymentDetail.setStudent(student);
        paymentDetail.setStudent_id(paymentDetailDetails.getStudent_id());
        paymentDetail.setStudentFullName(paymentDetailDetails.getStudentFullName());
        paymentDetail.setAmount(paymentDetailDetails.getAmount());
        paymentDetail.setDate(paymentDetailDetails.getDate());
        paymentDetail.setPaymentType(paymentDetailDetails.getPaymentType());
        paymentDetail.setStatus(paymentDetailDetails.getStatus());
        paymentDetail.setClassName("com.inventary.enriqueta.Model.payment_detail");
        return paymentDetailRepository.save(paymentDetail);
    }

    public PaymentDetail deactivatePaymentDetail(String id) {
        PaymentDetail paymentDetail = paymentDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PaymentDetail not found with id " + id));
        paymentDetail.setStatus("I");
        return paymentDetailRepository.save(paymentDetail);
    }

    public PaymentDetail activatePaymentDetail(String id) {
        PaymentDetail paymentDetail = paymentDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PaymentDetail not found with id " + id));
        paymentDetail.setStatus("A");
        return paymentDetailRepository.save(paymentDetail);
    }

    public StudentDTO getStudentDTO(String studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id " + studentId));
        return new StudentDTO(student.getName(), student.getLastName());
    }

    public AttorneyDTO getAttorneyDTO(String attorneyId) {
        Attorney attorney = attorneyRepository.findById(attorneyId)
                .orElseThrow(() -> new RuntimeException("Attorney not found with id " + attorneyId));
        return new AttorneyDTO(attorney.getNames(), attorney.getSurnames());
    }
}
