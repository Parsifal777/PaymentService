package com.example.service;

import com.example.aspect.LogLevel;
import com.example.aspect.ToLog;
import com.example.model.Payment;
import com.example.model.Resident;
import com.example.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.DoubleAccumulator;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final String logFile = "operations.log";

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional toId(Long id) {
        return paymentRepository.findById(id);
    }

    @ToLog(value = "Обновление информации о платеже", level = LogLevel.INFO)
    public Payment setInfo(Long id, Resident resident, LocalDate data, Double amount, Payment nwPayment) {
        nwPayment.setPaymentDate(data);
        nwPayment.setId(id);
        nwPayment.setAmount(amount);
        nwPayment.setResident(resident);

        return paymentRepository.save(nwPayment);
    }

    @ToLog(value = "Добавление нового платежа", level = LogLevel.INFO)
    public Payment addPayment(Payment payment) {
        logOperation("Добавлен платеж: " + payment.getAmount() + " от " + payment.getResident().getFullName());
        return paymentRepository.save(payment);
    }

    @ToLog(value = "Удаление платежа", level = LogLevel.WARN)
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
        logOperation("Удален платеж с ID: " + id);
    }

    private void logOperation(String message) {
        String logEntry = LocalDateTime.now() + " - " + message + "\n";
        try {
            Files.write(Paths.get(logFile), logEntry.getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}