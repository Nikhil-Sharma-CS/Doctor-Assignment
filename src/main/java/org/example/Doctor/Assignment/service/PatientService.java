package org.example.Doctor.Assignment.service;

import org.example.Doctor.Assignment.model.Patient;
import org.example.Doctor.Assignment.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    PatientRepo patientRepo;

    @Autowired
    DoctorService doctorService;

    //Adding patient to database
    public String addPatient(Patient patient) {
        patientRepo.save(patient);
        return "Patient saved";
    }


    //Deleting patient from database
    public String deletePatient(Integer patientId) {
        if(patientRepo.existsById(patientId))
        {
            patientRepo.deleteById(patientId);
            return "Patient deleted";
        }
        return "Patient with Id not found";
    }

    //Logic for suggesting doctors to patient
    public String suggestDoctor(Integer patientId) {
        String ans = "";

        Patient patient = patientRepo.findById(patientId).orElse(null);

        if(patient == null)
            return "Patient not found with this Id";

        //Logic to suggest doctor
        //Get location/City of the patient first
        String location = patient.getPatientCity();

        //Get Symptoms from patient
        String symptom = patient.getSymptom().toString();

        ans = doctorService.findDoctor(location, symptom);

        return ans;
    }

    public List<Patient> getAll() {
        return patientRepo.findAll();
    }
}
