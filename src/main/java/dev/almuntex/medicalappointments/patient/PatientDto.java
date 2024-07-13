package dev.almuntex.medicalappointments.patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDto {

    private String idnp;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Character gender;
}
