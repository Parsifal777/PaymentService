package com.example.repository;

import com.example.model.Payment;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    private final List<Payment> payments = new ArrayList<>();

    @Override
    public List<Payment> findAll() {
        return payments;
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return payments.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @Override
    public Payment save(Payment payment) {
        payments.add(payment);
        return payment;
    }

    @Override
    public void deleteById(Long id) {
        payments.removeIf(p -> p.getId().equals(id));
    }
}