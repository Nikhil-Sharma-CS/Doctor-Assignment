package org.example.Doctor.Assignment.controller;

import org.example.Doctor.Assignment.model.Doctor;
import org.example.Doctor.Assignment.model.Patient;
import org.example.Doctor.Assignment.service.DoctorService;
import org.example.Doctor.Assignment.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
public class HomeController {


    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientService patientService;

    //Adding Doctor
    @PostMapping("/add-Doctor")
    public String addDoctor(@RequestBody Doctor doctor)
    {
        return doctorService.addDoctor(doctor);
    }

    //Deleting Doctor
    @DeleteMapping("/delete-Doctor")
    public String deleteDoctor(@RequestParam Integer docId)
    {
        return doctorService.deleteDoctor(docId);
    }

    //Adding Patient
    @PostMapping("/add-Patient")
    public String addPatient(@RequestBody Patient patient)
    {
        return patientService.addPatient(patient);
    }

    //Deleting Patient
    @DeleteMapping("/delete-Patient")
    public String deletePatient(@RequestParam Integer patientId)
    {
        return patientService.deletePatient(patientId);
    }

    //Suggesting Doctor based on Patient's Symptom and Location
    @GetMapping("/suggest-Doctor")
    public String suggestDoctor(@RequestParam Integer patientId)
    {
        return patientService.suggestDoctor(patientId);
    }

}
