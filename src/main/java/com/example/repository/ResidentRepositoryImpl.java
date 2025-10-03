package com.example.repository;

import com.example.model.Resident;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ResidentRepositoryImpl implements ResidentRepository {
    private final List<Resident> residents = new ArrayList<>();

    @Override
    public List<Resident> findAll() {
        return residents;
    }

    @Override
    public Optional<Resident> findById(Long id) {
        return residents.stream().filter(r -> r.getId().equals(id)).findFirst();
    }

    @Override
    public Resident save(Resident resident) {
        residents.add(resident);
        return resident;
    }

    @Override
    public void deleteById(Long id) {
        residents.removeIf(r -> r.getId().equals(id));
    }
}