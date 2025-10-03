package com.example.service;

import com.example.model.Resident;
import com.example.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ResidentService {
    private final ResidentRepository residentRepository;

    @Autowired
    public ResidentService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    public List<Resident> getAllResidents() {
        return residentRepository.findAll();
    }

    public Optional toId(Long id) {
        return residentRepository.findById(id);
    }

    public Resident addResident(Resident resident) {
        logOperation("Добавлен жилец: " + resident.getFullName());
        return residentRepository.save(resident);
    }

    public Resident setInfo(String adress, String fullNAme, Long id, Resident people) {
        people.setAddress(adress);
        people.setFullName(fullNAme);
        people.setId(id);
        return residentRepository.save(people);
    }

    public void deleteResident(Long id) {
        residentRepository.deleteById(id);
        logOperation("Удален жилец с ID: " + id);
    }

    private void logOperation(String message) {
        String logEntry = LocalDateTime.now() + " - " + message + "\n";
        try {
            String logFile = "operations.log";
            Files.write(Paths.get(logFile), logEntry.getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}