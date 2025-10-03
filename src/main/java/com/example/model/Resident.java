package com.example.model;

public class Resident {
    private Long id;
    private String fullName;
    private String address;

    public Resident() {}

    public Resident(Long id, String fullName, String address) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return "com.example.model.Resident{id=" + id + ", fullName='" + fullName + "', address='" + address + "'}";
    }
}