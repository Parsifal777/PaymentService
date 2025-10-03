package com.example.repository;

import com.example.model.Resident;

import java.util.List;
import java.util.Optional;

public interface ResidentRepository {
    List<Resident> findAll();
    Optional<Resident> findById(Long id);
    Resident save(Resident resident);
    void deleteById(Long id);
}