package org.example.Doctor.Assignment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Doctor.Assignment.model.enums.Symptom;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    @Length(min = 3, message = "Please enter a longer name")
    private String patientName;

    @Length(max = 20, message = "Please enter a shorter city name")
    private String patientCity;

    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Please enter a valid email")
    private String patientEmail;

    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Please enter a valid number")
    private String phoneNumber;

    private Symptom symptom;
}
