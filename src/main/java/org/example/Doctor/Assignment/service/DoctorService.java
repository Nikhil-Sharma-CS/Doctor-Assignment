package org.example.Doctor.Assignment.service;

import org.example.Doctor.Assignment.model.Doctor;
import org.example.Doctor.Assignment.model.enums.City;
import org.example.Doctor.Assignment.model.enums.Symptom;
import org.example.Doctor.Assignment.repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    DoctorRepo doctorRepo;

    //Adding Doctor to Database
    public String addDoctor(Doctor doctor) {
        doctorRepo.save(doctor);
        System.out.println(doctor.getSpeciality());
        return "Doctor Saved";
    }

    //Deleting Doctor From Database
    public String deleteDoctor(Integer docId) {
        if(doctorRepo.existsById(docId))
        {
            doctorRepo.deleteById(docId);
            return "Doctor deleted";
        }
        return "Doctor with Id not found";
    }

    //Finding Doctors based on location and patient's symptom
    public String findDoctor(String location, String symptom) {
        //Find doctors on the location

        //If the location is not of these 3
        if(!location.equals("Delhi") && !location.equals("Noida") && !location.equals("Faridabad"))
        {
            return "We are still waiting to expand to your location";
        }

        //Getting list of doctors based on location
        List<Doctor> doctors = doctorRepo.getByDocCity(City.valueOf(location));
        List<Doctor> recommended;

        //In case the doctors are not available at that location
        if(doctors.isEmpty())
            return "We are still waiting to expand to your location";


        recommended = switch (symptom) {
            case "Arthritis", "Back_Pain", "Tissue_Injuries" ->
                    doctors.stream().filter(doctor -> doctor.getSpeciality().toString().equals("Orthopedic")).toList();
            case "Dysmenorrhea" ->
                    doctors.stream().filter(doctor -> doctor.getSpeciality().toString().equals("Gynecology")).toList();
            case "Skin_Infection", "Skin_Burn" ->
                    doctors.stream().filter(doctor -> doctor.getSpeciality().toString().equals("Dermatology")).toList();
            default -> doctors.stream().filter(doctor -> doctor.getSpeciality().toString().equals("ENT")).toList();
        };

        //If there isn't any doctor for the symptom in that area
        if(recommended.isEmpty())
            return "There isn’t any doctor present at your location for your symptom";
        else
        {
            //Creating list of doctors as answer
            StringBuilder ans = new StringBuilder();
            for(Doctor doctor : recommended)
            {
                String doc = "Doctor Name : " + doctor.getDocName()
                        + ", Email : " + doctor.getDocEmail()
                        + ", Phone Number : " + doctor.getPhoneNumber()
                        + ", City : " + doctor.getDocCity()
                        + ", Speciality : " + doctor.getSpeciality();
                ans.append("\n").append(doc);
            }
            return ans.toString();
        }

    }
}
