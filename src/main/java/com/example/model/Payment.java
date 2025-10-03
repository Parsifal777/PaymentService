package com.example.model;

import java.time.LocalDate;

public class Payment {
    private Long id;
    private Resident resident;
    private LocalDate paymentDate;
    private Double amount;

    public Payment() {}
    public Payment(Long id, Resident resident, LocalDate paymentDate, Double amount) {
        this.id = id;
        this.resident = resident;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Resident getResident() { return resident; }
    public void setResident(Resident resident) { this.resident = resident; }

    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    @Override
    public String toString() {
        return "com.example.model.Payment{id=" + id + ", resident=" + resident.getFullName() +
                ", paymentDate=" + paymentDate + ", amount=" + amount + "}";
    }
}