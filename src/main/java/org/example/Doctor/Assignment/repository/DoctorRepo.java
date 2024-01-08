package org.example.Doctor.Assignment.repository;

import org.example.Doctor.Assignment.model.Doctor;
import org.example.Doctor.Assignment.model.enums.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.List;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Integer> {
    List<Doctor> getByDocCity(City location);
}
