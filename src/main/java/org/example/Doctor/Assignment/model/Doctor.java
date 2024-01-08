package org.example.Doctor.Assignment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Doctor.Assignment.model.enums.City;
import org.example.Doctor.Assignment.model.enums.Speciality;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    @Length(min = 3, message = "Please enter a longer name")
    private String docName;

    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Please enter a valid number")
    private String phoneNumber;

    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Please enter a valid email")
    private String docEmail;

    @Enumerated(EnumType.STRING)
    private City docCity;

    @Enumerated(EnumType.STRING)
    private Speciality speciality;

}
