package com.example.model;

import java.time.LocalDate;

public class Invoice {
    private Long id;
    private Resident resident;
    private LocalDate period;
    private Double amount;

    public Invoice() {}
    public Invoice(Long id, Resident resident, LocalDate period, Double amount) {
        this.id = id;
        this.resident = resident;
        this.period = period;
        this.amount = amount;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Resident getResident() { return resident; }
    public void setResident(Resident resident) { this.resident = resident; }

    public LocalDate getPeriod() { return period; }
    public void setPeriod(LocalDate period) { this.period = period; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    @Override
    public String toString() {
        return "com.example.model.Invoice{id=" + id + ", resident=" + resident.getFullName() +
                ", period=" + period + ", amount=" + amount + "}";
    }
}